import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Scanner;

public class ChatAppClient {

	User user;
	
	public ChatAppClient(int id,String name) {
		try {
			user = new User(id,name,new ChatMessageCallbackImpl());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
	}

	
	public static void main(String[] args) {
		try {
			ChatAppManager cam = (ChatAppManager)Naming.lookup("rmi://localhost:1099/ChatApp");
			Scanner in = new Scanner(System.in);
			System.out.println("Unesite vas id:");
			int id = Integer.parseInt(in.nextLine());
			System.out.println("Unesite vase ime");
			String ime = in.nextLine();
			ChatAppClient cac = new ChatAppClient(id, ime);
			cam.Register(cac.user);
			
			
			String poruka = "";
			int zaId;
			while(poruka.compareTo("Exit") != 0) {
				System.out.println("Unesite poruku:");
				poruka = in.nextLine();
				System.out.println("id kome saljete");				
				zaId = Integer.parseInt(in.nextLine());
				Date d = new Date();
				cam.SendChatMessage(cac.user,cam.getUserById(zaId) , new ChatMessage(cac.user,cam.getUserById(zaId),poruka,d.getHours(),d.getMinutes()));
				
			}
			
			
			in.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void PrintMessage(ChatMessage message) {
		System.out.println(" " + message.fromUser.name + " :: " + message.hour + ":" + message.minute + " > " + message.message + "\n" );
	}
	
	class ChatMessageCallbackImpl extends UnicastRemoteObject implements ChatMessageCallback {

		public ChatMessageCallbackImpl() throws RemoteException {
			super();
			
		}
		
		public void onMessage(ChatMessage msg) throws RemoteException {
			PrintMessage(msg);
		}
	}
}
