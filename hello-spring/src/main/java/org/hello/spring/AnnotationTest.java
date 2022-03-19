package org.hello.spring;

import org.hello.spring.config.MyConfig;
import org.hello.spring.dao.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2022-03-19 13:47
 **/
public class AnnotationTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
		Student student = applicationContext.getBean(Student.class);
		student.setName("zhangsan");
		System.out.println(student);
	}
}
