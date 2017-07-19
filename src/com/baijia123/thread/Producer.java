package com.baijia123.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

public class Producer extends Thread {
    List<Integer> list = new ArrayList<>();
    Exchanger<List<Integer>> exchanger = null;

    public Producer(Exchanger<List<Integer>> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            list.clear();
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            list.add(rand.nextInt(10000));
            try {
                list = exchanger.exchange(list);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
