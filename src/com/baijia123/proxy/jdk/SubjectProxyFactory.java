package com.baijia123.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SubjectProxyFactory {
    public static Subject getSubject(final Subject realSubject) {
        return (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), new Class[] { Subject.class }, new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("\naction before method invocation....");
                Object retVal = method.invoke(realSubject, args);
                System.out.println("action after method invocation....\n");
                return retVal;
            }
        });
    }
}
