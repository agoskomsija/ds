import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Ticket extends UnicastRemoteObject implements BingoCallback {
	
	String id;
	Vector<Integer> numbers;
	
	public Ticket(String id,Vector<Integer> numbers) throws RemoteException {
		super();
		this.id = id;
		this.numbers = numbers;
	}	

	@Override
	public void isWinner() throws RemoteException {
		System.out.println("You are the winner!");
	}
	
	
}
