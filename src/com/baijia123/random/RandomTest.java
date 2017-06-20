package com.baijia123.random;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Random r1 = new Random(100);
        Random r2 = new Random(100);
        for(int i = 0 ; i < 100 ; i ++) {
            System.out.println(r1.nextInt(10) + ", " + r2.nextInt(10));
        }
    }

}
