import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {

	public CalculatorServer() {
		try {
			LocateRegistry.createRegistry(3333);
			Calculator c = new CalculatorImpl();
			Naming.rebind("rmi://localhost:3333/Calculator", c);
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CalculatorServer();
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
