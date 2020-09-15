import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class FacultyServer {
	public static void main(String[] args) {
		try {
			
			LocateRegistry.createRegistry(3030);
			FacultyManager fm = new FacultyManagerImpl();
			Naming.rebind("rmi://localhost:3030/Faculty", fm);
			System.in.read();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
