import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface BingoManager extends Remote {
	Ticket playTicket(Vector<Integer>  numbers) throws RemoteException;
	void drawNumber() throws RemoteException;
}
