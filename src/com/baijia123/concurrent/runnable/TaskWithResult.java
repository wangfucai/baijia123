package com.baijia123.concurrent.runnable;

import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {

    private int id;

    @Override
    public String call() throws Exception {
        // TODO Auto-generated method stub
        return "result of TaskWithResult " + id;
    }

    public TaskWithResult(int id) {
        super();
        this.id = id;
    }

}
