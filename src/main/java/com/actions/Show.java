package com.actions;

import com.Main;
import com.components.Task;
import com.components.TaskList;
import com.errors.Error;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by elmiq_60ab9qq on 1/29/2017.
 */
public class Show {
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static void showByDate(Map<String, List<Task>> byDate){

        for (Map.Entry<String, List<Task>> initDates : byDate.entrySet()) {
            Main.getOut().println(initDates.getKey());
            for (Task task : initDates.getValue()) {
                Main.getOut().printf("    [%c] %d: %s %n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            Main.getOut().println();
        }

    }
    public static Map<String, List<Task>> creatMap(String opt){
        Map<String, List<Task>> byTheDate = new TreeMap<>();
        for (Map.Entry<String, List<Task>> project : TaskList.getInstant().getTasks().entrySet()) {
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


     public static void show() {
         for (Map.Entry<String, List<Task>> project : TaskList.getInstant().getTasks().entrySet()) {
             Main.getOut().println(project.getKey());
             for (Task task : project.getValue()) {
                 Main.getOut().printf("    [%c] %d: %s >Deadline %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), formatter.format(task.getDateLimite()));
             }
             Main.getOut().println();
         }
     }

    public static void viewby(String commandLine) {
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
            case "Project":
                show();
                break;
            default:
                Error.errorArg(commandLine);
                break;
        }
    }
}
