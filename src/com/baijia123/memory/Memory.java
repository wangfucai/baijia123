package com.baijia123.memory;

public class Memory {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Runtime currRuntime = Runtime.getRuntime ();
        int nFreeMemory = ( int ) (currRuntime.freeMemory() / 1024 / 1024);
        int nTotalMemory = ( int ) (currRuntime.totalMemory() / 1024 / 1024);
        System.out.println("memory = " + nFreeMemory + "M/" + nTotalMemory + "M(free/total)");
    }

}
