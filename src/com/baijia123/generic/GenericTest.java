package com.baijia123.generic;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

public class GenericTest {
	public List<String> list = new LinkedList<String>();
	
	public static void main(String[] args) throws SecurityException,NoSuchFieldException{
		ParameterizedType pt = (ParameterizedType) GenericTest.class.getField("list").getGenericType();
		System.out.println(pt.getActualTypeArguments().length);
		System.out.println(pt.getActualTypeArguments()[0]);
		System.out.println(GenericTest.class.getField("list").getType().getName());
	}
	
	public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
	    T[] arr = new Gson().fromJson(s, clazz);
	    return Arrays.asList(arr);
	}
}
