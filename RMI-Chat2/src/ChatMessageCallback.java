import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatMessageCallback extends Remote {
	void onMessage(ChatMessage msg) throws RemoteException;
}
