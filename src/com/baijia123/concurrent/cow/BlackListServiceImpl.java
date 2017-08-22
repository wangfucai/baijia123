package com.baijia123.concurrent.cow;

import java.util.Map;


/**
 * CopyOnWrite并发容器用于读多写少的并发场景。比如白名单，黑名单，商品类目的访问和更新场景，
 * 假如我们有一个搜索网站，用户在这个网站的搜索框中，输入关键字搜索内容，但是某些关键字不允许被搜索。
 * 这些不能被搜索的关键字会被放在一个黑名单当中，黑名单每天晚上更新一次。
 * 当用户搜索时，会检查当前关键字在不在黑名单当中，如果在，则提示不能搜索。
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
     * 批量添加黑名单
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
