package passengerPackage;

import java.util.ArrayList;
import java.util.Scanner;

import driverPackage.Driver;
import generalManager.ManagerStucomBus;
import routePackage.Route;

public class PassengerManager {
	ArrayList<Passenger> passengerArraylist = new ArrayList<>();
	ManagerStucomBus manageSB;
	Passenger passenger;

	public PassengerManager(ManagerStucomBus mag) {
		manageSB = mag;
	}

	public void addPassenger() throws Exception {

		String name;
		String surname;
		String id;

		Scanner sc = new Scanner(System.in);
		passenger = new Passenger();
		System.out.println("---------------------- Add Passenger ----------------------");
		System.out.println("Write id");
		id = sc.next();

		if (manageSB.getDatabase().getPassengerById(id) == false) {

			System.out.println("Write name");
			name = sc.next();
			System.out.println("Write surname");
			surname = sc.next();
			passenger = new Passenger(id, name, surname);
			manageSB.getDatabase().insertPassenger(passenger);
			assignPassengerToRoute(passenger);

		} else {
			System.err.println("<This passenger already exist in the data base>");
		}
	}

	public void menuPassenger() throws Exception {
		boolean salir = false;
		Scanner sc = new Scanner(System.in);

		while (!salir) {
			System.out.println("----- The Management menu -----");
			System.out.println("1. Add new passenger");
			System.out.println("2. Add existing passenger");
			System.out.println("3. OUT");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				addPassenger();
				break;
			case 2:
				selectExistingPassenger();
				break;
			case 3:
				salir = true;
				break;
			default:
				System.err.println("Insert a correct option");
			}
		}
	}

	public void selectExistingPassenger() throws Exception {
		String id;
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------- Select a Passenger ----------------------");
		System.out.println("Write id");
		id = sc.next();

		if (manageSB.getDatabase().getPassengerById(id) == true) {
			for (Passenger x : manageSB.getDatabase().getPassenger()) {
				if (x.getId().equalsIgnoreCase(id)) {
					assignPassengerToRoute(x);
				}
			}
		} else {
			System.err.println("<This passenger not exist in the data base>");
		}
	}

	private void assignPassengerToRoute(Passenger passenger) throws Exception {
		Scanner sc = new Scanner(System.in);
		int id;

		manageSB.getDatabase().showRouteList();
		System.out.println("Chosse a route by the ID");
		id = sc.nextInt();
		if (manageSB.getDatabase().getRouteById(id) == true) {
			manageSB.getDatabase().assingPassengerToRoute(passenger, id);
		} else {
			System.out.println("This route do not exist");
		}
	}

	public void removePassengerFromRoute() throws Exception {
		Scanner sc = new Scanner(System.in);
		String dni;
		PassengerRoute Pr = new PassengerRoute();

		System.out.println("---------------------- Passenger list by route ----------------------");
		showRoutes();
		System.out.println("---------------------- All passengers ----------------------");
		showAllpassengers();
		System.out.println("Select the passenger by DNI");
		dni = sc.next();

		if (manageSB.getDatabase().getPassengerRouteById(dni) == true) {
			Pr = new PassengerRoute(dni, 0, 0);
			manageSB.getDatabase().deletePassengerRoute(Pr);
			for (Passenger y : manageSB.getDatabase().getPassenger()) {
				if (y.getId().equalsIgnoreCase(dni)) {
					manageSB.getDatabase().deletePassenger(y);
				}
			}
		} else {
			System.err.println("<This passenger is not associated with any route>");
			for (Passenger y : manageSB.getDatabase().getPassenger()) {
				if (y.getId().equalsIgnoreCase(dni)) {
					manageSB.getDatabase().deletePassenger(y);
				}
			}
		}
	}

	public void showAllpassengers() throws Exception {
		for (Passenger y : manageSB.getDatabase().getPassenger()) {
			System.out.println(y.toString());
		}
	}

	public void showRoutes() throws Exception {
		for (PassengerRoute z : manageSB.getDatabase().getPassengerRoute()) {
			for (Route x : manageSB.getDatabase().getRoutes()) {
				if (z.getIdRoute() == x.getId()) {
					System.out.println(x.toStringSimple());
					for (Passenger y : manageSB.getDatabase().getPassenger()) {
						if (z.getPassengerDNI().equalsIgnoreCase(y.getId())) {
							System.out.println(y.toString());
						}
					}
				}
			}
		}
	}
}
