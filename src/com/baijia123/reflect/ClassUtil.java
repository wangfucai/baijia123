package com.baijia123.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
    public static void printClassMethodMessage(Object obj) {
        Class c = obj.getClass();
        System.out.println("类的名称是:" + c.getName());
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
         * getFields()获得某个类的所有的公共（public）的字段，包括父类。 
         * getDeclaredFields()获得某个类的所有申明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
         * 同样类似的还有getConstructors()和getDeclaredConstructors()，
         * getMethods()和getDeclaredMethods()。
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
         * //通过类类型，创建该类的对象
         *      cl.newInstance();
         * }catch(Exception e){
         *      e.printStackTrace();
         *      }
         */
    }
}
