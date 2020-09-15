import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WhiteboardCallback extends Remote {
	public void Callback(int version) throws RemoteException;
}
