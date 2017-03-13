package com.baijia123.finaltest;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Ҫ�������ɱ��࣬Ҫʵ�����漸�����裺 
 * 1.��������Ϊfinal�����������ܱ��̳� 
 * 2.�����еĳ�Ա����Ϊ˽�еģ������Ͳ�����ֱ�ӷ�����Щ��Ա 
 * 3.�Ա�����Ҫ�ṩsetter���� 
 * 4.�����пɱ�ĳ�Ա����Ϊfinal������ֻ�ܶ����Ǹ�ֵһ�� 
 * 5.ͨ����������ʼ�����г�Ա���������(deep copy) 
 * 6.��getter�����У���Ҫֱ�ӷ��ض��������ǿ�¡���󣬲����ض���Ŀ���
 * 
 * �����ܽ���һЩʹ��final�ؼ��ֵĺô�
 * 1.final�ؼ�����������ܡ�JVM��JavaӦ�ö��Ỻ��final������
 * 2.final�������԰�ȫ���ڶ��̻߳����½��й���������Ҫ�����ͬ��������
 * 3.ʹ��final�ؼ��֣�JVM��Է�����������������Ż���
 * @author WangFuCai
 *
 */
public final class FinalClassExample {
    private final int id;
    private final String name;
    private final HashMap testMap;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashMap getTestMap() {
        // return testMap;
        return (HashMap) testMap.clone();
    }

    /**
     * ʵ��ǳ����(shallow copy)�Ĺ�����
     * 
     * @param id
     * @param name
     * @param testMap
     */
    /**
    public FinalClassExample(int id, String name, HashMap testMap) {
        super();
        this.id = id;
        this.name = name;
        this.testMap = testMap;
    }**/

    /**
     * ʵ�����(deep copy)�Ĺ�����
     * 
     * @param id
     * @param name
     * @param testMap
     */
    public FinalClassExample(int id, String name, HashMap testMap) {
        super();
        this.id = id;
        this.name = name;
        HashMap tempMap = new HashMap();
        String key;
        Iterator it = testMap.keySet().iterator();
        while (it.hasNext()) {
            key = (String) it.next();
            tempMap.put(key, testMap.get(key));
        }
        this.testMap = tempMap;
    }

    public static void main(String[] args) {
        HashMap h1 = new HashMap();
        h1.put("1", "first");
        h1.put("2", "second");

        String s = "original";

        int i = 10;

        FinalClassExample ce = new FinalClassExample(i, s, h1);

        // Lets see whether its copy by field or reference
        System.out.println(s == ce.getName());
        System.out.println(h1 == ce.getTestMap());
        // print the ce values
        System.out.println("ce id:" + ce.getId());
        System.out.println("ce name:" + ce.getName());
        System.out.println("ce testMap:" + ce.getTestMap());
        // change the local variable values
        i = 20;
        s = "modified";
        h1.put("3", "third");
        // print the values again
        System.out.println("ce id after local variable change:" + ce.getId());
        System.out.println("ce name after local variable change:" + ce.getName());
        System.out.println("ce testMap after local variable change:" + ce.getTestMap());

        HashMap hmTest = ce.getTestMap();
        hmTest.put("4", "new");

        System.out.println("ce testMap after changing variable from accessor methods:" + ce.getTestMap());
    }

}
