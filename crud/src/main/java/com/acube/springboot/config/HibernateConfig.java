package com.acube.springboot.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(basePackages = "com.acube.springboot.repository", entityManagerFactoryRef = "globalEntityManager", transactionManagerRef = "globalTransactionManager")
public class HibernateConfig {

	@Autowired
	private Environment env;

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean globalEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(globalDataSource());
		em.setPackagesToScan(new String[] { "com.acube.springboot.entity" });
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(Boolean.getBoolean(env.getProperty("spring.jpa.show-sql")));
		em.setJpaVendorAdapter(vendorAdapter);
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("spring.jpa.properties.hibernate.hbm2ddl.auto"));
		properties.put(org.hibernate.cfg.Environment.DIALECT, env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put(org.hibernate.cfg.Environment.DEFAULT_SCHEMA, "NEXTGEN_GLOBAL");
		properties.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Primary
	@Bean
	public DataSource globalDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Primary
	@Bean
	public PlatformTransactionManager globalTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(globalEntityManager().getObject());
		
		return transactionManager;
	}

}
