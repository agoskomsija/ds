import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Player extends UnicastRemoteObject implements CardCallback {
	String id,name;
	private int points;
	
	public Player(String id,String name) throws RemoteException {
		super();
		this.id = id;
		this.name = name;
		setPoints(0);
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

	public void callback() throws RemoteException {

		
	}
	
	
	
}
