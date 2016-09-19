package com.baijia123.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String url = "rmi://localhost/";
        try {

            Context namingContext = new InitialContext();
            //检索指定的对象。 即找到服务器端相对应的服务对象存根
            IService service = (IService) namingContext.lookup(url + "service02");
            Class stubClass = service.getClass();
            System.out.println(service + "是" + stubClass.getName() + "的实例");
            //获得本底存根已实现的接口类型
            Class[] interfaces = stubClass.getInterfaces();
            for (Class c : interfaces) {
                System.out.println("存根类实现了" + c.getName() + "接口!");
            }
            System.out.println(service.service("您好！"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
