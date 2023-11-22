package routePackage;

public class Route {
	private int id;
	private String licensePlate;
	private String driverDNI;
	private String departureDate;
	private String arrivalDate;
	private int originCity;
	private int destinyCity;
	
	public Route(int id, String licensePlate, String driverDNI, String departureDate, String arrivalDate,
			int originCity, int destinyCity) {
		
		this.id = id;
		this.licensePlate = licensePlate;
		this.driverDNI = driverDNI;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.originCity = originCity;
		this.destinyCity = destinyCity;
	}
	
	public Route() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getDriverDNI() {
		return driverDNI;
	}

	public void setDriverDNI(String driverDNI) {
		this.driverDNI = driverDNI;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getOriginCity() {
		return originCity;
	}

	public void setOriginCity(int originCity) {
		this.originCity = originCity;
	}

	public int getDestinyCity() {
		return destinyCity;
	}

	public void setDestinyCity(int destinyCity) {
		this.destinyCity = destinyCity;
	}

	@Override
	public String toString() {
		return "Route id = " + id + ", licensePlate = " + licensePlate + ", driverDNI = " + driverDNI + ", departureDate = "
				+ departureDate + ", arrivalDate = " + arrivalDate;
	}
	
	public String toStringSimple() {
		return "Route id = " + id + ", departureDate = "
				+ departureDate + ", arrivalDate = " + arrivalDate;
	}
	
	
	
}
