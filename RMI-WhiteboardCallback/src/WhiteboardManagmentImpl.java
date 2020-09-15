import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

public class WhiteboardManagmentImpl extends UnicastRemoteObject implements WhiteboardManagment {

	private int version;
	private Vector theList;
	private ArrayList<WhiteboardCallback> clients = new ArrayList<WhiteboardCallback>();
	
	public WhiteboardManagmentImpl() throws RemoteException {
		super();
		theList = new Vector();
		version = 0;
	}
	
	public Shape newShape(GraphicObject obj) throws RemoteException {
		
		version++;
		Shape s = new ShapeImpl(obj, version);
		theList.addElement(s);
		
		callback();
		
		return s;
	}

	public Vector allShapes() throws RemoteException {

		return theList;
	}

	public int getVersions() throws RemoteException {
		return version;
	}

	public synchronized void register(WhiteboardCallback cb) throws RemoteException {
		clients.add(cb);
	}

	public synchronized void unregister(WhiteboardCallback cb) throws RemoteException {
		clients.remove(cb);
	}
	
	private void callback() {
		for(WhiteboardCallback c : clients) {
			
			try {
				if(c != null) {
					c.Callback(version);
				}
			}
			catch(Exception e) {
				
			}
		}
	}
}