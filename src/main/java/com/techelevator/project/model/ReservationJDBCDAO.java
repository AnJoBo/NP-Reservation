package com.techelevator.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ReservationJDBCDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	public ReservationJDBCDAO (DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Reservation> getAvailableRes(String userInput1, String userInput2, String campgroundName) {
		List<Reservation> reservationList = new ArrayList<>();
		
		String sqlGetReser = "SELECT * FROM reservation JOIN site ON reservation.site_id = site.site_id JOIN campground on site.campground_id = campground.campground_id "
				+ "WHERE (from_date >= ? AND from_date <= ?) OR (to_date >= ? AND to_date <= ?) AND campground.name = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReser, parseDate(userInput1), parseDate(userInput2), parseDate(userInput1), parseDate(userInput2), campgroundName);
		
		while (results.next()) {
			Reservation theReservation = mapRowToReservation(results);
			reservationList.add(theReservation);
			
		}
		return reservationList;
	}
private Reservation mapRowToReservation(SqlRowSet results) {
	Reservation theReservation;
	theReservation = new Reservation();
	theReservation.setReservationId(results.getLong("reservation_id"));
	theReservation.setSiteId(results.getInt("site_id"));
	theReservation.setName(results.getString("name"));
	theReservation.setFromDate(results.getDate("from_date"));
	theReservation.setToDate(results.getDate("to_date"));

	return theReservation;

	}



	@Override
	public Date parseDate(String userInput) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		Date date = null;
		try {
			date = (Date) formatter.parse(userInput);
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		
	}

	
}
