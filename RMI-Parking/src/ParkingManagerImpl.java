import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;

public class ParkingManagerImpl extends UnicastRemoteObject implements ParkingManager {
	HashMap<String, ParkingTicket> tickets;
	public ParkingManagerImpl() throws RemoteException {
		super();
		tickets = new HashMap<String, ParkingTicket>();
	}
	
	public ParkingTicket requestParkingTicket(String reg,int zone,int hour,int minute) throws RemoteException {
		if(!tickets.containsKey(reg))
		{
			tickets.put(reg,new ParkingTicket(reg,zone,hour,minute));
			return tickets.get(reg);
		}
		else
		{
			Date d = new Date();
			int hoursNow = d.getHours();
			int minutesNow = d.getMinutes();
			if(hour > hoursNow)
				return null;
			else
			{
				tickets.replace(reg,new ParkingTicket(reg,zone,hour,minute));
				return tickets.get(reg);
			}
		}
	}
	public ParkingTicket extendParkingTicket(ParkingTicket ticket,int hour,int minute) throws RemoteException {
		
		
		return null;
	}
}