package com.errors;

import com.Main;

public class Error {
    public static void errorCommand(String command) {
        Main.getOut().printf("I don't know what the command \"%s\" is.", command);
        Main.getOut().println();
    }
    public static void errorArg(String command){
        Main.getOut().printf("errors argument does not exist \"%s\" is.", command);
        Main.getOut().println();
    }
    public static void errorId(int id){
        Main.getOut().printf("Could not find a task with an ID of %d.", id);
        Main.getOut().println();
    }
    public static void errorProject(String project){
        Main.getOut().printf("Could not find a project with the name \"%s\".", project);
        Main.getOut().println();
    }
}
