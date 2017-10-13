package com.baijia123.spring.context;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/*
 * 在Spring中已经定义了五个标准事件，分别介绍如下：
1)ContextRefreshedEvent：当ApplicationContext初始化或者刷新时触发该事件。
2)ContextClosedEvent：当ApplicationContext被关闭时触发该事件。容器被关闭时，其管理的所有单例Bean都被销毁。
3)RequestHandleEvent：在Web应用中，当一个http请求（request）结束触发该事件。
4)ContextStartedEvent：Spring2.5新增的事件，当容器调用ConfigurableApplicationContext的Start()方法开始/重新开始容器时触发该事件。
5)ContextStopedEvent：Spring2.5新增的事件，当容器调用ConfigurableApplicationContext的Stop()方法停止容器时触发该事件。
 */
public class TradeContextListener implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event.getClass().toString());
		if (event instanceof ContextStartedEvent) {
			System.out.println("it was contextStartedEvent");
		}

		if (event instanceof TradeEvent) {
			System.out.println(event.getSource());
		}
	}

}
