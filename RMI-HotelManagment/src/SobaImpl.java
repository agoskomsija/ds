import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SobaImpl extends UnicastRemoteObject implements Soba {

	private int price;
	private int numOfBeds;
	private boolean isReserved;
	private Putnik reservedFor;
	
	
	public SobaImpl(int price,int numOfBeds,boolean isReserved) throws RemoteException {
		super();
		this.price = price;
		this.numOfBeds = numOfBeds;
		this.isReserved = isReserved;
		this.reservedFor = null;
	}
	
	public int getPrice() throws RemoteException {
		return this.price;
	}

	public int getNumOfBeds() throws RemoteException {
		return this.numOfBeds;
	}

	public boolean isFree() throws RemoteException {
		return !this.isReserved;
	}

	public void reserve(Putnik putnik) throws RemoteException {
		this.isReserved = true;
		this.reservedFor = putnik;
	}

	public String getInfo() throws RemoteException {
		String result = "Price: " + this.price + " Num of beds: " + this.numOfBeds + " Reserved: " + this.isReserved;
		result += this.isReserved ? " Putnik: " + this.reservedFor.ime + " " + this.reservedFor.prezime : "";
		return result;
	}

}
