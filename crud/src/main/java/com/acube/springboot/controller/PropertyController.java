package com.acube.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acube.springboot.entity.GlobalPlatformDTO;
import com.acube.springboot.entity.Persons;
import com.acube.springboot.repository.GlobalPlatformRepository;
import com.acube.springboot.repository.PersonsRepository;

@RestController
public class PropertyController {

	@Autowired
	private GlobalPlatformRepository propertyRepo;
	
	@Autowired
	private PersonsRepository persons;
	
	@RequestMapping("/hello")
	public String testSpringBoot() {
		return "Hello World";
	}
	
	@RequestMapping("/property")
	public String getProperty() {
		String propertyName = null;
		Iterable<GlobalPlatformDTO> findAll = propertyRepo.findAll();
		for(GlobalPlatformDTO property: findAll) {
			propertyName = property.getDefaultClientSchema();
		}
		return propertyName;
	}
	
	@RequestMapping("/personproperty")
	public String getPerson() {
		String propertyName = null;
		Iterable<Persons> findAll = persons.findAll();
		findAll.forEach(a -> System.out.println(a.getName()));
		return propertyName;
	}
}
