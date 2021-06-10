/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core.env;

/**
 * Interface representing the environment in which the current application is running.
 * Models two key aspects of the application environment: <em>profiles</em> and
 * <em>properties</em>. Methods related to property access are exposed via the
 * {@link PropertyResolver} superinterface.
 *
 * <p>A <em>profile</em> is a named, logical group of bean definitions to be registered
 * with the container only if the given profile is <em>active</em>. Beans may be assigned
 * to a profile whether defined in XML or via annotations; see the spring-beans 3.1 schema
 * or the {@link org.springframework.context.annotation.Profile @Profile} annotation for
 * syntax details. The role of the {@code Environment} object with relation to profiles is
 * in determining which profiles (if any) are currently {@linkplain #getActiveProfiles
 * active}, and which profiles (if any) should be {@linkplain #getDefaultProfiles active
 * by default}.
 *
 * <p><em>Properties</em> play an important role in almost all applications, and may
 * originate from a variety of sources: properties files, JVM system properties, system
 * environment variables, JNDI, servlet context parameters, ad-hoc Properties objects,
 * Maps, and so on. The role of the environment object with relation to properties is to
 * provide the user with a convenient service interface for configuring property sources
 * and resolving properties from them.
 *
 * <p>Beans managed within an {@code ApplicationContext} may register to be {@link
 * org.springframework.context.EnvironmentAware EnvironmentAware} or {@code @Inject} the
 * {@code Environment} in order to query profile state or resolve properties directly.
 *
 * <p>In most cases, however, application-level beans should not need to interact with the
 * {@code Environment} directly but instead may have to have {@code ${...}} property
 * values replaced by a property placeholder configurer such as
 * {@link org.springframework.context.support.PropertySourcesPlaceholderConfigurer
 * PropertySourcesPlaceholderConfigurer}, which itself is {@code EnvironmentAware} and
 * as of Spring 3.1 is registered by default when using
 * {@code <context:property-placeholder/>}.
 *
 * <p>Configuration of the environment object must be done through the
 * {@code ConfigurableEnvironment} interface, returned from all
 * {@code AbstractApplicationContext} subclass {@code getEnvironment()} methods. See
 * {@link ConfigurableEnvironment} Javadoc for usage examples demonstrating manipulation
 * of property sources prior to application context {@code refresh()}.
 *
 * @author Chris Beams
 * @since 3.1
 * @see PropertyResolver
 * @see EnvironmentCapable
 * @see ConfigurableEnvironment
 * @see AbstractEnvironment
 * @see StandardEnvironment
 * @see org.springframework.context.EnvironmentAware
 * @see org.springframework.context.ConfigurableApplicationContext#getEnvironment
 * @see org.springframework.context.ConfigurableApplicationContext#setEnvironment
 * @see org.springframework.context.support.AbstractApplicationContext#createEnvironment
 */

/**
 * 在Environment中，有两大主要概念：
 *
 * Profile：在Spring中profile是针对Bean定义而言，是Bean定义的逻辑分组。通常表现为：dev/test/production等等，对于Bean定义属于哪个profile是由XML或者Annotation配置决定；
 * Properties：即键值对（Keys-Pairs），在Spring中将*.properties文件/JVM系统属性（JVM System Property）/系统环境变量（JVM Environment Variables）/Properties对象/Map对象抽象为Properties；
 *
 * Envoriment的能力
 * Envoriment提供了获取和设置Profile的能力，可以决定生效哪些Profiles。
 * 同时提供了操作Properties的能力，可以增加/移除整个Properties，能获取某个Key-Pair。
 *
 * Environment可以获取系统JVM属性：-Dspring.profiles.active="..."或者显示编程式setActiveProfiles("...")生效相应的profile；
 * 可以通过Environment提供的getActiveProfiles和getDefaultProfiles获取profile；
 * Environment可以决定Bean定义属于哪个Profile；
 * Environment可以操作上下文中的Properties，提供了add/remove接口；
 * Environment集成PropertyResolver，利用Spring中的Conversion服务多Properties中的属性进行类型转化；
 * Environment提供了解析任意Resource资源路径中的${...}占位，支持嵌套占位解析；
 *
 *
 * PropertyResolver用于解析潜在的源的属性。潜在源可以是多种形式：properties文件、jvm属性、jvm环境变量、map等等。
 *
 * 从以上可以推导出Environment具有从源中解析获取Properties的能力，或者说Environment本身就是属性解析器。
 *
 * Environment接口体系中非常重要的成员是ConfigurableEnvironment，它提供了更强的处理能力：
 * 设置上下文容器的profile；
 * 操作Environment中properties；
 *
 * Environment的核心功能：
 * Environment如何获取jvm系统属性和系统环境变量；
 * 如何实现profile决定bean属于哪个profile；
 * 设置属性properties原理；
 * 如何实现属性获取和资源路径占位解析；
 *
 * Envoriment中易忽略点
 * Environment能够解析获取Spring上下文中的属性源，且支持解析占位${...}，但是：
 * Environment是否支持解析Spring中所有的属性源
 * Environment是否支持解析Spring应用出现的任意${...}
 * 1.支持环境相关的属性解析
 * Environment英文是环境的意思，所以Spring中的Environment组件的属性解析和${...}也只是和环境相关，与环境以外的属性源和${...}，Environment是不支持的。
 * Environment中支持的属性元PropertySource只有以下三种情况：
 * Environment组件自身加载的属性源，如StandardEnvironment中加载的系统属性系统环境变量；如StandardServletEnvironment中加载的ServletContext参数和Servlet参数；
 * 通过ConfigralbeEnvironment获取MutablePropertySources对象，编程式加入的属性源；
 * 通过@PropertySource注解加载的.properties文件中的属性；
 *
 * 2.支持环境相关的占位解析
 * Environment中支持的占位${...}解析只与环境相关，前文中介绍只有Resource路径中的占位Environment才负责解析。
 * Tips：
 * 关于@Value注解和Bean定义的占位，由PropertySourcesPlaceholderConfigurer负责解析，后续文章会详解。
 */
/*
@Configuration
@PropertySource("classpath:/com/myco/app.properties")
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    public TestBean testBean() {
        TestBean testBean = new TestBean();
        // 通过Environment获取属性
        testBean.setName(env.getProperty("testbean.name"));
        return testBean;
    }
}
 */
public interface Environment extends PropertyResolver {

	/**
	 * Return the set of profiles explicitly made active for this environment. Profiles
	 * are used for creating logical groupings of bean definitions to be registered
	 * conditionally, for example based on deployment environment. Profiles can be
	 * activated by setting {@linkplain AbstractEnvironment#ACTIVE_PROFILES_PROPERTY_NAME
	 * "spring.profiles.active"} as a system property or by calling
	 * {@link ConfigurableEnvironment#setActiveProfiles(String...)}.
	 * <p>If no profiles have explicitly been specified as active, then any
	 * {@linkplain #getDefaultProfiles() default profiles} will automatically be activated.
	 * @see #getDefaultProfiles
	 * @see ConfigurableEnvironment#setActiveProfiles
	 * @see AbstractEnvironment#ACTIVE_PROFILES_PROPERTY_NAME
	 */
	//获取当前上下文生效应用的profile
	String[] getActiveProfiles();

	/**
	 * Return the set of profiles to be active by default when no active profiles have
	 * been set explicitly.
	 * @see #getActiveProfiles
	 * @see ConfigurableEnvironment#setDefaultProfiles
	 * @see AbstractEnvironment#DEFAULT_PROFILES_PROPERTY_NAME
	 */
	// 获取当前上下文默认的profile
	String[] getDefaultProfiles();

	/**
	 * Return whether one or more of the given profiles is active or, in the case of no
	 * explicit active profiles, whether one or more of the given profiles is included in
	 * the set of default profiles. If a profile begins with '!' the logic is inverted,
	 * i.e. the method will return {@code true} if the given profile is <em>not</em> active.
	 * For example, {@code env.acceptsProfiles("p1", "!p2")} will return {@code true} if
	 * profile 'p1' is active or 'p2' is not active.
	 * @throws IllegalArgumentException if called with zero arguments
	 * or if any profile is {@code null}, empty, or whitespace only
	 * @see #getActiveProfiles
	 * @see #getDefaultProfiles
	 * @see #acceptsProfiles(Profiles)
	 * @deprecated as of 5.1 in favor of {@link #acceptsProfiles(Profiles)}
	 */
	@Deprecated
	// 判断是否接受某个profile，用于决定bean定义属于哪个profile
	boolean acceptsProfiles(String... profiles);

	/**
	 * Return whether the {@linkplain #getActiveProfiles() active profiles}
	 * match the given {@link Profiles} predicate.
	 */
	boolean acceptsProfiles(Profiles profiles);

}
