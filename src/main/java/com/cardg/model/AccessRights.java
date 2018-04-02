package com.cardg.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class AccessRights implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accessRightsID;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created")
	private java.util.Date dateCreated;
	private String accessRightsName;
	private String accessRightsStatus;

	@ManyToMany
	private List<Role> listRole;

	public long getAccessRightsID() {
		return accessRightsID;
	}

	public void setAccessRightsID(long accessRightsID) {
		this.accessRightsID = accessRightsID;
	}

	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAccessRightsName() {
		return accessRightsName;
	}

	public void setAccessRightsName(String accessRightsName) {
		this.accessRightsName = accessRightsName;
	}

	public String getAccessRightsStatus() {
		return accessRightsStatus;
	}

	public void setAccessRightsStatus(String accessRightsStatus) {
		this.accessRightsStatus = accessRightsStatus;
	}

	public List<Role> getListRole() {
		return listRole;
	}

	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}

}
