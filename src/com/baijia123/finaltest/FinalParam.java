package com.baijia123.finaltest;

import java.util.ArrayList;
import java.util.List;

/**
 * final�������Ҫ������3�����ã�
 * 1.final����classǰ���ܾ����౻�̳�;
 * 2.final������ķ�����ǰ���ܾ�������д�÷�����
 * 3.final����������ǰ��һ����������ֵ�����ö��󣬾���Ҳ�������¸�ֵ��������������
 * @author WangFuCai
 *
 */
public class FinalParam {
    
    public void testFinal(List<Integer> list) {
        for(final Integer i : list) {
            //System.out.println(i++);//(1)��ֹ����ʱ �޸�
            System.out.println(i);
        }
    }
    
    public void testFinal2(final List<Integer> list) {
        //list = new ArrayList<>();(2)//��ֹ��������Ӧ��
    }
}
