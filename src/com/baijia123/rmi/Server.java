package com.baijia123.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import com.baijia123.rmi.IService;

public class Server {
    public static void main(String[] args) {
        try {
            // 实例化实现了IService接口的远程服务ServiceImpl对象
            IService service02 = new ServiceImpl("service02");
            // 初始化命名空间
            Context namingContext = new InitialContext();
            // 将名称绑定到对象,即向命名空间注册已经实例化的远程服务对象
            namingContext.rebind("rmi://localhost/service02", service02);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("服务器向命名表注册了一个远程服务对象！");
    }
}
