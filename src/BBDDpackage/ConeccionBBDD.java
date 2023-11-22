package BBDDpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import busPackage.Bus;
import driverPackage.Driver;
import generalManager.City;
import passengerPackage.Passenger;
import passengerPackage.PassengerRoute;
import routePackage.Route;

public class ConeccionBBDD {

	private String user = "root";
	private String password = "";
	private String ruta = "jdbc:mysql://localhost:3306/stucombus?serverTimezone=UTC";
	Connection con = null;

	public Connection getCon() throws Exception {
	    this.con = DriverManager.getConnection(this.ruta, this.user, this.password);
		return con;
	}

	public ArrayList<Driver> getDrivers() throws Exception {
		con = getCon();
		Driver driver;
		ArrayList<Driver> driverList = new ArrayList<Driver>();
		
		PreparedStatement statment = con.prepareStatement(ConstantQuery.GET_DRIVER);
		ResultSet result = statment.executeQuery();

		while (result.next()) {
			driver = new Driver();
			String id = result.getString("DNI");
			String name = result.getString("NOMBRE");
			String surname = result.getString("APELLIDO");
			driver.setId(id);
			driver.setName(name);
			driver.setSurname(surname);
			driverList.add(driver);
		}
		return driverList;
	}

	public ArrayList<Bus> getBuses() throws Exception {
		con = getCon();
		Bus bus;
		ArrayList<Bus> busArrayList = new ArrayList<>();
		
		PreparedStatement statment = con.prepareStatement(ConstantQuery.GET_VEHICLE);
		ResultSet result = statment.executeQuery();

		while (result.next()) {
			bus = new Bus();

			String id = result.getString("MATRICULA");
			int seating = result.getInt("ASIENTOS");

			bus.setId(id);
			bus.setSeating(seating);
			busArrayList.add(bus);

		}
		return busArrayList;
	}

	public ArrayList<Route> getRoutes() throws Exception {
		con = getCon();
		Route route;
		ArrayList<Route> routeArrayList = new ArrayList<>();
		
		PreparedStatement statment = con.prepareStatement(ConstantQuery.GET_ROUTE);
		ResultSet result = statment.executeQuery();

		while (result.next()) {
			route = new Route();

			int id = result.getInt("IDRUTA");
			String license = result.getString("MATRICULA");
			String driverDNI = result.getString("CONDUCTORDNI");
			String departureDate = result.getString("FECHA_SALIDA");
			String arrivalDate = result.getString("FECHA_LLEGADA");
			int originCity = result.getInt("CIUDAD_ORIGEN");
			int destinyCity = result.getInt("CIUDAD_DESTINO");

			route.setId(id);
			route.setLicensePlate(license);
			route.setDriverDNI(driverDNI);
			route.setDepartureDate(departureDate);
			route.setArrivalDate(arrivalDate);
			route.setOriginCity(originCity);
			route.setDestinyCity(destinyCity);
			routeArrayList.add(route);
		}
		return routeArrayList;
	}

	public ArrayList<City> getCities() throws Exception {
		City city;
		ArrayList<City> cityArrayList = new ArrayList<>();
		con = getCon();
		PreparedStatement statment = con.prepareStatement(ConstantQuery.GET_CITY);
		ResultSet result = statment.executeQuery();

		while (result.next()) {
			city = new City();

			int id = result.getInt("ID");
			String name = result.getString("NOMBRE");

			city.setId(id);
			city.setName(name);
			cityArrayList.add(city);
		}
		return cityArrayList;
	}

	public ArrayList<Passenger> getPassenger() throws Exception {
		con = getCon();
		Passenger passenger;
		ArrayList<Passenger> passengerArrayList = new ArrayList<>();
		
		PreparedStatement statment = con.prepareStatement(ConstantQuery.GET_PASSENGER);
		ResultSet result = statment.executeQuery();

		while (result.next()) {
			passenger = new Passenger();

			String id = result.getString("DNI");
			String name = result.getString("NOMBRE");
			String surname = result.getString("APELLIDO");

			passenger.setId(id);
			passenger.setName(name);
			passenger.setSurname(surname);
			passengerArrayList.add(passenger);
		}
		return passengerArrayList;
	}

	public ArrayList<PassengerRoute> getPassengerRoute() throws Exception {
		con = getCon();
		PassengerRoute passengerRoute;
		ArrayList<PassengerRoute> passengerRouteArrayList = new ArrayList<>();
		
		PreparedStatement statment = con.prepareStatement(ConstantQuery.GET_PASSENGER_ROUTE);
		ResultSet result = statment.executeQuery();

		while (result.next()) {
			passengerRoute = new PassengerRoute();

			String passengerDNI = result.getString("DNIPASAJERO");
			int idRoute = result.getInt("IDRUTA");
			int idPassengerRoute = result.getInt("IDPASAJERORUTA");

			passengerRoute.setPassengerDNI(passengerDNI);
			passengerRoute.setIdRoute(idRoute);
			passengerRoute.setIdPassengerRoute(idPassengerRoute);
			passengerRouteArrayList.add(passengerRoute);
		}
		return passengerRouteArrayList;
	}

	public void showRouteList() throws Exception {
		con = getCon();
		String origin = null;
		String destiny = null;
		System.out.println("---------------------- Routes list ----------------------");
		for (Route x : getRoutes()) {
			for (City y : getCities()) {
				if (x.getOriginCity() == y.getId()) {
					origin = y.getName();
				}
				if (x.getDestinyCity() == y.getId()) {
					destiny = y.getName();
				}
			}
			System.out.println(x.toString() + ", OriginCity = " + origin + ", DestinyCity = " + destiny);
		}
		close();
	}

	public boolean getDriverById(String dni) throws Exception {
		for (Driver x : getDrivers()) {
			if (x.getId().equalsIgnoreCase(dni))
				return true;
		}
		
		return false;
	}

	public boolean getBusById(String id) throws Exception {
		for (Bus x : getBuses()) {
			if (x.getId().equalsIgnoreCase(id))
				return true;
		}
		
		return false;
	}

	public boolean getRouteById(int id) throws Exception {
		for (Route x : getRoutes()) {
			if (x.getId() == id) {
				return true;
			}
		}
		
		return false;
	}

	public boolean getPassengerById(String id) throws Exception {
		for (Passenger x : getPassenger()) {
			if (x.getId().equalsIgnoreCase(id)) {
				return true;
			}
		}
		
		return false;
	}

	public boolean getPassengerRouteById(String id) throws Exception {
		for (PassengerRoute x : getPassengerRoute()) {
			if (x.getPassengerDNI().equalsIgnoreCase(id)) {
				return true;
			}
		}
		
		return false;
	}

	public void insertDrivers(Driver driver) throws Exception {
		con = getCon();
		PreparedStatement statment = con.prepareStatement(ConstantQuery.INSERT_DRIVER);
		statment.setString(1, driver.getId());
		statment.setString(2, driver.getName());
		statment.setString(3, driver.getSurname());
		statment.executeUpdate();
		System.out.println("< Driver added to database >");
		close();
	}

	public void insertBus(Bus bus) throws Exception {
		con = getCon();
		PreparedStatement statment = con.prepareStatement(ConstantQuery.INSERT_VEHICLE);
		statment.setString(1, bus.getId());
		statment.setInt(2, bus.getSeating());
		statment.executeUpdate();
		System.out.println("< Bus added to database >");
		close();
	}

	public void insertRoute(Route route) throws Exception {
		con = getCon();
		PreparedStatement statment = con.prepareStatement(ConstantQuery.INSERT_ROUTE);
		statment.setInt(1, route.getId());
		statment.setString(2, route.getLicensePlate());
		statment.setString(3, route.getDriverDNI());
		statment.setString(4, route.getDepartureDate());
		statment.setString(5, route.getArrivalDate());
		statment.setInt(6, route.getOriginCity());
		statment.setInt(7, route.getDestinyCity());
		statment.executeUpdate();
		System.out.println("< Route added to database >");
		close();
	}

	public void insertPassenger(Passenger passenger) throws Exception {
		con = getCon();
		PreparedStatement statment = con.prepareStatement(ConstantQuery.INSERT_PASSENGER);
		statment.setString(1, passenger.getId());
		statment.setString(2, passenger.getName());
		statment.setString(3, passenger.getSurname());
		statment.executeUpdate();
		System.out.println("< Passenger added to database >");
		close();
	}

	public void deleteDriver(Driver driver) throws Exception {
		con = getCon();

		if (associateDriver(driver) == false) {
			PreparedStatement statment = con.prepareStatement(ConstantQuery.DELETE_DRIVER);
			statment.setString(1, driver.getId());
			statment.executeUpdate();
			System.out.println("< Driver deleted of database >");
		} else {
			System.err.println("< This driver has an associated route >");
		}
		close();
	}

	public void deleteBus(Bus bus) throws Exception {
		con = getCon();

		if (associateBus(bus) == false) {
			PreparedStatement statment = con.prepareStatement(ConstantQuery.DELETE_VEHICLE);
			statment.setString(1, bus.getId());
			statment.executeUpdate();
			System.out.println("< Bus deleted of database >");
		} else {
			System.err.println("< This bus has an associated route >");
		}
		close();
	}

	public void deleteRoute(Route route) throws Exception {
		con = getCon();

		PreparedStatement statment = con.prepareStatement(ConstantQuery.DELETE_ROUTE);
		statment.setInt(1, route.getId());
		statment.executeUpdate();
		System.out.println("< Driver deleted of database >");
		close();
		
	}

	public void deletePassenger(Passenger passenger) throws Exception {
		con = getCon();

		PreparedStatement statment = con.prepareStatement(ConstantQuery.DELETE_PASSENGER);
		statment.setString(1, passenger.getId());
		statment.executeUpdate();
		System.out.println("< Passenger deleted of database >");
		close();
		
	}

	public void deletePassengerRoute(PassengerRoute pr) throws Exception {
		con = getCon();

		PreparedStatement statment = con.prepareStatement(ConstantQuery.DELETE_PASSENGER_ROUTE);
		statment.setString(1, pr.getPassengerDNI());
		statment.executeUpdate();
		System.out.println("< Passenger deleted of Route >");
		close();
		
	}

	private boolean associateDriver(Driver driver) throws Exception {
		for (Route x : getRoutes()) {
			if (x.getDriverDNI().equalsIgnoreCase(driver.getId())) {
				return true;
			}
		}
		return false;
	}

	private boolean associateBus(Bus bus) throws Exception {
		for (Route x : getRoutes()) {
			if (x.getDriverDNI().equalsIgnoreCase(bus.getId())) {
				return true;
			}
		}
		return false;
	}

	public void assingPassengerToRoute(Passenger passenger, int id) throws Exception {
		con = getCon();
		int count = 0;

		for (Route y : getRoutes()) {
			if (y.getId() == id) {
				for (Bus x : getBuses()) {
					if (y.getLicensePlate().equalsIgnoreCase(x.getId())) {
						if (x.getSeating() > 0) {
							PreparedStatement statment = con.prepareStatement(ConstantQuery.INSERT_ROUTE_PASSENGER);
							statment.setString(1, passenger.getId());
							statment.setInt(2, id);
							statment.setInt(3, createAutomaticID());
							statment.executeUpdate();
							System.out.println("< Passenger added to route >");
							subtractSeating(x);
						} else {
							count++;
						}
					}
				}
			}
		}
		if (count > 0) {
			System.out.println("< There is no seats in this bus >");
		}
		close();
	}

	private int createAutomaticID() throws Exception {
		con = getCon();
		ArrayList<String> passengerRoute = new ArrayList<String>();
		PreparedStatement statment = con.prepareStatement(ConstantQuery.GET_ID_PASSENGER_ROUTE);
		ResultSet result = statment.executeQuery();

		while (result.next()) {
			String id = result.getString("IDPASAJERORUTA");
			passengerRoute.add(id);
		}
		return passengerRoute.size() + 1;
	}

	private void subtractSeating(Bus x) throws Exception {
		con = getCon();
		int seatUpdate = x.deleteSeating(1);
		PreparedStatement sentencia = con.prepareStatement(ConstantQuery.UPDATE_SEATS);
		sentencia.setInt(1, seatUpdate);
		sentencia.setString(2, x.getId());
		sentencia.executeUpdate();
		close();
	}

	public void close() throws SQLException {
		if (!con.isClosed())
			con.close();
	}

}
