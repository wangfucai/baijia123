package com.baijia123.java8;

public interface Formula {
    double calculate(int a);
    
    //Java 8�������Ǹ��ӿ����һ���ǳ���ķ���ʵ�֣�ֻ��Ҫʹ�� default�ؼ��ּ��ɣ���������ֽ�����չ����
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
