package com.baijia123.test;

public class HandlerTest {
    
    public static class AbortExecutionHandler implements ExecutionHandler {

        @Override
        public void rejectedExecution() {
            // TODO Auto-generated method stub
            System.out.println("AbortExecutionHandler");
        }

    }
    
    public static class DiscardExecutionHandler implements ExecutionHandler {

        @Override
        public void rejectedExecution() {
            // TODO Auto-generated method stub
            System.out.println("DiscardExecutionHandler");
        }

    }

    public final static ExecutionHandler handler = new AbortExecutionHandler();
    
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
