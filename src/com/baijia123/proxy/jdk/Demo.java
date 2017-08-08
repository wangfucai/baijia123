package com.baijia123.proxy.jdk;

import java.lang.reflect.Proxy;

public class Demo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // ���ô�ϵͳ����,�Բ鿴�������ļ�
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.out.println(Proxy.getProxyClass(Subject.class.getClassLoader(), Subject.class));  

        // ������ʵ����
        Subject subj = new SimpleSubject();
        subj.pub("name", "kevin.fan");
        subj.sub("name");

        // �����������
        Subject proxy = SubjectProxyFactory.getSubject(subj);
        proxy.pub("hobby", "r&b music");
        proxy.sub("name");
    }

}
