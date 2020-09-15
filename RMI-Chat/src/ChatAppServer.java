import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ChatAppServer {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			ChatAppManager cam = new ChatAppManagerImpl();
			Naming.rebind("rmi://localhost:1099/Chat", cam);
			
			System.in.read();
		}
		catch(Exception e) {
			
		}
		
	}
}
