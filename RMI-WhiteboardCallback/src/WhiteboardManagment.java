import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface WhiteboardManagment extends Remote {
	public Shape newShape(GraphicObject obj) throws RemoteException;
	Vector allShapes() throws RemoteException;
	int getVersions() throws RemoteException;
	void register(WhiteboardCallback cb) throws RemoteException;
	void unregister(WhiteboardCallback cb) throws RemoteException;
}
