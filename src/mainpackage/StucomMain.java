package mainpackage;
import generalManager.ManagerStucomBus;

public class StucomMain {
	
	public static void main(String[] args) throws Exception {
		ManagerStucomBus managerSB = null;
		try {
			managerSB = new ManagerStucomBus();
			managerSB.menu();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			managerSB.menu();
		}
	}
}