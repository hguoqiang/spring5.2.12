package org.hello.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-02-09 18:26
 **/
public class HelloSpringTest {
	public static void main(String[] args) {
//		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
//		Hello he = applicationContext.getBean(Hello.class);
//		he.hello();

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		//ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-${username}.xml");
		Object user = applicationContext.getBean("user");
		System.out.println(user);

		/*EnvironmentDemo environmentDemo = (EnvironmentDemo)applicationContext.getBean("environmentDemo");

		for (String activeProfile : environmentDemo.getEnvironment().getActiveProfiles()) {
			System.out.println(activeProfile);
		}
		System.out.println("--------------------");
		for (String defaultProfile : environmentDemo.getEnvironment().getDefaultProfiles()) {
			System.out.println(defaultProfile);
		}*/
	}


}
