package com.baijia123.lazyinitial;

//使用延迟初始化占位(Holder)类模式使用一个专门的类来初始化Resource。JVM将推迟ResourceHolder的初始化
//操作，知道开始使用这个类时才初始化，并且由于通过一个静态初始化来初始化Resource，因为不需要额外的同步。
//当任何一个线程第一次调用getRerouce时，都会使ResourceHolder被加载和初始化，此时静态初始化器将执行
//Resource的初始化操作
public class ResourceFactory {
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getResource() {
        return ResourceHolder.resource;
    }
}
