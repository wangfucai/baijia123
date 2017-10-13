package com.baijia123.spring.context;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class HelloWorld implements ApplicationEventPublisherAware {

	private String word;
	private ApplicationEventPublisher tradeEventPublisher;

	public void setWord(String w) {
		this.word = w;
	}

	public void say() {
		System.out.println("say : " + this.word);
		// construct a TradeEvent instance and publish it
		TradeEvent tradeEvent = new TradeEvent(new String("tradeEvent"));
		this.tradeEventPublisher.publishEvent(tradeEvent);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub
		this.tradeEventPublisher = applicationEventPublisher;
	}

}
