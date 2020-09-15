import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaxiCallback extends Remote {
	void notifyTaxi() throws RemoteException;
}
