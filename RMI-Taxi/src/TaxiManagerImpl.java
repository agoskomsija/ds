import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class TaxiManagerImpl extends UnicastRemoteObject implements TaxiManager {

	ArrayList<String> adrese;
	ArrayList<Taxi> taksi;
	
	protected TaxiManagerImpl() throws RemoteException {
		super();
		
		adrese = new ArrayList<String>();
		taksi = new ArrayList<Taxi>();
		
	}

	@Override
	public boolean requireTaxi(String address) throws RemoteException {
		
		for(Taxi item : taksi) {
			if(item.isFree) {
				item.isFree = false;
				item.address = address;
				item.cb.notifyTaxi();
				return true;
			}
		}
		
		adrese.add(address);
		
		return false;
	}

	@Override
	public void setTaxiStatus(int id, boolean isFree) throws RemoteException {
		Taxi result = null;
		for(Taxi item : taksi)
			if(item.id == id) {
				result = item;
				break;
			}
		
		if(isFree && adrese.size() > 0)
		{
				result.address = adrese.get(0);
				adrese.remove(0);				
				result.cb.notifyTaxi();
				return;		
		}
		else {
			result.isFree = isFree;
		}
		
		
	}

	@Override
	public void Register(Taxi taxi)  throws RemoteException{
		// TODO Auto-generated method stub
		taksi.add(taxi);
		setTaxiStatus(taxi.id,true);
	}

	@Override
	public void Unregister(Taxi taxi)  throws RemoteException {
		// TODO Auto-generated method stub
		for(Taxi item : taksi) {
			if(item.id == taxi.id) {
				taksi.remove(item.id);
				break;
			}
		}

	}

}
