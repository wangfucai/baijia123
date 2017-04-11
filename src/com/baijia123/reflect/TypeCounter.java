package com.baijia123.reflect;

import java.util.HashMap;
import java.util.Map;

public class TypeCounter extends HashMap<Class<?>, Integer> {

    /**
     * 
     */
    private static final long serialVersionUID = -6895709471874908867L;
    private Class<?> baseType;

    public TypeCounter(Class<?> baseType) {
        this.baseType = baseType;
    }

    public void count(Object obj) {
        Class<?> type = obj.getClass();
        if (!baseType.isAssignableFrom(type)) {
            throw new RuntimeException(obj + " incorrect type " + type + ", "
                    + "should be type or subtype of " + baseType);
        }
        countClass(type);
    }

    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        Class<?> superClass = type.getSuperclass();
        /**
         * Class.isAssignableFrom()�������ж�һ����Class1����һ����Class2�Ƿ���ͬ������һ����������ӿڡ� 
         * ��ʽΪ�� Class1.isAssignableFrom(Class2) �����ߺͲ�������java.lang.Class���͡�
         * instanceof�������ж�һ������ʵ���Ƿ���һ�����ӿڵĻ��������ӽӿڵ�ʵ����
         * clazz.isAssignableFrom(obj.getClass()) == clazz.isInstance(obj)
         */
        if (superClass != null && baseType.isAssignableFrom(superClass)) {
            countClass(superClass);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<Class<?>, Integer> pair : entrySet()) {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("} ");
        return result.toString();
    }
    
    public static void main(String[] args) {
        Cat cat = new Cat();
        TypeCounter tc = new TypeCounter(Pet.class);
        tc.count(cat);
        System.out.println(tc.toString());
    }

}
