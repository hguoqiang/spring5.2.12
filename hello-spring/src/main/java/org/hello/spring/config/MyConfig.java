package org.hello.spring.config;

import org.hello.spring.dao.Student;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-02-09 18:24
 **/
@Configuration
@ComponentScan(basePackages = {"org.hello.spring.dao"})
@Import(Student.class)
public class MyConfig {

}
