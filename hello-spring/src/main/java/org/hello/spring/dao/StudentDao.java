package org.hello.spring.dao;

import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2022-03-19 13:42
 **/
@Repository
public class StudentDao {

	public StudentDao() {
		System.out.println("StudentDao no args");
	}

	public void hello() {
		System.out.println("Hello,StudentDao!");
	}
}
