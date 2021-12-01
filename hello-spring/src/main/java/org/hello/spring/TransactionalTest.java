package org.hello.spring;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-11-22 16:32
 **/
public class TransactionalTest {
	public static void main(String[] args) {
		/**
		 * Spring声明式事务控制
		 * 声明式事务很⽅便，尤其纯注解模式，仅仅⼏个注解就能控制事务了
		 * 思考：这些注解都做了什么？好神奇！
		 *  @EnableTransactionManagement  @Transactional
		 *  (1) @EnableTransactionManagement 注解使⽤ @Import 标签引⼊了TransactionManagementConfigurationSelector类，这个类⼜向容器中导⼊了两个重要的组件:
		 *  AutoProxyRegistrar.class 、 ProxyTransactionManagementConfiguration.class
		 * AutoProxyRegistrar 组件：AutoProxyRegistrar 类的 registerBeanDefinitions ⽅法中⼜注册了⼀个组件，发现最终，注册了⼀个叫做 InfrastructureAdvisorAutoProxyCreator 的 Bean，⽽这个类是
		 *  AbstractAutoProxyCreator 的⼦类，实现了 SmartInstantiationAwareBeanPostProcessor 接⼝，说明这是⼀个后置处理器，⽽且跟
		 *  spring AOP 开启@EnableAspectJAutoProxy 时注册的 AnnotationAwareAspectJProxyCreator实现的是同⼀个接⼝，所以说，声明式事务是 springAOP 思想的⼀种应⽤。
		 *
		 * ProxyTransactionManagementConfiguration 组件：
		 *
		 *
		 * 上述组件如何关联起来的？
		 * 事务拦截器实现了MethodInterceptor接⼝，追溯⼀下上⾯提到的
		 * InfrastructureAdvisorAutoProxyCreator后置处理器，它会在代理对象执⾏⽬标⽅法的时候
		 * 获取其拦截器链，⽽拦截器链就是这个TransactionInterceptor，这就把这两个组件联系起
		 * 来；
		 * 构造⽅法传⼊ PlatformTransactionManager(事务管理器)、 TransactionAttributeSource(属
		 * 性解析器)，但是追溯⼀下上⾯贴的ProxyTransactionManagementConfiguration的源码，
		 * 在注册事务拦截器的时候并没有调⽤这个带参构造⽅法，⽽是调⽤的⽆参构造⽅法，然后再
		 * 调⽤set⽅法注⼊这两个属性，效果⼀样。
		 *
		 *
		 * @EnableTransactionManagement 注解
		 * 1)通过@import引⼊了TransactionManagementConfigurationSelector类
		 * 它的selectImports⽅法导⼊了另外两个类： AutoProxyRegistrar和ProxyTransactionManagementConfiguration
		 * 2） AutoProxyRegistrar类分析
		 * ⽅法registerBeanDefinitions中，引⼊了其他类，通过AopConfigUtils.registerAutoProxyCreatorIfNecessary(registry)引⼊
		 * InfrastructureAdvisorAutoProxyCreator，它继承了AbstractAutoProxyCreator，是⼀个后置处理器类
		 * 3） ProxyTransactionManagementConfiguration 是⼀个添加了@Configuration注解的配置类（注册bean）
		 * 注册事务增强器（注⼊属性解析器、事务拦截器）
		 * 属性解析器： AnnotationTransactionAttributeSource，内部持有了⼀个解析器集合
		 * Set<TransactionAnnotationParser> annotationParsers;
		 * 具体使⽤的是SpringTransactionAnnotationParser解析器，⽤来解析@Transactional的事务属性。
		 *
		 * 事务拦截器： TransactionInterceptor实现了MethodInterceptor接⼝，该通⽤拦截会在产⽣代理对象之前和aop增强合并，最终⼀起影响到代理对象
		 * TransactionInterceptor的invoke⽅法中invokeWithinTransaction会触发原有业务逻辑调⽤（增强事务）
		 */
	}
}
