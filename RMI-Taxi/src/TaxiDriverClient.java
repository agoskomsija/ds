import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TaxiDriverClient {

	class TaxiCallbackImpl extends UnicastRemoteObject implements TaxiCallback {

		protected TaxiCallbackImpl() throws RemoteException {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public void notifyTaxi() throws RemoteException {
			Print();
		}
		
	}
	
	void Print() {
		System.out.println("Sledeca adresa je: " + myTaxi.address);
	}
	
	Taxi myTaxi;
	public TaxiDriverClient() throws Exception {

		myTaxi = new Taxi(10,null,true);
		myTaxi.cb = new TaxiCallbackImpl();
		
		TaxiManager tm = (TaxiManager)Naming.lookup("rmi://localhost:1099/Taxi");
		tm.Register(myTaxi);
		
		tm.setTaxiStatus(myTaxi.id, true);
		
		System.in.read();
		
		
		tm.Unregister(myTaxi);
		
	}
	
	
	public static void main(String[] args) {
		try {
			new TaxiDriverClient();
		}
		catch(Exception e) {
			
		}
	}
	
}
