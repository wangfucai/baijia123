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
 * JMX�����ڹ���ͼ��JAVAӦ�õĽӿڹ淶��ͬʱ���������ص㣺
 * 1)ͨ��JMX�ġ�MBeans������ȡ����
 * 2)���Զ�ȡ��д��MBean������
 * 3)����ִ��MBean�ķ���
 * ͨ��JMX���������ɻ�ȡJVM�ĸ�������ֵ������GCִ�д�������ǰJVMʹ�õ�GC���ͣ��ڴ�ռ�ã�GC��ͣ��ʱ��ȡ�
 * �ڴ�֮�ϣ�Tomcat���Ⱪ¶��������õ����ݣ�����ͨ��JMX��ȡ����ͨ�����������ݣ�
 * ����������Ӧ����ʧ�ܵ������ж��٣��̳߳ص����ݣ�JNDI�����ݵȵȡ�
 * һ����ϵͳ�У����ڲ�ģ��ϵͳ֮��Ļ��ڽӿڷ�ʽ�Ļ�����ú͹���ʹ��jmx����ѷ�����
 * �����ĺô���
 * 1.����ӿڣ�Զ�̵��ö��ڿ�����Ա��͸���ģ�ģ���ڵ���jmx�ӿ�ʱ������ñ��ط���������ͬ��
 * 2.���ӻ��Ĺ������,ͨ��Jconsole��jmx�ͻ��ˣ�����ʵʱ���ϵͳ�����ҿ�ʵʱ���÷�������ĳЩ����
 * ����Ӧ�ó�����
 * ĳ����ϵͳ��һ̨��������Ϊ �����û��б������ A1������̨������Ϊ�û��ṩ����ҵ���� N1 ,N2,N3...��
 * һ̨��������Ϊ��̨����ϵͳA2����
 * ϵͳ����Ա���ڽ�����������һ���������쿴ĳ�û��Ƿ����ߣ��ҵ����û������������ߣ��򽫸��û�������������������ߣ�
 * ��Ӧ��jmx�ӿڿ��������¼�������
 * A1ΪA2�ṩ��ѯ�����û������ӿڣ�����������ӿڣ�kickout�ӿڣ�
 * A1ΪN1..�ȷ������ṩ���½ӿڣ���ע��ҵ�����������������û������Һ������û�
 * N1...��N3ΪA1�ṩkickout�ӿڣ�
 * ���������������߲����������û���A2�ģ������淢��������A1ִ�У�A1��¼������֮�����ҵ��û�����ҵ�����������N1�ṩ�Ľӿ����û����ߣ�
 * ���������������������µĲ��𣬶��ڿ�������������Խ�A1,A2,N...N3�ȹ��ܺϲ���һ��Ӧ���е��ԣ�������ʹ�õ��ǣ����ӿڣ��ڱ��ص��Ժϲ�֮�󣬿���ֱ�ӵ���Ӧ���ڲ��ӿڷ�����
 * ��������JMXʵ�ֵ�Ӧ��ģ��������װ���֣�ʹ��ϵͳ�Ŀ��Ը��ݸ�����Ҫ������������������Ĳ�ֺ����ϲ���ֲ�ʽ��Ӧ�ã�
 * ���������ѡ��webservice,xmlrpc�ȣ�������Щ����Ҫ�ֹ���д���ù������ɴ����Ĵ�����������ɽӿڼ��java�������л�����
 * ����JMX������
 * 1��JBoss��ʹ�ã��������ڲ��ĸ���service��
 * 2������java�Ŀ�Դ������� Hyperic  HQ ��ͨ��jmx�����������Դ����ͨѶ����Ϣ�ɼ���
 * JMX��һ������Ŀ�ܡ�
 * ��������ʹ��JMX��ʱ�򣬾�Ҫ�ʣ����ǵ�ϵͳ��������Ҫ��ع������Դ���߶�����ʵ������һ�㣬���ǲ���Ϊ����ʹ��һ���߶˵ļ�����������ϵͳ�ı�����Ŀ��
 * �����һ�������ǿ϶��ģ����������ǿ���Щ��Դ�Ƿ����������ڡ�
 * ���䰸����jboss���ǽ����пɲ���������Ϊ��Դ��������Щ�齨�������������ڡ�
 * ��������������쵽����ϵͳ�ڲ��������ڲ��ķ�����Ϊ������뵽JMX�������ɾ���jboss����jmx��΢�ں�ϵͳ��
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
        MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[] { new MBeanAttributeInfo("name", "String", "��������", true, true, false) };
        MBeanOperationInfo opers[] = { new MBeanOperationInfo("printName", "print", null, "void", MBeanOperationInfo.ACTION) };

        MBeanInfo info = new MBeanInfo(this.getClass().getName(), "TestDynamic", dAttributes, null, opers, null);
        return info;
    }

}
