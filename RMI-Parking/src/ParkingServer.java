import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ParkingServer {
	public static void main(String[] args) {
		try {
			
			LocateRegistry.createRegistry(1099);
			ParkingManager pm = new ParkingManagerImpl();
			Naming.rebind("rmi://localhost:1099/ParkingManager",pm);
			System.in.read();
		}
		catch(Exception e) {}
	}
}
