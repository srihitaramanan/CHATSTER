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
@Table(name="role")
public class Role {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int roleId;
	
	 @Column(name="authority")
	private String authority;
	
	 @Column(name="is_active")
	private short isActive;
	
	 @Column(name="row_created")
	private Date rowCreated;
	
	 @Column(name="row_altered")
	private Date rowAltered;
	
	@OneToMany(mappedBy = "roleFk", cascade = CascadeType.PERSIST)
	private List<UserRole> uRole = new ArrayList<UserRole>();
	 
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
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
	/*public List<UserRole> getuRole() {
		return uRole;
	}
	public void setuRole(List<UserRole> uRole) {
		this.uRole = uRole;
	}*/

}
