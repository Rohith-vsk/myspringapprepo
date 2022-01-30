package com.demo.course.config;



import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "courseEntityManagerFactory",basePackages= {"com.demo.course.repository"},transactionManagerRef="courseTransactionManager")
public class CourseDBConfig {
	
	/*
	 * @Value("${connectionString}") private String connectionString;
	 * 
	 * @Value("${userName}") private String username;
	 * 
	 * @Value("${password}") private String password;
	 * 
	 * 
	 * @GetMapping("/conn") public String getConfDetails() { return
	 * "The Connection details are: "+"URL: "+connectionString+" Username: "
	 * +username+"password: "+password; }
	 */
	
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSourceProperties dataSourceProperties()
	{
		return new DataSourceProperties();
	}
	
	@Bean(name="datasource")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public HikariDataSource primaryDataSource(DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	
	
	/*
	 * public DataSource dataSource() { return DataSourceBuilder.create().build(); }
	 */
	
	@Primary
	@Bean(name="courseEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactoryBean(EntityManagerFactoryBuilder builder,@Qualifier("datasource") DataSource datasource)
	{
		/*Map<String,Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddlauto","update");
		properties.put("hibernate.dialect","org.hibernate.dialect.SQLServer2012Dialect");
		return builder.dataSource(datasource).properties(properties).packages("com.demo.springhikariazuresql.entity").persistenceUnit("Department").build();
		*/
		return builder.dataSource(datasource).packages("com.demo.course.entity").build();
	}
	
	@Bean(name="courseTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("courseEntityManagerFactory") EntityManagerFactory entityManagerFactory)
	{
		return new JpaTransactionManager(entityManagerFactory);
	}
}
