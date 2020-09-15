import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Shape extends Remote {
	public int getVersion() throws RemoteException;
	GraphicObject getAllState() throws RemoteException;
}
