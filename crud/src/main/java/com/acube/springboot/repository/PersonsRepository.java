package com.acube.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.acube.springboot.entity.Persons;

public interface PersonsRepository extends CrudRepository<Persons, Integer>{

}
