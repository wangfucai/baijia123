package com.baijia123.concurrent.cow;

import java.util.Map;


/**
 * CopyOnWrite�����������ڶ���д�ٵĲ������������������������������Ʒ��Ŀ�ķ��ʺ͸��³�����
 * ����������һ��������վ���û��������վ���������У�����ؼ����������ݣ�����ĳЩ�ؼ��ֲ�����������
 * ��Щ���ܱ������Ĺؼ��ֻᱻ����һ�����������У�������ÿ�����ϸ���һ�Ρ�
 * ���û�����ʱ�����鵱ǰ�ؼ����ڲ��ں��������У�����ڣ�����ʾ����������
 * 
 * 
 * @author WangFuCai
 *
 */
public class BlackListServiceImpl {

    private static CopyOnWriteMap<String, Boolean> blackListMap = new CopyOnWriteMap<String, Boolean>(1000);

    public static boolean isBlackList(String id) {
        return blackListMap.get(id) == null ? false : true;
    }

    public static void addBlackList(String id) {
        blackListMap.put(id, Boolean.TRUE);
    }

    /**
     * ������Ӻ�����
     *
     * @param ids
     */
    public static void addBlackList(Map<String, Boolean> ids) {
        blackListMap.putAll(ids);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BlackListServiceImpl.addBlackList("5");
        System.out.println(BlackListServiceImpl.isBlackList("5"));
        System.out.println(BlackListServiceImpl.isBlackList("4"));
    }

}
