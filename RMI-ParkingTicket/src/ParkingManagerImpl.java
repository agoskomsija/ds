import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;

public class ParkingManagerImpl extends UnicastRemoteObject implements ParkingManager {
	
	HashMap<String,ParkingTicket> tickets;
	
	public ParkingManagerImpl() throws RemoteException {
		super();
		tickets = new HashMap<String, ParkingTicket>();
	}
	
	public ParkingTicket requestParkingTicket(String reg,int zone,int hour,int minute) throws RemoteException {
		
		ParkingTicket result = new ParkingTicket(reg,zone,hour,minute);
		
		int h = new Date().getHours();
		int m = new Date().getMinutes();
		System.out.println("Now > " + h +":" + m);
		
		if(hour <= h && minute < m) {
			return result;
		}
		
		tickets.put(reg, result);
		
		return null;
	}
	public ParkingTicket extendParkingTicket(ParkingTicket ticket,int hour,int minute) throws RemoteException {
		
		int m = Math.abs(minute - ticket.minute);
		if(Math.abs(hour - ticket.hour) == 1 && (m >= 0 && m <= 15)) {
			
			ParkingTicket res = tickets.get(ticket.reg);
			res.hour ++;
			
			return null;
		}
		
		return ticket;
	}
}
