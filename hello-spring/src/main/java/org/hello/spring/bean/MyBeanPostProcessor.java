package org.hello.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-11-09 11:54
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {

	public MyBeanPostProcessor() {
		System.out.println("MyBeanPostProcessor  noArgsConstructor");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if("beanLifeCycle".equals(beanName)) {
			System.out.println("BeanPostProcessor 实现类 before ⽅法被调⽤中......");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if("beanLifeCycle".equals(beanName)) {
			System.out.println("BeanPostProcessor 实现类 after ⽅法被调⽤中......");
		}
		return bean;
	}
}
