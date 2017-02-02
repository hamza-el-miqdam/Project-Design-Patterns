package com.actions;

import com.Main;
import com.components.Task;
import com.components.TaskList;

import java.util.List;
import java.util.Map;

public class Show {

    public static void show() {
         for (Map.Entry<String, List<Task>> project : TaskList.getInstant().getTasks().entrySet()) {
             Main.getOut().println(project.getKey());
             for (Task task : project.getValue()) {
                 Main.getOut().printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
             }
             Main.getOut().println();
         }
     }

}
