import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CardCallback extends Remote {
	public void callback() throws RemoteException;
}
