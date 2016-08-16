package com.baijia123.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * JDK�Ķ�̬�������ֻ�ܴ���ʵ���˽ӿڵ��࣬������ʵ�ֽӿڵ���Ͳ���ʵ��JDK�Ķ�̬����
 * cglib���������ʵ�ִ���ģ�����ԭ���Ƕ�ָ����Ŀ��������һ�����࣬���������з���ʵ��
 * ��ǿ������Ϊ���õ��Ǽ̳У����Բ��ܶ�final���ε�����д���
 * 
 * CGLib�����Ķ�̬����������ܱ�JDK�����Ķ�̬�����������ܸ߲��٣�����CGLib�ڴ�������
 * ����ʱ�����ѵ�ʱ��ȴ��JDK��ö࣬���Զ��ڵ����Ķ�����Ϊ����Ƶ������������CGLib��
 * �ʣ���֮��ʹ��JDK��ʽҪ��Ϊ����һЩ
 */

/**
 * ʹ��cglib��̬����
 * 
 * @author
 * 
 */
public class BookFacadeCglib implements MethodInterceptor {

    private Object target;

    /**
     * �����������
     * 
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // �ص�����
        enhancer.setCallback(this);
        // �����������
        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("���￪ʼ");
        arg3.invokeSuper(arg0, arg2);
        System.out.println("�������");
        return null;
    }
}
