package com.baijia123.reflect;

public class Clazz {

    public static void main(String[] args) throws ClassNotFoundException {
        // TODO Auto-generated method stub
        String s = "test";
        
        //��ȡһ������ķ�����
        Class<?> clz = s.getClass();
        
        Class<?> clz2 = Class.forName("java.lang.String");
        
        Class<?> clz3 = String.class;
    }

}
