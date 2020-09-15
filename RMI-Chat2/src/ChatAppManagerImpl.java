import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Vector;

public class ChatAppManagerImpl extends UnicastRemoteObject implements ChatAppManager {

	HashMap<Integer,User> korisnici;
	Vector<ChatMessage> svePoruke;
	
	public ChatAppManagerImpl() throws RemoteException {
		super();
		korisnici = new HashMap<Integer,User>();
		svePoruke = new Vector<ChatMessage>();
	}
	
	public void SendChatMessage(User fromUser,User toUser,ChatMessage msg) throws RemoteException {
		/*if(korisnici.get(fromUser.id) == null)
			korisnici.put(fromUser.id,fromUser);
		if(korisnici.get(toUser.id) == null)
			korisnici.put(toUser.id,toUser);
		*/
		svePoruke.add(msg);
		toUser.callback.onMessage(msg);

	}
	public Vector<ChatMessage> GetChatMessages(User korinik,int hour, int minute) throws RemoteException {
		Vector<ChatMessage> result = new Vector<ChatMessage>();
		
		for(ChatMessage msg : svePoruke) 
			if(msg.toUser == korinik && msg.hour > hour && msg.minute > minute) 
				result.add(msg);
		
		return result;	
	}

	@Override
	public User getUserById(int id) throws RemoteException {
		return korisnici.get(id);
	}

	@Override
	public void Register(User korisnik) throws RemoteException {
		korisnici.put(korisnik.id,korisnik);
	}
	
}
