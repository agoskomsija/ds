import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HotelManager extends Remote {
	public Soba nadjiSobu(int maxPrice, int minNumOfBeds) throws RemoteException;
}
