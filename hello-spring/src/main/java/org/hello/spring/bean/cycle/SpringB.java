package org.hello.spring.bean.cycle;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-11-12 17:09
 **/
public class SpringB {
	private SpringA a;

	public SpringA getA() {
		return a;
	}

	public void setA(SpringA a) {
		this.a = a;
	}
}
