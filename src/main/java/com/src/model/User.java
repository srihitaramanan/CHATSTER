package com.src.model;


import java.util.Date;
import java.util.ArrayList;
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
@Table(name = "user")
public class User{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "password")
	private String password;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "group_name")
	private String groupName;

	@Column(name = "is_active")
	private short isActive;

	@Column(name = "row_created")
	private Date rowCreated;

	@Column(name = "row_altered")
	private Date rowAltered;
	
	
	@OneToMany(mappedBy="userFk",cascade=CascadeType.PERSIST)
	private List<UserRole> uRole = new ArrayList<UserRole>();
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	
	public User(){
		
	}
	public User(int userId, String firstName, String lastName, String groupName) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.groupName = groupName;
	}

	
	
	
	

}
