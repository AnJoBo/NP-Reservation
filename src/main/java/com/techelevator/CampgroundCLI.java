package com.techelevator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.project.model.Campground;
import com.techelevator.project.model.CampgroundDAO;
import com.techelevator.project.model.CampgroundJDBCDAO;
import com.techelevator.project.model.Campsite;
import com.techelevator.project.model.CampsiteDAO;
import com.techelevator.project.model.CampsiteJDBCDAO;
import com.techelevator.project.model.Park;
import com.techelevator.project.model.ParkDAO;
import com.techelevator.project.model.ParkJDBCDAO;
import com.techelevator.project.model.Reservation;
import com.techelevator.project.model.ReservationDAO;
import com.techelevator.project.model.ReservationJDBCDAO;

public class CampgroundCLI {

	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private CampsiteDAO campsiteDAO;
	private ReservationDAO reservationDAO;
	private Menu menu;

	public static void main(String[] args) {

		CampgroundCLI application = new CampgroundCLI();
		application.run();
	}

	public CampgroundCLI() {
		this.menu = new Menu(System.in, System.out);

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");

		parkDAO = new ParkJDBCDAO(dataSource);
		campgroundDAO = new CampgroundJDBCDAO(dataSource);
		campsiteDAO = new CampsiteJDBCDAO(dataSource);
		reservationDAO = new ReservationJDBCDAO(dataSource);

	}

	public void run() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nSelect a Park for Further Details");
		List<Park> parkList = parkDAO.getAllParks();
		Park[] parkArray = new Park[parkList.size()];
		parkArray = parkList.toArray(parkArray);		

		Park chosenPark = (Park)menu.getChoiceFromOptions(parkArray);
	
		String strDate = String.format("Established: %1$td %1$tB %1$tY", chosenPark.getEstablishDate());
		System.out.printf("%n%s National Park%nLocation: %s %n%s %nArea: %d sq km%nAnnual Visitors: %d %n%n %s%n",
				chosenPark.getName(), chosenPark.getLocation(), strDate, chosenPark.getArea(), chosenPark.getVisitors(), chosenPark.getDescription());
		
		List<Campground> campgroundList = campgroundDAO.getAllCampgrounds(chosenPark);
		Campground[] campgroundArray = new Campground[campgroundList.size()];
		campgroundArray = campgroundList.toArray(campgroundArray);	
		
		System.out.println("\nSelect a Campground");
		System.out.printf("%12s %12s %10s %13s", "Name", "Open", "Close", "Daily Fee");
		Campground chosenCampground = (Campground)menu.getChoiceFromOptions(campgroundArray);
		
		
		System.out.println("What is the arrival date (mm/dd/yyyy)?");
		String arrivalString = input.nextLine();
		
		System.out.println("What is the departure date (mm/dd/yyyy)?");
		String departureString = input.nextLine();
		
		
		List<Reservation> reservationList = reservationDAO.checkReservations(arrivalString, departureString, chosenCampground.getName());
		List<Campsite> campsiteList = campsiteDAO.getOpenReservations(arrivalString, departureString, chosenCampground.getName());
		
		Campsite[] campsiteArray = new Campsite[campsiteList.size()];
		campsiteArray = campsiteList.toArray(campsiteArray);
		
		//System.out.println("\nSelect a Campground");
		//System.out.printf("%12s %12s %10s %13s", "Name", "Open", "Close", "Daily Fee");
		Campsite availableCampsites = (Campsite)menu.getChoiceFromOptions(campsiteArray);

		
		if (reservationList.size() > 0) {
			System.out.println("Sorry there are no available sites, please choose another date range.");
			
		}
		else {
			System.out.println(campsiteList);
		}
		
		
	}
	
//	public BigDecimal handleTotalFee(String start, String end, BigDecimal dailyFee) {
//		LocalDate arrivalDate = parseDate(start);
//		LocalDate departureDate = parseDate(end);
//
//		long days = ChronoUnit.DAYS.between(arrivalDate, departureDate);
//		
//		BigDecimal totalDays = new BigDecimal(days);
//		BigDecimal totalFee = totalDays.multiply(dailyFee);
//		return totalFee;
//	}
//	
//	public LocalDate parseDate(String userInput) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//
//		LocalDate date = LocalDate.parse(userInput, formatter);
//		return date;
//	}
//	public LocalDate parseLocalDate(String input) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//		LocalDate date = null;
//		date = (LocalDate) formatter.parse(input);
//		return date;
//	}
}
	

