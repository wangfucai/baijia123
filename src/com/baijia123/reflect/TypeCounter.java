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
         * Class.isAssignableFrom()是用来判断一个类Class1和另一个类Class2是否相同或是另一个类的子类或接口。 
         * 格式为： Class1.isAssignableFrom(Class2) 调用者和参数都是java.lang.Class类型。
         * instanceof是用来判断一个对象实例是否是一个类或接口的或其子类子接口的实例。
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
