package org.hello.spring.bean;

import org.springframework.beans.factory.InitializingBean;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-11-09 11:32
 **/
public class BeanLifeCycle implements InitializingBean {

	public BeanLifeCycle() {
		System.out.println("BeanLifeCycle noArgsConstructor ");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("implements InitializingBean  afterPropertiesSet");
	}
}
