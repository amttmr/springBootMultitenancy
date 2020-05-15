package com.acube.springboot.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROPERTY",schema = "NEXTGEN_GLOBAL")
public class PropertyDTO extends BaseDTO{

	private static final long serialVersionUID = 98577340074105279L;
	
	@Id
	@Column(name = "PROPERTY_ID")
	private UUID id;

	@Column(name = "PROPERTY_DESC")
	private String desc;

	@Column(name = "KEY_NAME")
	private String keyName;

	@Column(name = "VALUE")
	private String value;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
