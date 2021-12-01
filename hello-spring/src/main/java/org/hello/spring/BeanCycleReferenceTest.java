package org.hello.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: bean 之间的循环引用问题
 * @author: huangguoqiang
 * @create: 2021-11-12 17:06
 **/
public class BeanCycleReferenceTest {
	public static void main(String[] args) {

		/**
		 * ApplicationContext 是容器的高级接口，BeanFactory是顶级容器、根容器，定义了容器的基本行为
		 * ApplicationContext：spring 应用上下文，官方称为IoC容器，（错误的认识：容器就是一个map,准确的来说，map是容器的一个成员，叫做单例池，singletonObjects,
		 * 容器是一组组件和过程的集合，包括BeanFactory、单例池、BeanPostProcessor等以及之间的协作流程）。
		 *
		 * IoC容器创建管理Bean对象，Spring Bean是有生命周期的：
		 * bean构造器执行、初始化方法执行、：AbstractApplicationContext#refresh#finishBeanFactoryInitialization
		 * BeanFactoryPostProcessor（bean工厂后置处理期）构造器执行、postProcessBeanFactory方法执行：AbstractApplicationContext#refresh#invokeBeanFactoryPostProcessors
		 * BeanPostProcessor（bean后置处理器）构造器执行：AbstractApplicationContext#refresh#registerBeanPostProcessors
		 * BeanPostProcessor（bean后置处理器）的before方法执行、after方法执行：AbstractApplicationContext#refresh#finishBeanFactoryInitialization
		 */

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("BeanCycleReference.xml");


	}
}
