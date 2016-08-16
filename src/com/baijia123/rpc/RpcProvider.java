package com.baijia123.rpc;

import com.baijia123.proxy.HelloService;
import com.baijia123.proxy.HelloServiceImpl;

public class RpcProvider {
    public static void main(String[] args) throws Exception{
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
