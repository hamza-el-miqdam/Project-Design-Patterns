package com.actions;

import com.components.Task;
import com.components.TaskList;
import com.errors.Error;

import java.util.List;
import java.util.Map;


public class Share {
    public static void shareTask(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        int id = Integer.parseInt(subcommandRest[0]);
        String projectName = subcommandRest[1];
        List<Task> projectTasks = TaskList.getInstant().getTasks().get(projectName);
        if (projectTasks == null) {
            Error.errorProject(projectName);
            return;
        }
        for (Map.Entry<String, List<Task>> project : TaskList.getInstant().getTasks().entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    TaskList.getInstant().getTasks().get(projectName).add(task);
                    return;
                }
            }
        }

    }
}
