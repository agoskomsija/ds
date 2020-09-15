import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ChatAppManager extends Remote {
	void SendChatMessage(User fromUser,User toUser,ChatMessage msg) throws RemoteException;
	Vector<ChatMessage> GetChatMessages(User korinik,int hour, int minute) throws RemoteException;
	User getUserById(int id) throws RemoteException;
	void Register(User korisnik) throws RemoteException;
}
