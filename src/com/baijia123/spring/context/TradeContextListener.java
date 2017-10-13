package com.baijia123.spring.context;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/*
 * ��Spring���Ѿ������������׼�¼����ֱ�������£�
1)ContextRefreshedEvent����ApplicationContext��ʼ������ˢ��ʱ�������¼���
2)ContextClosedEvent����ApplicationContext���ر�ʱ�������¼����������ر�ʱ�����������е���Bean�������١�
3)RequestHandleEvent����WebӦ���У���һ��http����request�������������¼���
4)ContextStartedEvent��Spring2.5�������¼�������������ConfigurableApplicationContext��Start()������ʼ/���¿�ʼ����ʱ�������¼���
5)ContextStopedEvent��Spring2.5�������¼�������������ConfigurableApplicationContext��Stop()����ֹͣ����ʱ�������¼���
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
