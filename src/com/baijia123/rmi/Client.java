package com.baijia123.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Client {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String url = "rmi://localhost/";
        try {

            Context namingContext = new InitialContext();
            //����ָ���Ķ��� ���ҵ������������Ӧ�ķ��������
            IService service = (IService) namingContext.lookup(url + "service02");
            Class stubClass = service.getClass();
            System.out.println(service + "��" + stubClass.getName() + "��ʵ��");
            //��ñ��״����ʵ�ֵĽӿ�����
            Class[] interfaces = stubClass.getInterfaces();
            for (Class c : interfaces) {
                System.out.println("�����ʵ����" + c.getName() + "�ӿ�!");
            }
            System.out.println(service.service("���ã�"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
