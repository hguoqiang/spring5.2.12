/*
 * Copyright 2002-2019 the original author or authors.
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
 * {@link Environment} implementation suitable for use in 'standard' (i.e. non-web)
 * applications.
 *
 * <p>In addition to the usual functions of a {@link ConfigurableEnvironment} such as
 * property resolution and profile-related operations, this implementation configures two
 * default property sources, to be searched in the following order:
 * <ul>
 * <li>{@linkplain AbstractEnvironment#getSystemProperties() system properties}
 * <li>{@linkplain AbstractEnvironment#getSystemEnvironment() system environment variables}
 * </ul>
 *
 * That is, if the key "xyz" is present both in the JVM system properties as well as in
 * the set of environment variables for the current process, the value of key "xyz" from
 * system properties will return from a call to {@code environment.getProperty("xyz")}.
 * This ordering is chosen by default because system properties are per-JVM, while
 * environment variables may be the same across many JVMs on a given system.  Giving
 * system properties precedence allows for overriding of environment variables on a
 * per-JVM basis.
 *
 * <p>These default property sources may be removed, reordered, or replaced; and
 * additional property sources may be added using the {@link MutablePropertySources}
 * instance available from {@link #getPropertySources()}. See
 * {@link ConfigurableEnvironment} Javadoc for usage examples.
 *
 * <p>See {@link SystemEnvironmentPropertySource} javadoc for details on special handling
 * of property names in shell environments (e.g. Bash) that disallow period characters in
 * variable names.
 *
 * @author Chris Beams
 * @since 3.1
 * @see ConfigurableEnvironment
 * @see SystemEnvironmentPropertySource
 * @see org.springframework.web.context.support.StandardServletEnvironment
 */

/**
 * Environment的核心功能：
 * 1.Environment如何获取jvm系统属性和系统环境变量；
 * 2.如何实现profile决定bean属于哪个profile；
 * 3.设置属性properties原理；
 * 4.如何实现属性获取和资源路径占位解析；
 *
 * 1.Environment如何获取jvm系统属性和系统环境变量
 * StandardEnvironment是Spring上下文中标准的Environment，在非web应用中使用该Environment作为ApplicationContext的环境组件：
 * StandardEnvironment实现解析systemEnvironment系统环境变量和systemProperties系统属性作为Properties。
 *
 * 2.如何实现profile并决定bean属于哪个profile
 * 在Spring中设置profile可以使Spring应用契合多环境：dev/test/pro等等。其中关键点在于：
 * 让Spring应用知道当前处于什么环境，这可以有系统启动时携带参数：系统属性等设置决定，则该环境为Spring应用有效的profile；
 * 当前配置的Bean是属于哪个环境（profile）
 *
 * Spring容器在解析Bean时会将Bean定义中的profile作为参数传递给Environment，由Environment决定该profile是否可以被当前环境接受。
 * 这系列的逻辑由Environment的acceptsProfiles接口承担实现，因为Environment持有当前上下文容器的所有profile（active和default）,{@link AbstractEnvironment}中实现：
 *
 * 3.设置属性properties原理
 * Environment中的properties分为两种，一种是Spring框架自身加载的：jvm系统属性和系统环境变量——前文中介绍StandardEnvironment；另一种是用户应用自定义的属性加载入Environment。
 * 在Environment中的properties被抽象成：{@link PropertySource}
 * 在AbstractEnvironment中持有 {@link MutablePropertySources }达到Environment中包含properties的目的。
 * {@link ConfigurableEnvironment }，该配置环境接口提供了获取多属性源的接口：
 * MutablePropertySources getPropertySources()，通过该接口获取易变的多属性源可以达到操作Environment中的属性源的操作：addBefore/addFirst/addAfter/AddLaster/remove等等操作属性源的接口。
 *
 *
 * 4.如何实现属性获取
 * Environment通过委托于PropertyResolver完成解析获取属性。PropertyResolver充当的角色
 * 解析获取属性，并支持类型转换；
 * 按层级搜索属性：即Environment中的PropertySource具有优先级；
 * AbstractEnvironment中接口实现
 *
 *
 *
 */

public class StandardEnvironment extends AbstractEnvironment {

	/** System environment property source name: {@value}. */
	public static final String SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME = "systemEnvironment";

	/** JVM system properties property source name: {@value}. */
	public static final String SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME = "systemProperties";


	/**
	 * Customize the set of property sources with those appropriate for any standard
	 * Java environment:
	 * <ul>
	 * <li>{@value #SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME}
	 * <li>{@value #SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME}
	 * </ul>
	 * <p>Properties present in {@value #SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME} will
	 * take precedence over those in {@value #SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME}.
	 * @see AbstractEnvironment#customizePropertySources(MutablePropertySources)
	 * @see #getSystemProperties()
	 * @see #getSystemEnvironment()
	 */
	@Override
	protected void customizePropertySources(MutablePropertySources propertySources) {
		propertySources.addLast(
				new PropertiesPropertySource(SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, getSystemProperties()));
		propertySources.addLast(
				new SystemEnvironmentPropertySource(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, getSystemEnvironment()));
	}

}
