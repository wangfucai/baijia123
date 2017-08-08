package com.baijia123.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectPerson {

    public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        ClassLoader classLoader = ReflectPerson.class.getClassLoader();
        System.out.println("1 classLoader = " + classLoader);
        classLoader = Class.class.getClassLoader();
        System.out.println("2 classLoader = " + classLoader);
        classLoader = new ReflectPerson().getClass().getClassLoader();
        System.out.println("3 classLoader = " + classLoader);
        Class clazz = classLoader.loadClass("com.baijia123.reflect.Person");
        System.out.println("4 clazz = " + clazz);
        clazz = Class.forName("com.baijia123.reflect.Person");
        System.out.println("5 clazz = " + clazz);

        Constructor[] constructors = clazz.getConstructors();
        int i = 6;
        for (Constructor c : constructors) {
            System.out.println(i + " " + c);
            i++;
        }

        Constructor cttEmpty = clazz.getConstructor(new Class[] {});
        Object objEmpty = cttEmpty.newInstance(new Object[] {});

        Constructor cttParamsStrInt = clazz.getConstructor(new Class[] { String.class, Integer.class });
        Person p = (Person) cttParamsStrInt.newInstance(new Object[] { "wang_tale", 31 });
        System.out.println("8 " + p.getName() + " = " + p.getAge());

    }

}
