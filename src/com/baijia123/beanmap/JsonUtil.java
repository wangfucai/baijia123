package com.baijia123.beanmap;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    //protected static Gson JSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    protected static Gson JSON = new GsonBuilder().create();

    public static String beanToString(Object obj) throws Exception {
        return JSON.toJson(obj);
    }

    public static Object stringToBean(String str, Class<?> clazz) throws Exception {
        return JSON.fromJson(str, clazz);
    }

    public static <T> T stringToBean(String str, Type type) {
        return JSON.fromJson(str, type);
    }
    
    public static void main(String[] args) throws Exception {
        User user = new User("wang_tale", "ÄÐ", "¸£ÖÝ", "13698562365");
        System.out.println(beanToString(user));
    }
}
