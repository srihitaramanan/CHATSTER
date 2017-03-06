package com.src.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user_role")
public class UserRole {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userRoleId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="user_fk")
	private User userFk;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="role_fk")
	private Role roleFk;
	
	@Column(name="is_active")
	private short isActive;
	
	 @Column(name="row_created")
	private Date rowCreated;
	
	 @Column(name="row_altered")
	private Date rowAltered;

	

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUserFk() {
		return userFk;
	}

	public void setUserFk(User userFk) {
		this.userFk = userFk;
	}

	public Role getRoleFk() {
		return roleFk;
	}

	public void setRoleFk(Role roleFk) {
		this.roleFk = roleFk;
	}

	public short getIsActive() {
		return isActive;
	}

	public void setIsActive(short isActive) {
		this.isActive = isActive;
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
	
	

}
