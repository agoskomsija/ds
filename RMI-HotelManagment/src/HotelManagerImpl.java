import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HotelManagerImpl extends UnicastRemoteObject implements HotelManager {

	private Soba[] sobe;
	
	public HotelManagerImpl() throws RemoteException {
		super();
		sobe = new Soba[3];
		
		sobe[0] = new SobaImpl(300,3,false);
		sobe[1] = new SobaImpl(600,2,false);
		sobe[2] = new SobaImpl(800,4,false);
		
	}
	
	public Soba nadjiSobu(int maxPrice, int minNumOfBeds) throws RemoteException {
		Soba result = null;
		for(int i = 0 ; i < 3 && result == null; i ++ ) {
			result = sobe[i].getPrice() <= maxPrice && sobe[i].getNumOfBeds() > minNumOfBeds && sobe[i].isFree() ? sobe[i] : null;
		}
		return result;
	}

}
