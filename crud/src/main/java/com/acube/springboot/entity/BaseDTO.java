package com.acube.springboot.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;

public class BaseDTO implements Serializable {

	private static final long serialVersionUID = -3815599674296341254L;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	@Column(name = "IS_OVERRIDABLE")
	private Boolean isOverridable;

	@Column(name = "LAST_UPDATED")
	private Timestamp lastUpdated;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsOverridable() {
		return isOverridable;
	}

	public void setIsOverridable(Boolean isOverridable) {
		this.isOverridable = isOverridable;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public String toString() {
		return "BaseDTO [isActive=" + isActive + ", isOverridable=" + isOverridable + ", lastUpdated=" + lastUpdated
				+ ", lastUpdatedBy=" + lastUpdatedBy + "]";
	}
}
