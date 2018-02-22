package com.techelevator.project.model;

import java.text.DateFormatSymbols;

public class Campground {
	
	private Long campgroundId;
	private Integer parkId;
	private String name;
	private int openFrom;
	private int openTo;
	private Double dailyFee;
	
	
	public Long getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(Long campgroundId) {
		this.campgroundId = campgroundId;
	}
	public Integer getParkId() {
		return parkId;
	}
	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOpenFrom() {
		return openFrom;
	}
	public void setOpenFrom(int openFrom) {
		this.openFrom = openFrom;
	}
	public int getOpenTo() {
		return openTo;
	}
	public void setOpenTo(int openTo) {
		this.openTo = openTo;
	}
	public Double getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(Double dailyFee) {
		this.dailyFee = dailyFee;
	}
	@Override
	public String toString() {
		//String strDate = String.format("Established: %1$td %1$tB %1$tY", actualPark.getEstablishDate());
		return String.format("%-15s %-10s %-10s %-10s", name, getMonth(openFrom), getMonth(openTo), dailyFee);
	}
	
	public String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month-1];
	}
	

}
