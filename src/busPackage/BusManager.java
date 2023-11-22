package busPackage;

import java.util.ArrayList;
import java.util.Scanner;
import generalManager.ManagerStucomBus;


public class BusManager {
	ArrayList<Bus> busArraylist = new ArrayList<>();
	ManagerStucomBus manageSB;
	Bus bus;
	
	public BusManager(ManagerStucomBus mg) {
		manageSB = mg;
	}
	
	public void addBus() throws Exception {
		String id;
		int seats;
		Scanner sc = new Scanner(System.in);
		bus = new Bus();
		
		System.out.println("Write the license plate");
		id = sc.next();
		
		if(manageSB.getDatabase().getBusById(id) == false) {
			System.out.println("How many seats does it have?");
			seats = sc.nextInt();
			bus = new Bus(id,seats);
			manageSB.getDatabase().insertBus(bus);
		}else {
			System.err.println("<This bus already exist in the data base>");
		}
	}
	
	public void deleteBus() throws Exception   {
		String id;
		Scanner sc = new Scanner(System.in);
		Bus bus = new Bus();
		
		System.out.println("Write the license plate");
		id = sc.next();
		
		if(manageSB.getDatabase().getBusById(id) == true) {
			bus = new Bus(id, 0);
			manageSB.getDatabase().deleteBus(bus);
		}else {
			System.err.println("<This bus do not exist in the data base>");
		}
	}
	
	
}
