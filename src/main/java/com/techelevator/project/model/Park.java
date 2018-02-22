package com.techelevator.project.model;

import java.time.LocalDate;
import java.util.Date;

public class Park {
	
	private Long id;
	private String name;
	private String location;
	private Date establishDate;
	private Integer area;
	private Integer visitors;
	private String description;
	
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public Date getEstablishDate() {
		return establishDate;
	}
	public Integer getArea() {
		return area;
	}
	public Integer getVisitors() {
		return visitors;
	}
	public String getDescription() {
		//logic to wrap text
		return description;
	}
	
	public String toString() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
