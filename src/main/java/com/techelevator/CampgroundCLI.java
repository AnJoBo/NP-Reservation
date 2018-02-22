package com.techelevator;

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

public class CampgroundCLI {

	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private CampsiteDAO campsiteDAO;
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

	}

	public void run() {
		Scanner input = new Scanner(System.in);                                                                                                                
		List<Park> allParks = parkDAO.getAllParks();
		Park[] park = new Park[allParks.size()];
		park = allParks.toArray(park);		

		Park actualPark = (Park)menu.getChoiceFromOptions(park);
	
		String strDate = String.format("Established: %1$td %1$tB %1$tY", actualPark.getEstablishDate());
		System.out.printf("%n%s National Park%nLocation: %s %n%s %nArea: %d sq km%nAnnual Visitors: %d %n%n %s",
				actualPark.getName(), actualPark.getLocation(), strDate, actualPark.getArea(), actualPark.getVisitors(), actualPark.getDescription());
		
		List<Campground> allCamps = campgroundDAO.getAllCampgrounds(actualPark);
		Campground[] campground = new Campground[allCamps.size()];
		campground = allCamps.toArray(campground);	
		
//		System.out.printf("%n%n%12s %12s %10s %13s", "Name", "Open", "Close", "Daily Fee");
		Campground actualCampgrounds = (Campground)menu.getChoiceFromOptions(campground);
		
		System.out.println("What is the arrival date (yyyy-mm-dd)?");
		String variable2 = input.nextLine();
		
		System.out.println("What is the departure date (yyyy-mm-dd)?");
		String variable3 = input.nextLine();
		
		List<Campsite> allSite = campsiteDAO.getAllSites(actualCampgrounds);
		Campsite[] campsite = new Campsite[allSite.size()];
		campsite = allSite.toArray(campsite);	
		
		Campsite actualSites = (Campsite)menu.getChoiceFromOptions(campsite);
		
		// TO DO: figure out step 3A with our user inputs.
		
	}
	
//	public void displayApplicationBanner() {
//		System.out.println(" __    __              __      __                                __        _______                      __  " );              
//		System.out.println("//\\  /  |            /  |    /  |                              /  |      /       \                    /  |    ");            
//		System.out.println("$$  \ $$ |  ______   _$$ |_   $$/   ______   _______    ______  $$ |      $$$$$$$  | ______    ______  $$ |   __   _______ ");
//		System.out.println("$$$  \$$ | /      \ / $$   |  /  | /      \ /       \  /      \ $$ |      $$ |__$$ |/      \  /      \ $$ |  /  | /       |");
//		System.out.println("$$$$  $$ | $$$$$$  |$$$$$$/   $$ |/$$$$$$  |$$$$$$$  | $$$$$$  |$$ |      $$    $$/ $$$$$$  |/$$$$$$  |$$ |_/$$/ /$$$$$$$/ ");
//		System.out.println("$$ $$ $$ | /    $$ |  $$ | __ $$ |$$ |  $$ |$$ |  $$ | /    $$ |$$ |      $$$$$$$/  /    $$ |$$ |  $$/ $$   $$<  $$      \ ");
//		System.out.println("$$ |$$$$ |/$$$$$$$ |  $$ |/  |$$ |$$ \__$$ |$$ |  $$ |/$$$$$$$ |$$ |      $$ |     /$$$$$$$ |$$ |      $$$$$$  \  $$$$$$  |");
//		System.out.println("$$ | $$$ |$$    $$ |  $$  $$/ $$ |$$    $$/ $$ |  $$ |$$    $$ |$$ |      $$ |     $$    $$ |$$ |      $$ | $$  |/     $$/ ");
//		System.out.println("$$/   $$/  $$$$$$$/    $$$$/  $$/  $$$$$$/  $$/   $$/  $$$$$$$/ $$/       $$/       $$$$$$$/ $$/       $$/   $$/ $$$$$$$/ " );
//		System.out.println();
//	}
}
