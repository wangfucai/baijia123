package com.baijia123.concurrent.cow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * ��һ��ʼ��Ҷ��ڹ���ͬһ�����ݣ���ĳ������Ҫ�޸�������ݵ�ʱ�򣬲Ż�����������Copy��ȥ�γ�һ���µ�����Ȼ���ٸģ�����һ����ʱ�������.
 * CopyOnWrite������дʱ���Ƶ�����
 * 
 * CopyOnWrite�����кܶ��ŵ㣬����ͬʱҲ�����������⣬���ڴ�ռ�����������һ�������� 1)�ڴ�ռ�����⡣��ΪCopyOnWrite��дʱ���ƻ��ƣ������ڽ���д������ʱ���ڴ����ͬʱפ������������ڴ棬
 * �ɵĶ������д��Ķ���ע��:�ڸ��Ƶ�ʱ��ֻ�Ǹ�������������ã�ֻ����д��ʱ��ᴴ���¶�����ӵ�������� ���������Ķ�����ʹ�ã����������ݶ����ڴ棩�������Щ����ռ�õ��ڴ�Ƚϴ󣬱���˵200M���ң� ��ô��д��100M���ݽ�ȥ���ڴ�ͻ�ռ��300M����ô���ʱ����п������Ƶ����Yong
 * GC��Full GC�� ֮ǰ����ϵͳ��ʹ����һ����������ÿ��ʹ��CopyOnWrite���Ƹ��´���������ÿ��15���Full GC��Ӧ����Ӧʱ��Ҳ��֮�䳤�� ����ڴ�ռ�����⣬����ͨ��ѹ�������е�Ԫ�صķ��������ٴ������ڴ����ģ����磬���Ԫ��ȫ��10���Ƶ����֣�
 * ���Կ��ǰ���ѹ����36���ƻ�64���ơ����߲�ʹ��CopyOnWrite��������ʹ�������Ĳ�����������ConcurrentHashMap��
 * 
 * 2)����һ�������⡣CopyOnWrite����ֻ�ܱ�֤���ݵ�����һ���ԣ����ܱ�֤���ݵ�ʵʱһ���ԡ� ���������ϣ��д��ĵ����ݣ������ܶ������벻Ҫʹ��CopyOnWrite������
 * 
 * @author WangFuCai
 *
 */
public class CopyOnWriteArrayListTest {

    /**
     * �ӽ�������׾Ϳ�������hashcode�仯�˶�Σ� * ˵����list�Ѿ�����ԭ����list�����ˡ� ��˵����CopyOnWriteArrayList���add������ִ�е�ʱ��ȷʵ���޸���list���������
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        List<String> a = new ArrayList<String>();
        a.add("a");
        a.add("b");
        a.add("c");
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>(a);
        //���е�ʱ��ͻ��׳�java.util.ConcurrentModificationException����������Ϊ���߳��ڱ���list��ʱ�����߳�����list�����Ԫ�ء�
        //final ArrayList<String> list = new ArrayList<String>(a);
        Thread t = new Thread(new Runnable() {
            int count = -1;

            @Override
            public void run() {
                while (true) {
                    list.add(count++ + "");
                }
            }
        });
        t.setDaemon(true);
        t.start();
        Thread.currentThread().sleep(3);
        for (String s : list) {
            System.out.println(list.hashCode());
            System.out.println(s);
        }
    }

}
