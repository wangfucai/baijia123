package com.baijia123.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author WangFuCai
 *
 *远程对象必须实现java.rmi.server.UniCastRemoteObject类，这样才能保证客户端访问
 *获得远程对象时，该远程对象将会把自身的一个拷贝以Socket的形式传输给客户端，此时客
 *户端所获得的这个拷贝称为“存根”，而服务器端本身已存在的远程对象则称之为“骨架”。其
 *实此时的存根是客户端的一个代理，用于与服务器端的通信，而骨架也可认为是服务器端的
 *一个代理，用于接收客户端的请求之后调用远程方法来响应客户端的请求。
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
