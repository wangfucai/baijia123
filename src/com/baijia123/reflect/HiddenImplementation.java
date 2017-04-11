package com.baijia123.reflect;

import java.lang.reflect.Method;

public class HiddenImplementation {
    public static void main(String[] args) throws Exception {
        TestInterface a = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        callHiddenMethod(a, "g");
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }
    
    public static void callHiddenMethod(Object a, String methondName) throws Exception {
        Method g = a.getClass().getDeclaredMethod(methondName, null);
        g.setAccessible(true);
        g.invoke(a, null);
    }
}
