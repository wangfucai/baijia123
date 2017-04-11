package com.baijia123.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectCase {

    static class Proxy {
        public void run() {
            System.out.println("run");
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        // TODO Auto-generated method stub
        Proxy target = new Proxy();
        Method method = target.getClass().getDeclaredMethod("run");
        method.invoke(target);
    }

}
