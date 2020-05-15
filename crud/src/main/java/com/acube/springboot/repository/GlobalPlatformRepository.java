package com.acube.springboot.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acube.springboot.entity.GlobalPlatformDTO;

/**
 * <h1>GLOBAL_PLATFORM Repository</h1> DAO layer for GLOBAL Schema.
 * 
 * @author apandey
 * @version 1.0
 * 
 */
@Repository
public interface GlobalPlatformRepository extends CrudRepository<GlobalPlatformDTO, UUID> {

	Iterable<GlobalPlatformDTO> findAll();

	// @Async
	// void findAllAsync();

}
