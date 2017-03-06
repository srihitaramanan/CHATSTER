package com.src.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="college_group")
public class CollegeGroup {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int groupId;
	
	 @Column(name="group_name")
	private String groupName;
	
	 @Column(name="is_active")
	private short isActive;
	
	 @Column(name="row_created")
	private Date rowCreated;
	
	 @Column(name="row_altered")
	private Date rowAltered;
	
	 
	public Date getRowCreated() {
		return rowCreated;
	}
	public void setRowCreated(Date rowCreated) {
		this.rowCreated = rowCreated;
	}
	public Date getRowAltered() {
		return rowAltered;
	}
	public void setRowAltered(Date rowAltered) {
		this.rowAltered = rowAltered;
	}
	public short getIsActive() {
		return isActive;
	}
	public void setIsActive(short isActive) {
		this.isActive = isActive;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public CollegeGroup(){}
	
	public CollegeGroup(int groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}

}
