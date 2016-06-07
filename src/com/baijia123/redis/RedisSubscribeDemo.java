package com.baijia123.redis;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@ContextConfiguration("/applicationContext.xml")
public class RedisSubscribeDemo {
	private static final Log log = LogFactory.getLog(RedisSubscribeDemo.class);
	
	//@Resource
	//private JedisPool pool;
	
	@Test
	public void doTest() throws InterruptedException{
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
//		JedisPool jedisPool = (JedisPool)context.getBean("pool");
//		for(int index = 0 ; index < 1; index ++){
//			TestThread thread = new TestThread(jedisPool);
//			thread.start();
//		}
//		Thread.sleep(50000L);
//		log.info("Test finish...");
		Jedis jedis = (Jedis)context.getBean("jedis");
		System.out.println("111111111");
		
	}
}
