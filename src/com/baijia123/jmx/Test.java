package com.baijia123.jmx;

public class Test implements TestMBean {

    private String name;

    @Override
    public void printHelloWorld() {
        // TODO Auto-generated method stub
        System.out.println(name + ",welcome to this world.");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        this.name = name;
    }

}
