import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CardGameClient {

	
	CardGameManager cgm;
	
	public CardGameClient() {
		try {
			
			cgm = (CardGameManager) Naming.lookup("rmi://localhost:1099/CardGame");

			
			
			
		}
		catch(Exception e) {
			
		}
	}
	
	
	public static void main(String[] args) {
	
		new CardGameClient();
	
		try {
			System.in.read();
		}
		catch(Exception e) {
			
		}
	}

}
