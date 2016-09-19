package com.baijia123.lazyinitial;

//通过提前初始化，避免每次调用SafeLazyInitialization中的getInstance时所产生的同步开销。
//通过将这项技术和JVM的延迟加载机制结合起来，可以形成一种延迟初始化技术，从而在常见的代码
//路径上不需要同步
public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getResource() {
        return resource;
    }
}
