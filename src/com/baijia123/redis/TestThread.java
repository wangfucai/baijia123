package com.baijia123.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestThread extends Thread {

	private Log log = LogFactory.getLog(TestThread.class);
	
	private JedisPool pool;
	
	
	public TestThread(JedisPool pool){
		log.info("TestThread initial");
		this.pool = pool;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		Jedis jedis = pool.getResource();
		jedis.psubscribe(new MySubscribe(), "*");
		try{
			Thread.sleep(10000L);
			
		}catch(Exception e){
			log.info("sleep failed");
			e.printStackTrace();
		}
		jedis.close();
		log.info("thread finished");
	}
	
}
