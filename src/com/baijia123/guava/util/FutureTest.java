package com.baijia123.guava.util;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class FutureTest {
    public static void main(String[] argv) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture explosion = service.submit(new Callable() {

            @Override
            public Object call() throws Exception {
                // TODO Auto-generated method stub
                return null;
            }

        });
        Futures.addCallback(explosion, new FutureCallback() {

            @Override
            public void onSuccess(Object result) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFailure(Throwable t) {
                // TODO Auto-generated method stub

            }

        });
    }
}
