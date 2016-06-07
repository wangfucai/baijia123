package com.baijia123.beanmap;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapUtil {
	
	@SuppressWarnings("rawtypes")
	public static Map convertMap(Object bean) throws Exception {
		Class type = bean.getClass();
		Map<String, String> returnMap = new HashMap<String, String>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptor = beanInfo
				.getPropertyDescriptors();
		for (PropertyDescriptor p : propertyDescriptor) {
			String name = p.getName();
			if (!name.equals("class")) {
				Method readMethod = p.getReadMethod();
				// Object result = method.invoke(bean, new Object[0]);
				Object result = readMethod.invoke(bean, (Object[]) null);
				if (result != null) {
					returnMap.put(name, (String)result);
				} else {
					returnMap.put(name, "");
				}
			}
		}
		return returnMap;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object convertObject(Class type, Map map) throws Exception{
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object object = type.newInstance();
		PropertyDescriptor[] propertyDescriptor = beanInfo.getPropertyDescriptors(); 
		for(PropertyDescriptor p : propertyDescriptor){
			String name = p.getName();
			if(map.containsKey(name)){
				Object value = map.get(name);
				Object[] args = new Object[1];
				args[0] = value;
				p.getWriteMethod().invoke(object, args);
			}
		}
		return object;
	}

	public static void main(String args[]) throws Exception {
		User user = new User("tale", "男", "新华兴大厦", "13860683640");
		HashMap<Object, Object> returnMap = (HashMap<Object, Object>) ObjectMapUtil.convertMap(user);
		// Set<String, String> entrySet = returnMap.entrySet();

		for (Map.Entry e : returnMap.entrySet()) {
			System.out.println("key = " + e.getKey() + " value = "
					+ e.getValue());
		}
		User result = (User)ObjectMapUtil.convertObject(User.class, returnMap);
		System.out.println("result = " + result.toString());
	}
}
