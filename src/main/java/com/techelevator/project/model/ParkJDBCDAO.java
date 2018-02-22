package com.techelevator.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ParkJDBCDAO  implements ParkDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public ParkJDBCDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parksList = new ArrayList<>();
		
		String sqlGetParks = "Select * FROM park";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParks);
		
		while (results.next()) {
			Park thePark = mapRowToParks(results);
			parksList.add(thePark);
			
		}
		return parksList;
	}


	private Park mapRowToParks(SqlRowSet results) {
		Park thePark;
		thePark = new Park();
		thePark.setId(results.getLong("park_id"));
		thePark.setName(results.getString("name"));
		thePark.setLocation(results.getString("location"));
		thePark.setEstablishDate(results.getDate("establish_date"));
		thePark.setArea(results.getInt("area"));
		thePark.setVisitors(results.getInt("visitors"));
		thePark.setDescription(results.getString("description"));

		return thePark;
	}

	@Override
	public List<String> getParkInfo() {
		List<String> parkInfo = new ArrayList<>();
		
		String sqlInfo = "SELECT * FROM park WHERE name = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlInfo);
		
		while(results.next()) {
			Park thePark = mapRowToParks(results);
			parkInfo.add(thePark.toString());
		}
		return parkInfo;
	}
	
	
}
