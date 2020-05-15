package com.acube.springboot.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.acube.springboot.entity.PropertyDTO;

public interface PropertyRepository extends CrudRepository<PropertyDTO, UUID>{

}
