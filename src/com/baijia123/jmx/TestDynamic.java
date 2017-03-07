package com.baijia123.jmx;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;
/**
 * JMX是用于管理和监控JAVA应用的接口规范。同时具有以下特点：
 * 1)通过JMX的”MBeans“来读取数据
 * 2)可以读取和写入MBean的属性
 * 3)可以执行MBean的方法
 * 通过JMX，可以轻松获取JVM的各项数据值，例如GC执行次数，当前JVM使用的GC类型，内存占用，GC暂停的时间等。
 * 在此之上，Tomcat对外暴露了许多有用的数据，可以通过JMX获取具体通道的连接数据，
 * 请求数，响应数，失败的请求有多少，线程池的数据，JNDI的数据等等。
 * 一个大系统中，各内部模块系统之间的基于接口方式的互相调用和管理，使用jmx是最佳方案．
 * 带来的好处是
 * 1.面向接口，远程调用对于开发人员是透明的，模块在调用jmx接口时，与调用本地方法几乎相同．
 * 2.可视化的管理界面,通过Jconsole等jmx客户端，可以实时监控系统，并且可实时调用方法进行某些操作
 * 典型应用场景：
 * 某聊天系统，一台服务器作为 在线用户列表服务器 A1，　ｎ台服务器为用户提供聊天业务处理 N1 ,N2,N3...，
 * 一台服务器作为后台管理系统A2．　
 * 系统管理员现在进行下面这样一个操作，察看某用户是否在线，找到该用户，发现其在线，则将该用户加入黑名单，并踢下线．
 * 对应的jmx接口可以由以下几个：　
 * A1为A2提供查询在线用户ｊｍｘ接口，加入黑名单接口，kickout接口，
 * A1为N1..等服务器提供以下接口：　注册业务服务器，添加在线用户．查找黑名单用户
 * N1...到N3为A1提供kickout接口．
 * 因此在上面的踢下线操作，则由用户在A2的ｗｅｂ界面发出，交由A1执行，A1记录黑名单之后，再找到用户所在业务服务器调用N1提供的接口让用户下线．
 * 以上情形是在生产环境下的部署，而在开发工作，则可以将A1,A2,N...N3等功能合并在一个应用中调试．　由于使用的是ｊｍｘ接口，在本地调试合并之后，可以直接调用应用内部接口方法．
 * 这样借助JMX实现的应用模块的灵活组装与拆分，使得系统的可以根据负载需要，根据性能情况，灵活的拆分和整合部署分布式的应用．
 * 替代方案，选择webservice,xmlrpc等，但是这些都需要手工编写或用工具生成大量的代码来辅助完成接口间的java对象序列化　。
 * 经典JMX案例：
 * 1．JBoss．使用ｊｍｘ管理内部的各个service。
 * 2．基于java的开源网管软件 Hyperic  HQ ，通过jmx与各被管理资源进行通讯和信息采集．
 * JMX是一个管理的框架。
 * 当我们想使用JMX的时候，就要问，我们的系统当中有需要监控管理的资源或者对象吗？实事求是一点，我们不能为了想使用一个高端的技术，就歪曲系统的本来面目。
 * 如果第一个问题是肯定的，接下来就是看这些资源是否有生命周期。
 * 经典案例：jboss就是将所有可部署的组件作为资源来管理，这些组建都有其生命周期。
 * 这个理念甚至延伸到了其系统内部，将其内部的服务作为组件纳入到JMX中来，成就了jboss基于jmx的微内核系统。
 * @author WangFuCai
 *
 */
public class TestDynamic implements DynamicMBean {

    private String name = "iamzhongyong";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println(name);
    }

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        // TODO Auto-generated method stub
        if (attribute == null) {
            throw new AttributeNotFoundException();
        }
        if ("name".equalsIgnoreCase(attribute)) {
            return getName();
        }
        throw new AttributeNotFoundException();
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException,
            ReflectionException {
        // TODO Auto-generated method stub
        String name = attribute.getName();
        Object value = attribute.getValue();
        if ("name".equalsIgnoreCase(name)) {
            this.setName(String.valueOf(value));
            return;
        }
        throw new AttributeNotFoundException();
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        // TODO Auto-generated method stub
        if ("printName".equals(actionName)) {
            printName();
        }
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        // TODO Auto-generated method stub
        MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[] { new MBeanAttributeInfo("name", "String", "缓存名称", true, true, false) };
        MBeanOperationInfo opers[] = { new MBeanOperationInfo("printName", "print", null, "void", MBeanOperationInfo.ACTION) };

        MBeanInfo info = new MBeanInfo(this.getClass().getName(), "TestDynamic", dAttributes, null, opers, null);
        return info;
    }

}
