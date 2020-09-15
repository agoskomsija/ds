import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class HotelServer {
	public HotelServer() {
		try {
			LocateRegistry.createRegistry(3030);
			HotelManager hm = new HotelManagerImpl();
			Naming.rebind("rmi://localhost:3030/HotelManager", hm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}	
	}
	
	public static void main(String[] args) {
		
		new HotelServer();
		
		try {
			System.in.read();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
