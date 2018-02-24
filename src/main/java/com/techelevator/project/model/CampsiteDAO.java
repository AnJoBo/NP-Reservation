package com.techelevator.project.model;

import java.util.Date;
import java.util.List;

public interface CampsiteDAO {
	
	public List<Campsite> getOpenReservations(String userInput1, String userInput2, String campgroundName);

	public Date parseDate(String userInput);


}
