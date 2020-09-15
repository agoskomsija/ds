import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaxiManager extends Remote {
	boolean requireTaxi(String address) throws RemoteException;
	void setTaxiStatus(int id,boolean isFree) throws RemoteException;
	void Register(Taxi taxi) throws RemoteException;
	void Unregister(Taxi taxi) throws RemoteException;
}
