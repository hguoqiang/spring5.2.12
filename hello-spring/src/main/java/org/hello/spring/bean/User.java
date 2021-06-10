package org.hello.spring.bean;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-02-09 20:23
 **/
public class User {
	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	public User() {
		System.out.println("noArgsConstructor");
	}

	public User(String value) {
		System.out.println("ArgsConstructor："+value);
		this.value = value;
	}

	public void init(){
		System.out.println("init...");
	}

}
