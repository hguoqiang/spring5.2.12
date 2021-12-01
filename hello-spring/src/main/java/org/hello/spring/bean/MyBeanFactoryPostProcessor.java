package org.hello.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-11-09 11:58
 **/
public class MyBeanFactoryPostProcessor  implements BeanFactoryPostProcessor {
	public MyBeanFactoryPostProcessor() {
		System.out.println("MyBeanFactoryPostProcessor  noArgsConstructor ");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor postProcessBeanFactory");
	}
}
