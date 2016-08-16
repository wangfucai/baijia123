package com.baijia123.proxy;

/**
 * ����һ�������ࣨ��ǿCountImplʵ���ࣩ ---���ھ�̬����
 * 
 * @author
 * 
 */
public class CountProxy implements Count {

    private CountImpl countImpl;

    /**
     * ����Ĭ�Ϲ�����
     * 
     * @param countImpl
     */
    public CountProxy(CountImpl countImpl) {
        this.countImpl = countImpl;
    }

    @Override
    public void queryCount() {
        // TODO Auto-generated method stub
        System.out.println("������֮ǰ");
        // ����ί����ķ���;
        countImpl.queryCount();
        System.out.println("������֮��");
    }

    @Override
    public void updateCount() {
        // TODO Auto-generated method stub
        System.out.println("������֮ǰ");
        // ����ί����ķ���;
        countImpl.updateCount();
        System.out.println("������֮��");
    }

}
