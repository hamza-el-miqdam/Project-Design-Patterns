package com.actions;

import com.components.Task;
import com.components.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Deadline {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static void deadline(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);

        int id = Integer.parseInt(subcommandRest[0]);
        Date datelim = null;
        try {
            datelim = formatter.parse(subcommandRest[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, List<Task>> project : TaskList.getInstant().getTasks().entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDateLimite(datelim);
                    return;
                }
            }
        }
    }
}

