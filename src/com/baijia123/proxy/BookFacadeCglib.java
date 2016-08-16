package com.baijia123.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * JDK的动态代理机制只能代理实现了接口的类，而不能实现接口的类就不能实现JDK的动态代理，
 * cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现
 * 增强，但因为采用的是继承，所以不能对final修饰的类进行代理
 * 
 * CGLib创建的动态代理对象性能比JDK创建的动态代理对象的性能高不少，但是CGLib在创建代理
 * 对象时所花费的时间却比JDK多得多，所以对于单例的对象，因为无需频繁创建对象，用CGLib合
 * 适，反之，使用JDK方式要更为合适一些
 */

/**
 * 使用cglib动态代理
 * 
 * @author
 * 
 */
public class BookFacadeCglib implements MethodInterceptor {

    private Object target;

    /**
     * 创建代理对象
     * 
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("事物开始");
        arg3.invokeSuper(arg0, arg2);
        System.out.println("事物结束");
        return null;
    }
}
