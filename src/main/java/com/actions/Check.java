package com.actions;

import com.components.Task;
import com.components.TaskList;
import com.errors.Error;

import java.util.List;
import java.util.Map;


public class Check {

    public static void check(String idString) {
        setDone(idString, true);
    }

    public static void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : TaskList.getInstant().getTasks().entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        Error.errorId(id);
    }

    public static void uncheck(String idString) {
        setDone(idString, false);
    }
}
