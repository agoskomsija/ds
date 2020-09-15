import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ShapeImpl extends UnicastRemoteObject implements Shape {

	int version;
	GraphicObject gobj;
	
	
	public ShapeImpl(GraphicObject gobj, int version) throws RemoteException {
		super();
		this.gobj = gobj;
		this.version = version;
	}
	
	public int getVersion() throws RemoteException {
		return version;
	}

	public GraphicObject getAllState() throws RemoteException {
		return gobj;
	}
	
}
