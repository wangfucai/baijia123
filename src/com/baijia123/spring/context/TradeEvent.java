package com.baijia123.spring.context;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TradeEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	private String event;
}
