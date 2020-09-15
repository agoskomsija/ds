import java.rmi.Naming;

public class ParkingClient {

	public static void main(String[] args) {
		try {
			ParkingManager pm = (ParkingManager)Naming.lookup("rmi://localhost:1099/Parking");
			
			ParkingTicket result = pm.requestParkingTicket("NI999XX", 3, 12, 00);
			if(result == null) {
				System.out.println("Poslat zahtev");
				
			}
			else {
				System.out.println("Nepravilan zahtev");
			}
		}
		catch(Exception e) {
			
		}
	}
}
