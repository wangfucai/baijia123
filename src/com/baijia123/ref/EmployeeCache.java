package com.baijia123.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

public class EmployeeCache {
    static private EmployeeCache cache;// һ��Cacheʵ��
    private Hashtable employeeRefs;// ����Chche���ݵĴ洢
    private ReferenceQueue q;// ����Reference�Ķ���

    // �̳�SoftReference��ʹ��ÿһ��ʵ�������п�ʶ��ı�ʶ��
    // ���Ҹñ�ʶ������HashMap�ڵ�key��ͬ��
    private class EmployeeRef extends SoftReference {
        private String _key = "";

        public EmployeeRef(Employee em, ReferenceQueue q) {
            super(em, q);
            _key = em.getId();
        }
    }

    // ����һ��������ʵ��
    private EmployeeCache() {
        employeeRefs = new Hashtable();
        q = new ReferenceQueue();
    }

    // ȡ�û�����ʵ��
    public static EmployeeCache getInstance() {
        if (cache == null) {
            cache = new EmployeeCache();
        }
        return cache;
    }

    // �������õķ�ʽ��һ��Employee�����ʵ���������ò����������
    private void cacheEmployee(Employee em) {
        cleanCache();// �����������
        EmployeeRef ref = new EmployeeRef(em, q);
        employeeRefs.put(em.getId(), ref);
    }

    // ������ָ����ID�ţ����»�ȡ��ӦEmployee�����ʵ��
    public Employee getEmployee(String ID) {
        Employee em = null;
        // �������Ƿ��и�Employeeʵ���������ã�����У�����������ȡ�á�
        if (employeeRefs.containsKey(ID)) {
            EmployeeRef ref = (EmployeeRef) employeeRefs.get(ID);
            em = (Employee) ref.get();
        }
        // ���û�������ã����ߴ��������еõ���ʵ����null�����¹���һ��ʵ����
        // �����������½�ʵ����������
        if (em == null) {
            em = new Employee(ID);
            System.out.println("Retrieve From EmployeeInfoCenter. ID=" + ID);
            this.cacheEmployee(em);
        }
        return em;
    }

    // �����Щ�������õ�Employee�����Ѿ������յ�EmployeeRef����
    private void cleanCache() {
        EmployeeRef ref = null;
        while ((ref = (EmployeeRef) q.poll()) != null) {
            //��������Ƴ�����Զ�����¼����
            employeeRefs.remove(ref._key);
        }
    }

    // ���Cache�ڵ�ȫ������
    public void clearCache() {
        cleanCache();
        employeeRefs.clear();
        System.gc();
        System.runFinalization();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EmployeeCache ec = EmployeeCache.getInstance();
        Employee e = ec.getEmployee("123");
        System.out.println("Employee id = " + e.getId());
    }

}
