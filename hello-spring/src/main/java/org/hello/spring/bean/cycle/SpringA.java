package org.hello.spring.bean.cycle;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-11-12 17:09
 **/
public class SpringA {
	private SpringB b;

	public SpringB getB() {
		return b;
	}

	public void setB(SpringB b) {
		this.b = b;
	}
}
