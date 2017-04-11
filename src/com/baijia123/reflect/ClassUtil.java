package com.baijia123.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
    public static void printClassMethodMessage(Object obj) {
        Class c = obj.getClass();
        System.out.println("���������:" + c.getName());
        Method[] ms = c.getMethods();
        for (int i = 0; i < ms.length; i++) {
            Class returnType = ms[i].getReturnType();
            System.out.println(returnType.getName() + " ");
            System.out.println(ms[i].getName() + "(");
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class class1 : paramTypes) {
                System.out.println(class1.getName() + ",");
            }
            System.out.println(")");
        }
    }

    public static void printFieldMessage(Object obj) {
        Class c = obj.getClass();
        /**
         * getFields()���ĳ��������еĹ�����public�����ֶΣ��������ࡣ 
         * getDeclaredFields()���ĳ����������������ֶΣ�������public��private��proteced�����ǲ���������������ֶΡ�
         * ͬ�����ƵĻ���getConstructors()��getDeclaredConstructors()��
         * getMethods()��getDeclaredMethods()��
         */
        //Field[] fs = c.getFields();
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            Class fieldType = field.getType();
            String typeName = fieldType.getName();
            String fieldName = field.getName();
            System.out.println(typeName+" "+fieldName);
        }        
    }
    
    public static void printConMessage(Object obj){
        Class c = obj.getClass();
        Constructor[] cs = c.getDeclaredConstructors();
        for (Constructor constructor : cs) {
            System.out.print(constructor.getName()+"(");
            Class[] paramTypes = constructor.getParameterTypes();
            for(Class cls1 : paramTypes) {
                System.out.print(cls1.getName()+",");
            }
            System.out.println(")");
        }
    }

    public static void main(String[] args) {
        // ClassUtil.printClassMethodMessage(Pet.class);
       // ClassUtil.printFieldMessage(Pet.class);
        ClassUtil.printConMessage(Pet.class);
        /**
         * try{
         *      Class cl= Class.forName(args[0]);
         * //ͨ�������ͣ���������Ķ���
         *      cl.newInstance();
         * }catch(Exception e){
         *      e.printStackTrace();
         *      }
         */
    }
}
