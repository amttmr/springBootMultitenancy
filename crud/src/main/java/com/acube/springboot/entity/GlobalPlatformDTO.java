package com.acube.springboot.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h1>Entity</h1> This is Mapping of PLATFORM Table of GLOBAL Schema.
 * 
 * @author apandey
 * @version 1.0
 * 
 */
@Entity
@Table(name = "GLOBAL_PLATFORM")
public class GlobalPlatformDTO extends BaseDTO {

	private static final long serialVersionUID = 5210654844619643268L;

	@Id
	@Column(name = "PLATFORM_ID")
	private UUID id;

	@Column(name = "PLATFORM_IDENTIFIER")
	private String identifier;

	@Column(name = "PLATFORM_DESC")
	private String desc;

	@Column(name = "PLATFORM_SCHEMA")
	private String schema;

	@Column(name = "DEFAULT_CLIENT_SCHEMA")
	private String defaultClientSchema;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getDefaultClientSchema() {
		return defaultClientSchema;
	}

	public void setDefaultClientSchema(String defaultClientSchema) {
		this.defaultClientSchema = defaultClientSchema;
	}

	@Override
	public String toString() {
		return "PlatformDTO [id=" + id + ", identifier=" + identifier + ", desc=" + desc
				+ ", schema=" + schema + ", defaultClientSchema=" + defaultClientSchema + "]";
	}
}
