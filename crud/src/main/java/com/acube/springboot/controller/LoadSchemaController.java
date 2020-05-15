package com.acube.springboot.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acube.springboot.MutlitenantManager;


@RestController
@PropertySource({"classpath:application.properties"})
public class LoadSchemaController {

	//private final MutlitenantManager tenantManager;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	/*
	 * public LoadSchemaController(MutlitenantManager tenantManager) {
	 * this.tenantManager = tenantManager; }
	 */
	@RequestMapping("/loadGlobal/{tenantId}")
	public String loadGlobalSchema(@RequestParam("tenantId")String tenantId) throws SQLException {
		
		//tenantManager.addTenant(tenantId, url, username, password);
		
		return "Global Schema Loaded.";
	}
}