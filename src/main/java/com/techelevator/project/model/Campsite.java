package com.techelevator.project.model;

import java.math.BigDecimal;

public class Campsite {
	
	private Long siteId;
	private int campgroundId;
	private int siteNumber;
	private int maxOccupancy;
	private boolean accessible = false;
	private int maximumLength;
	private boolean utilities = false;
	private BigDecimal dailyFee;
	private BigDecimal totalFee;
	private long totalDays;
	
	
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public int getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	public int getSiteNumber() {
		return siteNumber;
	}
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public boolean isAccessible() {
		return accessible;
	}
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	public int getMaximumLength() {
		return maximumLength;
	}
	public void setMaximumLength(int maximumLength) {
		this.maximumLength = maximumLength;
	}
	public boolean isUtilities() {
		return utilities;
	}
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
	
	public void setTotalDays(long totalDays) {
		this.totalDays = totalDays;
	}
	
	public long getTotalDays() {
		return totalDays;
	}
	
	@Override
	public String toString() {
		return String.format("%3s %3s %3s %6s %6.2f"
		, siteNumber, maxOccupancy,maximumLength, utilities, (dailyFee.multiply(new BigDecimal(getTotalDays()))));
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}



}
