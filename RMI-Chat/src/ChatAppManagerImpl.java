import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

public class ChatAppManagerImpl extends UnicastRemoteObject implements ChatAppManager {
	
	ArrayList<ChatMessage> messages;
	ArrayList<User> users;
	
	public ChatAppManagerImpl() throws RemoteException {
		super();
		messages = new ArrayList<ChatMessage>();
		users = new ArrayList<User>();
	}
	
	public void SendChatMessage(User formUser, User toUser,ChatMessage cmsg) throws RemoteException {
		
		messages.add(cmsg);
		toUser.callback();
		
	}
	
	public Vector<ChatMessage> GetChatMessages(User korisnik,int hour,int minute) throws RemoteException {
		Vector<ChatMessage> myMessages = new Vector<ChatMessage>();
		for(ChatMessage m : messages) {
			if(m.toUser.id == korisnik.id) {
				myMessages.add(m);
			}
		}
		return myMessages;
	}
}
