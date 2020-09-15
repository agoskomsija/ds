import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatAppCallback extends Remote {
	public void onChatMessage() throws RemoteException;
}
