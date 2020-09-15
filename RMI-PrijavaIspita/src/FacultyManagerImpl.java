import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class FacultyManagerImpl extends UnicastRemoteObject implements FacultyManager {
	
	HashMap<String, Ispit> ispiti;
	
	public FacultyManagerImpl() throws RemoteException {
		super();
		ispiti = new HashMap<String, Ispit>();
		ispiti.put("1", new IspitImpl("1", "Distribuirani Sistemi"));
		ispiti.put("2", new IspitImpl("2", "Mikroracunarski Sistemi"));
		ispiti.put("3", new IspitImpl("3", "Racunarski Sistemi"));
		ispiti.put("4", new IspitImpl("4", "Operativni Sistemi"));
		
	}

	@Override
	public Ispit NadjiIspit(String sifra) throws RemoteException {
		return ispiti.get(sifra);
	}
}
