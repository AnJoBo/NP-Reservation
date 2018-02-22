package com.techelevator.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class CampsiteJDBCDAO implements CampsiteDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public CampsiteJDBCDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campsite> getAllSites(Campground actualCampground) {
			List<Campsite> campsiteList = new ArrayList<>();
			
			String sqlGetSite = "Select * FROM site  WHERE campground_id = ?"
					+ "LIMIT 5";
			
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSite, actualCampground.getCampgroundId());
			
			while (results.next()) {
				Campsite theCampsite = mapRowToParks(results);
				campsiteList.add(theCampsite);
				
			}
			return campsiteList;
		}
	private Campsite mapRowToParks(SqlRowSet results) {
		Campsite theCampsite;
		theCampsite = new Campsite();
		theCampsite.setSiteId(results.getLong("site_number"));
		theCampsite.setCampgroundId(results.getInt("campground_id"));
		theCampsite.setSiteNumber(results.getInt("site_number"));
		theCampsite.setMaxOccupancy(results.getInt("max_occupancy"));
		theCampsite.setAccessible(results.getBoolean("accessible"));
		theCampsite.setMaximumLength(results.getInt("max_rv_length"));
		theCampsite.setUtilities(results.getBoolean("utilities"));

		return theCampsite;
	}
}
