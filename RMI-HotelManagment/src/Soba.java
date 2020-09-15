import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Soba extends Remote {
	public int getPrice() throws RemoteException;
	public int getNumOfBeds() throws RemoteException;
	public boolean isFree() throws RemoteException;
	public void reserve(Putnik putnik) throws RemoteException;
	public String getInfo() throws RemoteException;
}
