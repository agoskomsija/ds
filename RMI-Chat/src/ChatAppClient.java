import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Vector;

public class ChatAppClient {

	
	ChatAppManager app;
	User user;
	ChatAppCallback callback;
	
	public ChatAppClient() {
		try {
			int id = System.in.read();
			callback = new ChatAppCallbackImpl();
			user = new User(id,"Milan " + id,callback);
			app = (ChatAppManager)Naming.lookup("rmi://localhost:1099/Chat");
		
		}
		catch(Exception e) {
			
		}
		
	}
	
	public static void main(String[] args) {
		
		new ChatAppClient();
		
		
	}
	
	void printMessages(Vector<ChatMessage> c) {
		for(ChatMessage m: c) {
			System.out.println(" > " + m.hour + ":" + m.minute + " :: " + m.fromUser.name + ":" + m.msg);
		}
	}
	
	class ChatAppCallbackImpl extends UnicastRemoteObject implements ChatAppCallback {

		protected ChatAppCallbackImpl() throws RemoteException {
			super();
		
		}

		public void onChatMessage() throws RemoteException {
			Date d = new Date();
			Vector<ChatMessage> c = app.GetChatMessages(user, d.getHours(), d.getMinutes());
			printMessages(c);
			
		}
		
	}
}
