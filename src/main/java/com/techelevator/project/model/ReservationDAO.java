package com.techelevator.project.model;

import java.sql.Date;
import java.util.List;

public interface ReservationDAO {
	
	public List<Reservation> getAvailableRes(String userInput1, String userInput2, String campgrounName);
	public java.util.Date parseDate(String userInput);
}
