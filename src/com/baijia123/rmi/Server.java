package com.baijia123.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import com.baijia123.rmi.IService;

public class Server {
    public static void main(String[] args) {
        try {
            // ʵ����ʵ����IService�ӿڵ�Զ�̷���ServiceImpl����
            IService service02 = new ServiceImpl("service02");
            // ��ʼ�������ռ�
            Context namingContext = new InitialContext();
            // �����ư󶨵�����,���������ռ�ע���Ѿ�ʵ������Զ�̷������
            namingContext.rebind("rmi://localhost/service02", service02);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("��������������ע����һ��Զ�̷������");
    }
}
