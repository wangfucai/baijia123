package com.baijia123.lazyinitial;

//ͨ����ǰ��ʼ��������ÿ�ε���SafeLazyInitialization�е�getInstanceʱ��������ͬ��������
//ͨ�����������JVM���ӳټ��ػ��ƽ�������������γ�һ���ӳٳ�ʼ���������Ӷ��ڳ����Ĵ���
//·���ϲ���Ҫͬ��
public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getResource() {
        return resource;
    }
}
