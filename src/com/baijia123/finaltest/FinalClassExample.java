package com.baijia123.finaltest;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 要创建不可变类，要实现下面几个步骤： 
 * 1.将类声明为final，所以它不能被继承 
 * 2.将所有的成员声明为私有的，这样就不允许直接访问这些成员 
 * 3.对变量不要提供setter方法 
 * 4.将所有可变的成员声明为final，这样只能对它们赋值一次 
 * 5.通过构造器初始化所有成员，进行深拷贝(deep copy) 
 * 6.在getter方法中，不要直接返回对象本身，而是克隆对象，并返回对象的拷贝
 * 
 * 下面总结了一些使用final关键字的好处
 * 1.final关键字提高了性能。JVM和Java应用都会缓存final变量。
 * 2.final变量可以安全的在多线程环境下进行共享，而不需要额外的同步开销。
 * 3.使用final关键字，JVM会对方法、变量及类进行优化。
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
     * 实现浅拷贝(shallow copy)的构造器
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
     * 实现深拷贝(deep copy)的构造器
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
