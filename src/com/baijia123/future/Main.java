package com.baijia123.future;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();

    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t) / 1000 + " seconds");
        return rand.nextInt(1000);
    }

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.<T> toList()));
    }

    public static <T> CompletableFuture<List<T>> sequence(Stream<CompletableFuture<T>> futures) {
        List<CompletableFuture<T>> futureList = futures.filter(f -> f != null).collect(Collectors.toList());
        return sequence(futureList);
    }

    public static <T> CompletableFuture<T> toCompletable(Future<T> future, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main::getMoreData);
        Future<Integer> f0 = future.whenComplete((v, e) -> {
            System.out.println("v = " + v);
            System.out.println("e = " + e);
        });
        System.out.println(f0.get());
        // System.in.read();

        // 辅助方法 allOf 和 anyOf
        Random rand = new Random();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        CompletableFuture<Void> f1 = CompletableFuture.allOf(future1, future2);
        // CompletableFuture<Object> f1 = CompletableFuture.anyOf(future1, future2);
        System.out.println("辅助方法值 = " + f1.get());

        // 转换
        future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f2 = future.thenApplyAsync(i -> i * 10).thenApply(i -> i.toString());
        System.out.println("转换值 = " + f2.get()); // "1000"

        // 纯消费(执行Action)
        future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f3 = future.thenAccept(System.out::println);
        System.out.println("纯消费(执行Action)值 = " + f3.get());

        CompletableFuture<Void> f4 = future.thenAcceptBoth(CompletableFuture.completedFuture(10), (x, y) -> System.out.println(x * y));
        System.out.println(f4.get());

        // 组合
        future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f5 = future.thenCompose(i -> {
            return CompletableFuture.supplyAsync(() -> {
                return (i * 10) + "";
            });
        });
        System.out.println("组合值 = " + f5.get()); // 1000

        future1 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        future2 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });
        CompletableFuture<String> f6 = future1.thenCombine(future2, (x, y) -> y + "-" + x);
        System.out.println(f6.get()); // abc-100

        // Either
        future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });
        CompletableFuture<String> f7 = future1.applyToEither(future3, i -> i.toString());
        System.out.println("Either = " + f7.get()); // abc-100
    }
}
