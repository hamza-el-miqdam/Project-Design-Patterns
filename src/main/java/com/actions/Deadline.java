package com.actions;

import com.Main;
import com.components.Task;
import com.components.TaskList;
import com.errors.Error;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Deadline {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static void setDeadline(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);

        int id = Integer.parseInt(subcommandRest[0]);
        Date datelim = null;
        try {
            datelim = formatter.parse(subcommandRest[1]);
        } catch (ParseException e) {
            Error.errorDate();
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

    public static void today(){
        Calendar now = new GregorianCalendar();
        Date nowdate = now.getTime();
        String nowstr = formatter.format(nowdate);

        for (Map.Entry<String, List<Task>> project : TaskList.getInstant().getTasks().entrySet()) {
            Main.getOut().println(nowstr);
            Main.getOut().println("  "+project.getKey());
            for (Task task : project.getValue()) {
                if(formatter.format(task.getDateLimite()).equals(nowstr)){
                    Main.getOut().printf("    [%c] %d: %s >Deadline %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), formatter.format(task.getDateLimite()));
                }
            }
            Main.getOut().println();
        }

    }
}

