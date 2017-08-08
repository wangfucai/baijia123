package com.baijia123.proxy.jdk;

import java.lang.reflect.Proxy;

public class Demo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 设置此系统属性,以查看代理类文件
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.out.println(Proxy.getProxyClass(Subject.class.getClassLoader(), Subject.class));  

        // 创建真实对象
        Subject subj = new SimpleSubject();
        subj.pub("name", "kevin.fan");
        subj.sub("name");

        // 创建代理对象
        Subject proxy = SubjectProxyFactory.getSubject(subj);
        proxy.pub("hobby", "r&b music");
        proxy.sub("name");
    }

}
