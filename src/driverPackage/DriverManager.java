package driverPackage;
import java.util.ArrayList;
import java.util.Scanner;
import generalManager.ManagerStucomBus;

public class DriverManager  {
	
	ArrayList<Driver> driverArraylist = new ArrayList<>();
	ManagerStucomBus manageSB;
	Driver driver ;
	
	public DriverManager(ManagerStucomBus mag) {
		manageSB = mag;
	}
	
	public void addDriver() throws Exception {
		String name;
		String surname;
		String id;
		Scanner sc = new Scanner(System.in);
		driver = new Driver();
		
		System.out.println("write dni");
		id = sc.next();
		
		if(manageSB.getDatabase().getDriverById(id) == false) {
			
			System.out.println("write name");
			name = sc.next();
			System.out.println("write surname");
			surname = sc.next();
			driver = new Driver(id, name, surname);
			manageSB.getDatabase().insertDrivers(driver);
		}else {
			System.err.println("<This driver already exist in the data base>");
		}
	}
	
	public void deleteDriver() throws Exception   {
		String id;
		Scanner sc = new Scanner(System.in);
		Driver driver = new Driver();
		
		System.out.println("write dni");
		id = sc.next();
		
		if(manageSB.getDatabase().getDriverById(id) == true) {
			driver = new Driver(id, "", "");
			manageSB.getDatabase().deleteDriver(driver);
		}else {
			System.err.println("<This driver do not exist in the data base>");
		}
	}
}