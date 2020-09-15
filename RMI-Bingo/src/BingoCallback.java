import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BingoCallback extends Remote {
	public void isWinner() throws RemoteException;
}
