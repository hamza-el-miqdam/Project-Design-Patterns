package com.components;

import java.util.*;

public final class TaskList {

    private final static Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private static TaskList taskList = new TaskList();

    public Map<String, List<Task>> getTasks() {
        return tasks;
    }

    private TaskList() {
    }

    public static TaskList getInstant(){
        return taskList;
    }
}
