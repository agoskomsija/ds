import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ChatAppManager extends Remote {
	public void SendChatMessage(User formUser, User toUser, ChatMessage cmsg) throws RemoteException;
	public Vector<ChatMessage> GetChatMessages(User korisnik,int hour,int minute) throws RemoteException;
}
