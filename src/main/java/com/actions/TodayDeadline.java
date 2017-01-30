package com.actions;

import com.Main;
import com.components.Task;
import com.components.TaskList;

import java.text.SimpleDateFormat;
import java.util.*;


public class TodayDeadline {
    public static void todaydead(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
