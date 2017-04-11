package com.baijia123.reflect;

public class Clazz {

    public static void main(String[] args) throws ClassNotFoundException {
        // TODO Auto-generated method stub
        String s = "test";
        
        //获取一个对象的反射类
        Class<?> clz = s.getClass();
        
        Class<?> clz2 = Class.forName("java.lang.String");
        
        Class<?> clz3 = String.class;
    }

}
