package com.actions;

import com.components.Task;
import com.components.TaskList;
import com.errors.Error;

import java.util.ArrayList;
import java.util.List;


public class Add {

    private static long lastTaskID = 0;

    public static void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    public static void addProject(String name) {
        TaskList.getInstant().getTasks().put(name, new ArrayList<Task>());
    }

    public static void addTask(String project, String description) {
        List<Task> projectTasks = TaskList.getInstant().getTasks().get(project);
        if (projectTasks == null) {
            Error.errorProject(project);
            return;
        }
        projectTasks.add(new Task(nextTaskId(), description, false));
    }
    private static long nextTaskId() {
        return ++ lastTaskID;
    }

}
