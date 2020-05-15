package com.acube.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class HelloController {
	
	@Autowired
	@PreAuthorize("hasRole('users')")
	@RequestMapping("/")
	public String HelloWorld() {
		return "Hello World!";
	}

}
