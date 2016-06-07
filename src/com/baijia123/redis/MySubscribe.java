package com.baijia123.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.JedisPubSub;

public class MySubscribe extends JedisPubSub {

	private static final Log log = LogFactory.getLog(MySubscribe.class);
	// ȡ�ð����ʽ�ķ�ʽ���ĵ���Ϣ��Ĵ���    
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		// TODO Auto-generated method stub
		//super.onPMessage(pattern, channel, message);
		log.info("pattern = " + pattern + " channel = " + channel + " message = " + message);
	}

	// ��ʼ�������ʽ�ķ�ʽ����ʱ��Ĵ���    
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub
		//super.onPSubscribe(pattern, subscribedChannels);
		log.info("pattern = " + pattern + " subscribedChannels = " + subscribedChannels);
	}
	
}
