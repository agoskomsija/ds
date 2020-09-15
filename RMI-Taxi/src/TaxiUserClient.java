import java.rmi.Naming;

public class TaxiUserClient {
	public static void main(String[] args) {
		try {
			TaxiManager tm = (TaxiManager)Naming.lookup("rmi://localhost:1099/Taxi");
			tm.requireTaxi("adress 1");
			tm.requireTaxi("adress 2");
			tm.requireTaxi("adress 3");
			tm.requireTaxi("adress 4");
			tm.requireTaxi("adress 5");
			tm.requireTaxi("adress 6");

		
		
		}
		catch(Exception e) {
			
		}
	}
}
