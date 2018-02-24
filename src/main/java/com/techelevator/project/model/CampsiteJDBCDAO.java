package com.techelevator.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class CampsiteJDBCDAO implements CampsiteDAO{
	
	private JdbcTemplate jdbcTemplate;
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	public CampsiteJDBCDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Campsite> getOpenReservations(String userInput1, String userInput2, String campgroundName) {
		List<Campsite> openCampsiteList = new ArrayList<>();

		String sqlGetReser = "SELECT site.*, campground.daily_fee " + 
				"FROM site " + 
				"JOIN reservation ON reservation.site_id = site.site_id " + 
				"JOIN campground ON site.campground_id = campground.campground_id " + 
				"Where (from_date <= ? OR from_date >= ?) " + 
				"AND (to_date  <= ? OR to_date >= ?) " + 
				"AND (campground.name= ?) " + 
				"ORDER BY max_occupancy " + 
				"LIMIT 5";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReser, parseDate(userInput1), parseDate(userInput2),
				parseDate(userInput1), parseDate(userInput2), campgroundName);

		while (results.next()) {
			Campsite theCampsite = mapRowToCampsite(results);
			openCampsiteList.add(theCampsite);

		}
		return openCampsiteList;
	}
	private Campsite mapRowToCampsite(SqlRowSet results) {
		Campsite theCampsite;
		theCampsite = new Campsite();
		theCampsite.setSiteId(results.getLong("site_id"));
		theCampsite.setCampgroundId(results.getInt("campground_id"));
		theCampsite.setSiteNumber(results.getInt("site_number"));
		theCampsite.setMaxOccupancy(results.getInt("max_occupancy"));
		theCampsite.setAccessible(results.getBoolean("accessible"));
		theCampsite.setMaximumLength(results.getInt("max_rv_length"));
		theCampsite.setUtilities(results.getBoolean("utilities"));
		theCampsite.setDailyFee(results.getBigDecimal("daily_fee"));

		return theCampsite;
	}
	@Override
	public Date parseDate(String userInput) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		Date date = null;
		try {
			date = (Date) formatter.parse(userInput);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
