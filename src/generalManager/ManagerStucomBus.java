package generalManager;

import java.util.Scanner;

import BBDDpackage.ConeccionBBDD;
import driverPackage.DriverManager;
import busPackage.BusManager;
import routePackage.RouteManager;
import generalManager.ManagerStucomBus;
import passengerPackage.Passenger;
import passengerPackage.PassengerManager;

public class ManagerStucomBus {

	private DriverManager manageDrivers;
	private BusManager manageBuses;
	private RouteManager routeManage;
	private PassengerManager passengerManager;
	private ConeccionBBDD bbdd;

	public ManagerStucomBus(DriverManager manageDrivers, BusManager manageBuses, RouteManager routeManage,
			ConeccionBBDD bbdd, PassengerManager passengerManager) throws Exception {
		this.manageDrivers = manageDrivers;
		this.manageBuses = manageBuses;
		this.routeManage = routeManage;
		this.passengerManager = passengerManager;
		this.bbdd = bbdd;
	}

	public ManagerStucomBus() throws Exception {

	}

	public void menu() throws Exception {
		Scanner sc = new Scanner(System.in);
		bbdd = new ConeccionBBDD();
		manageDrivers = new DriverManager(this);
		manageBuses = new BusManager(this);
		routeManage = new RouteManager(this);
		passengerManager = new PassengerManager(this);
		
		boolean salir = false;
		int count = 0;
		int general = generalMenu();

		if (general == 1) {
			salir = false;
			while (!salir) {
				System.out.println("----- The Administration menu -----");
				System.out.println("1. Register a driver");
				System.out.println("2. Unregister  a driver");
				System.out.println("3. Register a vehicle");
				System.out.println("4. Unregister a vehicle");
				System.out.println("5. Register a route");
				System.out.println("6. Unregister a route");
				System.out.println("7. Show the route list");
				System.out.println("8. OUT");
				int option1 = sc.nextInt();

				switch (option1) {
				case 1:
					manageDrivers.addDriver();
					break;
				case 2:
					manageDrivers.deleteDriver();
					break;
				case 3:
					manageBuses.addBus();
					break;
				case 4:
					manageBuses.deleteBus();
					break;
				case 5:
					routeManage.addRoute();
					break;
				case 6:
					routeManage.deleteRoute();
					break;
				case 7:
					getDatabase().showRouteList();
					break;
				case 8:
					salir = true;

					break;
				default:
					System.err.println("Insert a correct option");
				}
			}
			menu();
		}

		if (general == 2) {
			salir = false;
			while (!salir) {
				System.out.println("----- The Management menu -----");
				System.out.println("1. Assign a passenger to a route");
				System.out.println("2. Remove a passenger from a route");
				System.out.println("3. View passengers on a route");
				System.out.println("4. OUT");
				int option2 = sc.nextInt();

				switch (option2) {
				case 1:
					passengerManager.menuPassenger();
					break;
				case 2:
					passengerManager.removePassengerFromRoute();;
					break;
				case 3:
					passengerManager.showRoutes();
					break;
				case 4:
					salir = true;
					break;
				default:
					System.err.println("Insert a correct option");
				}
			}
			menu();
		}
	}

	public int generalMenu() {
		boolean salir = false;
		Scanner sc = new Scanner(System.in);
		int optionG = 0;
		while (!salir) {
			System.out.println("----- Geneal Menu -----");
			System.out.println("1. Administration");
			System.out.println("2. Management");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				return optionG = 1;
			case 2:
				return optionG = 2;
			default:
				System.err.println("Insert a correct option");
			}
		}
		return optionG;
	}

	public ConeccionBBDD getDatabase() {
		return this.bbdd;
	}
}