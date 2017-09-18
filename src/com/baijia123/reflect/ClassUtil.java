package com.baijia123.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
         * getFields()���ĳ��������еĹ�����public�����ֶΣ��������ࡣ getDeclaredFields()���ĳ����������������ֶΣ�������public��private��proteced�����ǲ���������������ֶΡ�
         * ͬ�����ƵĻ���getConstructors()��getDeclaredConstructors()�� getMethods()��getDeclaredMethods()��
         */
        // Field[] fs = c.getFields();
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            Class fieldType = field.getType();
            String typeName = fieldType.getName();
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    @SuppressWarnings("rawtypes")
    public static void printConMessage(Object obj) {
        Class c = obj.getClass();
        Constructor[] cs = c.getDeclaredConstructors();
        for (Constructor constructor : cs) {
            System.out.print(constructor.getName() + "(");
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class cls1 : paramTypes) {
                System.out.print(cls1.getName() + ",");
            }
            System.out.println(")");
        }
    }

    public static void printInterfaceMessage(Object obj) {
        System.out.println("printInterfaceMessage");
        Class c = obj.getClass();
        
        Type[] ts = c.getSuperclass().getGenericInterfaces();
        //Type[] ts = c.getGenericInterfaces();
        for (int i = 0 ; i < ts.length; i ++) {
            if(ts[i] instanceof ParameterizedType) {
                
                ParameterizedType p = (ParameterizedType)ts[i];
                System.out.println("getTypeName = " + ts[i].getTypeName());
                Type[] params = p.getActualTypeArguments();
                System.out.println("getOwnerType = " + p.getOwnerType());
                for (int index = 0; index < params.length; index++) {
                    System.out.println("getActualTypeArguments = " + params[index]);
                }
                
                System.out.println("ParameterizedType = " + p.getRawType().toString());
            }
            System.out.println(ts[i].toString());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //ClassUtil.printClassMethodMessage(Pet.class);
        // ClassUtil.printFieldMessage(Pet.class);
        // ClassUtil.printConMessage(Pet.class);
        ClassUtil.printInterfaceMessage(new Pet("123"));
        //System.out.println(new Pet("123").getClass().getGenericSuperclass());
        
        //Class c = Class.forName("java.lang.Long");
        /*
        Class c = Pet.class;
        Type types1[] = c.getGenericInterfaces();
        System.out.println("1������ֱ��ʵ�ֵĽӿڣ�");
        for (int i = 0; i < types1.length; i++) {
            System.out.println(types1[i].toString());
        }*/
        /**
         * try{ Class cl= Class.forName(args[0]); //ͨ�������ͣ���������Ķ��� cl.newInstance(); }catch(Exception e){ e.printStackTrace(); }
         */
    }
}
