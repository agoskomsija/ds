import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ParkingManager extends Remote {
	ParkingTicket requestParkingTicket(String reg,int zone,int hour,int minute) throws RemoteException;
	ParkingTicket extendParkingTicket(ParkingTicket ticket,int hour,int minute) throws RemoteException;
}
