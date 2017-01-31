package com.components;

import com.errors.Error;

import java.util.*;

public final class TaskList {

    private final static Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private static TaskList taskList = new TaskList();
    private static long lastId = 0;

    public Map<String, List<Task>> getTasks() {
        return tasks;
    }

    private TaskList() {
    }

    public static TaskList getInstant(){
        return taskList;
    }

    public void delete( String commandLine) {
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


    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private static void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    private static void addTask(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            Error.errorProject(project);
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    private static long nextId() {
        return ++lastId;
    }
}
