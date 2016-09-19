package com.baijia123.lazyinitial;

public class SafeLazyInitialization {
    private static Resource resource;

    //采用synchronized决定了这个规则仅适用于在构造时的状态，如果对象是可变的，那么在读线程和写线程之间
    //仍然需要通过同步来确保随后的修改操作是可见的，以及避免数据破坏
    public synchronized static Resource getInstance() {
        if (resource == null) {
            resource = new Resource();
        }
        return resource;
    }
}
