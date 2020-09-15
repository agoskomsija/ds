import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BingoServer {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			BingoManager bm = new BingoManagerImpl();
			Naming.rebind("rmi://localhost:1099/Bingo", bm);
			
			System.in.read();
			bm.drawNumber();
			System.in.read();

		}
		catch(Exception e) {
			
		}
	}
}
