package com.baijia123.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author WangFuCai
 *
 *Զ�̶������ʵ��java.rmi.server.UniCastRemoteObject�࣬�������ܱ�֤�ͻ��˷���
 *���Զ�̶���ʱ����Զ�̶��󽫻�������һ��������Socket����ʽ������ͻ��ˣ���ʱ��
 *��������õ����������Ϊ������������������˱����Ѵ��ڵ�Զ�̶������֮Ϊ���Ǽܡ�����
 *ʵ��ʱ�Ĵ���ǿͻ��˵�һ������������������˵�ͨ�ţ����Ǽ�Ҳ����Ϊ�Ƿ������˵�
 *һ���������ڽ��տͻ��˵�����֮�����Զ�̷�������Ӧ�ͻ��˵�����
 *
 */
public class ServiceImpl extends UnicastRemoteObject implements IService {
    
    private String name;

    protected ServiceImpl(String name) throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
        this.name = name;
    }

    /**
     * 
     */
    private static final long serialVersionUID = -2123452281868371937L;

    @Override
    public String service(String content) throws RemoteException {
        // TODO Auto-generated method stub
        return "service >> " + content;
    }

}
