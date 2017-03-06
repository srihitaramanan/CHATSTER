package com.src.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="year")
public class Year {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int yearId;
	
	 @Column(name="year")
	private String year;
	
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
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

}
