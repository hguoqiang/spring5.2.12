package org.hello.spring.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-08 15:36
 **/
public class AFactoryBean implements FactoryBean<A> {
	@Override
	public A getObject() throws Exception {
		return new A();
	}

	@Override
	public Class<?> getObjectType() {
		return A.class;
	}
}
