package com.errors;

import com.Main;

public class Error {
    public static void errorCommand(String command) {
        Main.getOut().printf("Error command : I don't know what the command \"%s\" is.", command);
        Main.getOut().println();
    }
    public static void errorArg(String argument){
        Main.getOut().printf("Error arguments : error argument does not exist \"%s\" is.", argument);
        Main.getOut().println();
    }
    public static void errorId(int id){
        Main.getOut().printf("Error Id task : Could not find a task with an ID of %d.", id);
        Main.getOut().println();
    }
    public static void errorProject(String project){
        Main.getOut().printf("Error name project : Could not find a project with the name \"%s\".", project);
        Main.getOut().println();
    }
    public static void errorDate(){
        Main.getOut().printf("Error date format : Put the date in the format dd/mm/yyyy." );
        Main.getOut().println();
    }
}
