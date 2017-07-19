package com.baijia123.thread;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangeTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        new Consumer(exchanger).start();
        new Producer(exchanger).start();
    }
}
