package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;
    private Date theDate;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(commandRest[1]);
                break;
            case "uncheck":
                uncheck(commandRest[1]);
                break;
            case "help":
                help();
                break;
            case "today":
                todaydead();
                break;
            case "deadline":
                deadline(commandRest[1]);
                break;
            case "delete":
                delete(commandRest[1]);
                break;
            case "view":
                viewby(commandRest[1]);
                break;
            default:
                error(command,0);
                break;
        }
    }

    private void viewby(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        switch (subcommandRest[1]){
            case "date":
                Map<String, List<Task>> byDate = creatMap("date");
                showByDate(byDate);
                break;
            case "deadline":
                Map<String, List<Task>> byDeadline = creatMap("deadline");
                showByDate(byDeadline);
                break;
            case "project":
                show();
                break;
            default:
                error(commandLine,1);
                break;
        }
    }
    private Map<String, List<Task>> creatMap(String opt){
        Map<String, List<Task>> byTheDate = new TreeMap<>();
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                String theDate;
                switch (opt) {
                    case "date":
                        theDate = formatter.format(task.getDate());
                        break;
                    case "deadline":
                        theDate = formatter.format(task.getDateLimite());
                        break;
                    default:
                        theDate = null;
                        break;
                }
                List<Task> listTasks = byTheDate.get(theDate);
                if (listTasks == null){
                    byTheDate.put(theDate, new ArrayList<Task>());
                    byTheDate.get(theDate).add(task);
                }else{
                    listTasks.add(task);
                }
            }
        }
        return byTheDate;
    }
    private void showByDate(Map<String, List<Task>> byDate){

        for (Map.Entry<String, List<Task>> initDates : byDate.entrySet()) {
            out.println(initDates.getKey());
            for (Task task : initDates.getValue()) {
                out.printf("    [%c] %d: %s %n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }

    }
    private void delete(String commandLine) {
        int id = Integer.parseInt(commandLine);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    project.getValue().remove(task);
                    return;
                }
            }
        }
    }

    private void deadline(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);

        int id = Integer.parseInt(subcommandRest[0]);
        Date datelim = null;
        try {
            datelim = formatter.parse(subcommandRest[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDateLimite(datelim);
                    return;
                }
            }
        }
    }


    private void todaydead(){
        Calendar now = new GregorianCalendar();
        Date nowdate = now.getTime();
        String nowstr = formatter.format(nowdate);

        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                if(formatter.format(task.getDateLimite()).equals(nowstr)){
                    out.printf("    [%c] %d: %s >Deadline %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), formatter.format(task.getDateLimite()));
                }
            }
            out.println();
            }

        }
    private void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s >Deadline %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), formatter.format(task.getDateLimite()));
            }
            out.println();
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    private void addTask(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    private void check(String idString) {
        setDone(idString, true);
    }

    private void uncheck(String idString) {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description> ");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  deadline <task ID> <deadline format 26/01/2017>");
        out.println("  today <task ID>");
        out.println();
    }

    private void error(String command,int numeroError) {
        switch (numeroError) {
            case 0:
                out.printf("I don't know what the command \"%s\" is.", command);
                out.println();
                break;
            case 1:
                out.printf("error argument does not exist \"%s\" is.", command);
                out.println();
                break;
        }
    }

    private long nextId() {
        return ++lastId;
    }
}
