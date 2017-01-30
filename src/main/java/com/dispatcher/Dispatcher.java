package com.dispatcher;

import com.Main;
import com.actions.*;
import com.components.TaskList;
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
                TaskList.getInstant().add(commandRest[1]);
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
                TodayDeadline.todaydead();
                break;
            case "deadline":
                Deadline.deadline(commandRest[1]);
                break;
            case "delete":
                TaskList.getInstant().delete(commandRest[1]);
                break;
            case "view":
                Show.viewby(Main.getOut(),commandRest[1]);
                break;
            default:
                Error.errorCommand(command);
                break;
        }
    }
}
