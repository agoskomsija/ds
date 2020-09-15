import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class WhiteboardServer {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			WhiteboardManagment wm = new WhiteboardManagmentImpl();
			Naming.rebind("rmi://localhost:1099/Whiteboard", wm);
			
			System.in.read();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}