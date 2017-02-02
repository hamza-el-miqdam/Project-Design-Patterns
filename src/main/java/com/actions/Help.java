package com.actions;

import com.Main;

public class Help {
    public static void help() {
        Main.getOut().println("Commands:");
        Main.getOut().println("  show");
        Main.getOut().println("  add project <project name>");
        Main.getOut().println("  add task <project name> <task description> ");
        Main.getOut().println("  check <task ID>");
        Main.getOut().println("  uncheck <task ID>");
        Main.getOut().println("  delete <task ID>");
        Main.getOut().println("  deadline <task ID> <deadline format 31/12/2017>");
        Main.getOut().println("  today ");
        Main.getOut().println("  share <task ID> <project name>");
        Main.getOut().println("  view by <date deadline or project> ");
        Main.getOut().println();
    }
}
