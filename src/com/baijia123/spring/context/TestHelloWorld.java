package com.baijia123.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		HelloWorld bean = (HelloWorld) applicationContext.getBean("helloWorld");
		bean.say();
	}

}
