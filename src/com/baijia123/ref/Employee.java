package com.baijia123.ref;

public class Employee {
    private String id;// ��Ա�ı�ʶ����
    private String name;// ��Ա����
    private String department;// �ù�Ա���ڲ���
    private String Phone;// �ù�Ա��ϵ�绰
    private int salary;// �ù�Աн��
    private String origin;// �ù�Ա��Ϣ����Դ

    // ���췽��
    public Employee(String id) {
        this.id = id;
        getDataFromlnfoCenter();
    }

    // �����ݿ���ȡ�ù�Ա��Ϣ
    private void getDataFromlnfoCenter() {
        // �����ݿ⽨�����Ӿ���ѯ�ù�Ա����Ϣ������ѯ�����ֵ
        // ��name��department��plone��salary�ȱ���
        // ͬʱ��origin��ֵΪ"From DataBase"
    }

    public String getId() {
        return id;
    }

    
    
}
