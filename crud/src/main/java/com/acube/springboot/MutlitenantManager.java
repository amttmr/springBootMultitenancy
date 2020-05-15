package com.acube.springboot;

import static java.lang.String.format;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

//@Configuration
public class MutlitenantManager {

	private final ThreadLocal<String> currentTenant = new ThreadLocal<>();
	private final Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();
	//private final DataSourceProperties properties ;
	
	private Function<String, DataSourceProperties> tenantResolver;

	private AbstractRoutingDataSource multiTenantDataSource;

	/*
	 * public MutlitenantManager(DataSourceProperties properties) { this.properties
	 * = properties; }
	 */	
	@Bean
	public DataSource dataSource() {

		multiTenantDataSource = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {
				return currentTenant.get();
			}
		};
		multiTenantDataSource.setTargetDataSources(tenantDataSources);
		multiTenantDataSource.setDefaultTargetDataSource(defaultDataSource());
		multiTenantDataSource.afterPropertiesSet();
		return multiTenantDataSource;
	}
	
	public void setTenantResolver(Function<String, DataSourceProperties> tenantResolver) {
		this.tenantResolver = tenantResolver;
	}
	
	public void setCurrentTenant(String tenantId) throws Exception {
		if (tenantIsAbsent(tenantId)) {
			if (tenantResolver != null) {
				DataSourceProperties properties;
				try {
					properties = tenantResolver.apply(tenantId);
					System.out.println("[d] Datasource properties resolved for tenant ID '{}'" +tenantId);
				} catch (Exception e) {
					throw new Exception(e);
				}

				String url = properties.getUrl();
				String username = properties.getUsername();
				String password = properties.getPassword();

				addTenant(tenantId, url, username, password);
			} else {
				throw new Exception(format("Tenant %s not found!", tenantId));
			}
		}
		currentTenant.set(tenantId);
		System.out.println("[d] Tenant '{}' set as current."+ tenantId);
	}
	
	public void addTenant(String tenantId, String url, String username, String password) throws SQLException {

		DataSource dataSource = DataSourceBuilder.create()
				.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
				.url(url)
				.username(username)
				.password(password)
				.build();

		// Check that new connection is 'live'. If not - throw exception
		try(Connection c = dataSource.getConnection()) {
			tenantDataSources.put(tenantId, dataSource);
			multiTenantDataSource.afterPropertiesSet();
			System.out.println("[d] Tenant '{}' added." +tenantId);
		}
	}
	
	public DataSource removeTenant(String tenantId) {
		Object removedDataSource = tenantDataSources.remove(tenantId);
		multiTenantDataSource.afterPropertiesSet();
		return (DataSource) removedDataSource;
	}

	public boolean tenantIsAbsent(String tenantId) {
		return !tenantDataSources.containsKey(tenantId);
	}
	
	public Collection<Object> getTenantList() {
		return tenantDataSources.keySet();
	}

	private DriverManagerDataSource defaultDataSource() {
		DriverManagerDataSource defaultDataSource = new DriverManagerDataSource();
		defaultDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		defaultDataSource.setUrl("jdbc:sqlserver://10.204.165.60:58564;databaseName=NextGenPortal");
		defaultDataSource.setUsername("NextGenDev");
		defaultDataSource.setPassword("NextGenDev#123");
		defaultDataSource.setSchema("NEXTGEN_GLOBAL");
		return defaultDataSource;
	}
}
