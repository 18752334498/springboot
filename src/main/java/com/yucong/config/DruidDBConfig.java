package com.yucong.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration	//注释此注解，则取消Druid连接池，采用默认连接池（Spring-Boot-2.0.0-M1版本将默认的数据库连接池从tomcat jdbc pool改为了hikari）
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
//其中name用来从application.properties中读取某个属性值，如果该值为空，则返回false;
//如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。
//如果返回值为false，则该configuration不生效；为true则生效。

public class DruidDBConfig {

	/**
	 * 写法模仿
	 * <p>
	 * org.springframework.boot.autoconfigure.jdbc
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	protected static <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
		return (T) properties.initializeDataSourceBuilder().type(type).build();
	}

	/**
	 * 写法模仿
	 * <p>
	 * org.springframework.boot.autoconfigure.jdbc
	 * </p>
	 */
	@Bean
	@ConfigurationProperties("spring.datasource.druid")
	public DruidDataSource druidDataSource(DataSourceProperties properties) {
		DruidDataSource dataSource = createDataSource(properties, DruidDataSource.class);
		DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
		String validationQuery = databaseDriver.getValidationQuery();
		if (validationQuery != null) {
			dataSource.setTestOnBorrow(true);
			dataSource.setValidationQuery(validationQuery);
		}
		return dataSource;
	}

	@Bean
	public ServletRegistrationBean<StatViewServlet> DruidStatViewServlet() {
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<StatViewServlet>(
				new StatViewServlet(), "/druid/*");
		bean.addInitParameter("loginUsername", "admin");
		bean.addInitParameter("loginPassword", "admin");
		return bean;
	}

	@Bean
	public FilterRegistrationBean<WebStatFilter> druidStatFilter() {

		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(
				new WebStatFilter());

		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");

		// 添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
