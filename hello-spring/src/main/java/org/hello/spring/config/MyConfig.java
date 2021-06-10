package org.hello.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-02-09 18:24
 **/
@Configuration
@ComponentScan(basePackages = {"org.hello.spring.bean"})
public class MyConfig {

}
