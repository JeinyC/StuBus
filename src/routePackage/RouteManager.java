package routePackage;

import java.util.ArrayList;
import java.util.Scanner;
import busPackage.Bus;
import driverPackage.Driver;
import generalManager.City;
import generalManager.ManagerStucomBus;

public class RouteManager {

	ArrayList<Route> routeArraylist = new ArrayList<>();
	ManagerStucomBus manageSB;
	Route route;
	City city;
	Bus bus;
	Driver driver;

	public RouteManager(ManagerStucomBus mg) {
		manageSB = mg;
	}

	public void addRoute() throws Exception {

		String id;
		String licensePlate;
		String driverDNI;
		int originCity;
		int destinyCity;
		int countLicensePlate = 0;
		int countDriver = 0;
		int count = 0;

		Scanner sc = new Scanner(System.in);
		route = new Route();

		do {
			count = 0;
			System.out.println("Enter the route id");
			id = sc.next();
			if (isNumeric(id) == true) {
				int idRoute = Integer.parseInt(id);
				route.setId(idRoute);
			} else {
				System.err.println("<The route id must be numeric>");
				count++;

			}
		} while (count > 0);

// asignar a una matricula
		do {
			count = 0;
			System.out.println("Assign a license plate");
			licensePlate = sc.next();

			for (Bus bus : manageSB.getDatabase().getBuses()) {
				if (bus.getId().equalsIgnoreCase(licensePlate)) {
					route.setLicensePlate(licensePlate);
					countLicensePlate++;
				}
			}

			if (countLicensePlate == 0) {
				System.err.println("<This bus do not exist in the data base>");
				count++;
			}
		} while (count > 0);

// asiganar a un conductor
		do {
			count = 0;
			System.out.println("Assign driver by DNI");
			driverDNI = sc.next();
			for (Driver driver : manageSB.getDatabase().getDrivers()) {
				if (driver.getId().equalsIgnoreCase(driverDNI)) {
					route.setDriverDNI(driverDNI);
					countDriver++;
				}
			}
			if (countDriver == 0) {
				System.err.println("<This driver do not exist in the data base>");
				count++;
			}
		} while (count > 0);

//asignar ciudad de origen
		do {
			count = 0;
			System.out.println("Choose a city of origin by ID");
			for (City city : manageSB.getDatabase().getCities()) {
				System.out.println(city.toString());
			}
			originCity = sc.nextInt();
			if (originCity == 1 || originCity == 2 || originCity == 3 || originCity == 4 || originCity == 5
					|| originCity == 6) {
				route.setOriginCity(originCity);
			} else {
				System.err.println("<This city do not exist in the data base>");
				count++;
			}
		} while (count > 0);

		do {
			count = 0;
			System.out.println("Choose destination city by ID");
			for (City city : manageSB.getDatabase().getCities()) {
				if (city.getId() != route.getOriginCity()) {
					System.out.println(city.toString());
				}
			}
			destinyCity = sc.nextInt();
			if (destinyCity == 1 || destinyCity == 2 || destinyCity == 3 || destinyCity == 4 || destinyCity == 5
					|| destinyCity == 6) {
				if (destinyCity != route.getOriginCity()) {
					route.setDestinyCity(destinyCity);
				} else {
					System.err.println("<This city has already been selected as the city of origin>");
					count++;
				}
			} else {
				System.err.println("<This city do not exist in the data base>");
				count++;
			}
		} while (count > 0);
		System.out.println("Enter the arrival date");
		route.setArrivalDate(sc.next());
		System.out.println("Enter departure date");
		route.setDepartureDate(sc.next());

		route = new Route(route.getId(), route.getLicensePlate(), route.getDriverDNI(), route.getArrivalDate(),
				route.getDepartureDate(), route.getOriginCity(), route.getDestinyCity());

		manageSB.getDatabase().insertRoute(route);
	}
	
	public void deleteRoute() throws Exception {
		manageSB.getDatabase().getRoutes();

		int id;
		Scanner sc = new Scanner(System.in);
		route = new Route();
		
		System.out.println("Write the Route ID");
		id = sc.nextInt();
		
		if(manageSB.getDatabase().getRouteById(id) == true) {
			route = new Route(id,null,null,null,null,0,0);
			manageSB.getDatabase().deleteRoute(route);
		}else {
		System.err.println("<This route do not exist in the data base>");
		}
	}

	private static boolean isNumeric(String id) {
		try {
			Integer.parseInt(id);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}