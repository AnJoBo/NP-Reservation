package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.project.model.Campground;
import com.techelevator.project.model.Campsite;
import com.techelevator.project.model.CampsiteJDBCDAO;

public class CampsiteJDBCDAOTest {
	private static SingleConnectionDataSource dataSource;
	private CampsiteJDBCDAO sut;
	private Long newCampsiteId;
	JdbcTemplate jdbcTemplate;
	Campsite newCampsite;
	private Long existingReservationId;
	private Campground camp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");

		dataSource.setAutoCommit(false);
		// Normally when you use the data source it automatically opens and closes a
		// transaction after each command
		// The above code will change this so that it does not auto commit after each
		// transaction
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dataSource.destroy();
	}

	@Before
	public void setUp() throws Exception {
		sut = new CampsiteJDBCDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlnewSite = "INSERT INTO site(campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) "
				+ "VALUES(?, ?, ?, ?, ?, ?) RETURNING site_id";
		newCampsiteId = jdbcTemplate.queryForObject(sqlnewSite, Long.class, 1, 1, 0, false, 0, false);

		String sqlNewReservation = "INSERT INTO reservation(site_id, name, from_date, to_date, create_date) "
				+ "VALUES(?, ?, ?, ?, ?) RETURNING reservation_id";
		existingReservationId = jdbcTemplate.queryForObject(sqlNewReservation, Long.class, newCampsiteId, "Mr. Death",
				LocalDate.parse("2018-01-01"), LocalDate.parse("2018-02-01"), LocalDate.parse("2018-01-01"));

	}

	@After
	public void tearDown() throws Exception {
		dataSource.getConnection().rollback();
	}

	@Test
	public void testGetSelectedCampsitesWithValidDate() {
		camp.setCampgroundId(1L);
		List<Campsite> newCampsites = sut.getOpenReservations("01/01/2018", "01/10/2018", camp);
		for (Campsite camp : newCampsites) {
			if (camp.getSiteId().equals(newCampsiteId)) {
				assertEquals(newCampsiteId, camp.getSiteId());
				return;
			}
		}

		fail("Site not returned");
	}
}