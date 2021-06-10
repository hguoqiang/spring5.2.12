package org.hello.spring.bean;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-06-07 11:40
 **/

public class EnvironmentDemo implements EnvironmentAware {
	private Environment environment;
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public Environment getEnvironment() {
		return environment;
	}
}
