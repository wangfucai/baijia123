package com.baijia123.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class SpringBean1 implements InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

}
