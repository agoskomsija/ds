import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ParkingClient {
	public static void main(String[] args) {
		try {
			ParkingManager pm = (ParkingManager)Naming.lookup("rmi://localhost:1099/ParkingManager");
			pm.requestParkingTicket("111", 1, 2, 10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
