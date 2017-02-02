package com.dispatcher;

import com.actions.*;
import com.errors.Error;


public class Dispatcher {

    public static void execute( String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                Show.show();
                break;
            case "add":
                Add.add(commandRest[1]);
                break;
            case "check":
                Check.check(commandRest[1]);
                break;
            case "uncheck":
                Check.uncheck(commandRest[1]);
                break;
            case "help":
                Help.help();
                break;
            case "today":
                Deadline.today();
                break;
            case "deadline":
                Deadline.setDeadline(commandRest[1]);
                break;
            case "delete":
                DeleteTask.delete(commandRest[1]);
                break;
            case "view":
                View.viewBy(commandRest[1]);
                break;
            case "share":
                Share.shareTask(commandRest[1]);
                break;
            default:
                Error.errorCommand(command);
                break;
        }
    }
}
