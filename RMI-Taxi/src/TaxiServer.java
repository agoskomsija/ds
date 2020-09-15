import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TaxiServer {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			TaxiManager tm = new TaxiManagerImpl();
			Naming.rebind("rmi://localhost:1099/Taxi",tm);
			
			System.in.read();
		}
		catch(Exception e) {
			
		}
	}
}
