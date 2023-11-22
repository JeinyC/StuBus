package passengerPackage;

public class PassengerRoute {
	private String passengerDNI;
	private int idRoute;
	private int idPassengerRoute;

	public PassengerRoute(String passengerDNI, int idRoute, int idPassengerRoute) {

		this.passengerDNI = passengerDNI;
		this.idRoute = idRoute;
		this.idPassengerRoute = idPassengerRoute;
	}

	public PassengerRoute() {

	}

	public String getPassengerDNI() {
		return passengerDNI;
	}

	public void setPassengerDNI(String passengerDNI) {
		this.passengerDNI = passengerDNI;
	}

	public int getIdRoute() {
		return idRoute;
	}

	public void setIdRoute(int idRoute) {
		this.idRoute = idRoute;
	}

	public int getIdPassengerRoute() {
		return idPassengerRoute;
	}

	public void setIdPassengerRoute(int idPassengerRoute) {
		this.idPassengerRoute = idPassengerRoute;
	}

	@Override
	public String toString() {
		return "PassengerRoute [passengerDNI=" + passengerDNI + ", idRoute=" + idRoute + ", idPassengerRoute="
				+ idPassengerRoute + "]";
	}

}
