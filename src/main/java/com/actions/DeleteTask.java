package com.actions;

import com.components.Task;
import com.components.TaskList;

import java.util.List;
import java.util.Map;


public class DeleteTask {
    public static void delete( String commandLine) {
        int id = Integer.parseInt(commandLine);
        for (Map.Entry<String, List<Task>> project : TaskList.getInstant().getTasks().entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    project.getValue().remove(task);
                    return;
                }
            }
        }
    }
}
