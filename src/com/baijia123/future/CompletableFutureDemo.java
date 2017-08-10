package com.baijia123.future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * @ClassName:CompletableFutureDemo
 * @Description:���̲߳�������,ȡ����鼯
 * @author WangFuCai
 *
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        // �����
        List<String> list = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        // ����3�̳߳�
        ExecutorService exs = Executors.newFixedThreadPool(10);
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        List<Integer> taskList = Lists.newArrayList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        try {
             //��ʽһ��ѭ������CompletableFuture list,����sequence()��װ����һ���з���ֵ��CompletableFuture�����ؽ��get()��ȡ
            for (int i = 0; i < taskList.size(); i++) {
                final int j = i + 1;
                CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> calc(j), exs)// �첽ִ��
                        .thenApply(e -> Integer.toString(e))// Integerת���ַ��� thenAcceptֻ���ܲ����ز�Ӱ����
                        .whenComplete((v, e) -> {// �����ȡ�����������˳�򣬴˴����뼴��
                                    System.out.println("����" + v + "���!result=" + v + "���쳣 e=" + e + "," + new Date());
                                    list2.add(v);
                                });
                futureList.add(future);
            }
            // ��ʽ��ȡ���
            list = sequence(futureList).get();// [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]�˴������Ϊʲô��������˳��˭֪�����֪

            // ��ʽ����ȫ��ʽ����ת����CompletableFuture[]+��װ��һ���޷���ֵCompletableFuture��join�ȴ�ִ����ϡ����ؽ��whenComplete��ȡ
            /*
            @SuppressWarnings("rawtypes")
            CompletableFuture[] cfs = taskList
                    .stream()
                    .map(object -> CompletableFuture.supplyAsync(() -> calc(object), exs).thenApply(h -> Integer.toString(h))
                            .whenComplete((v, e) -> {// �����ȡ�����������˳�򣬴˴����뼴��
                                        System.out.println("����" + v + "���!result=" + v + "���쳣 e=" + e + "," + new Date());
                                        list2.add(v);
                                    })).toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(cfs).join();// ��װ���޷���ֵ�������Լ�whenComplete()��ȡ
            */
            System.out.println("list2=" + list2 + "list=" + list + ",��ʱ=" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();
        }
    }

    public static Integer calc(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(3000);// ����1��ʱ3��
            } else if (i == 5) {
                Thread.sleep(5000);// ����5��ʱ5��
            } else {
                Thread.sleep(1000);// ���������ʱ1��
            }
            System.out.println("task�̣߳�" + Thread.currentThread().getName() + "����i=" + i + ",��ɣ�+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 
     * @Description ��϶��CompletableFutureΪһ��CompletableFuture,����������ȫ����ɣ���Ϻ������Ż���ɡ�������ֵ����ֱ��get.
     */
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        // 1.����һ����CompletableFuture����������Ϊ�������list size
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        // 2.��ʽ��ÿ��������join������ת��Ϊlist������CompletableFuture����ӽ��
        return allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.<T> toList()));
    }

    /**
     * 
     * @Description Stream��ʽ����futuresת����һ��CompletableFuture,����������ȫ����ɣ���Ϻ������Ż���ɡ�������ֵ����ֱ��get.
     * 
     */
    public static <T> CompletableFuture<List<T>> sequence(Stream<CompletableFuture<T>> futures) {
        List<CompletableFuture<T>> futureList = futures.filter(f -> f != null).collect(Collectors.toList());
        return sequence(futureList);
    }
}
