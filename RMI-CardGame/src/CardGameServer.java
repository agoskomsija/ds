import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CardGameServer {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			CardGameManager cg = new CardGameManagerImpl();
			Naming.rebind("rmi://localhost:1099/CardGame", cg);
			
			System.in.read();
		}
		catch(Exception e) {
			
		}
	}
}
