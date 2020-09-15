import java.awt.Color;
import java.awt.Rectangle;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Vector;

public class WhiteboardClient {

	private WhiteboardManagment wbm;
	private WhiteboardCallback wbc;
	
	public WhiteboardClient() {
		try {
			wbm = (WhiteboardManagment)Naming.lookup("rmi://localhost:1099/Whiteboard");
			wbc = new WhiteboardCallbackImpl();
			wbm.register(wbc);
			
			
			Scanner s = new Scanner(System.in);
			
			while(true) {
				
				String input =s.nextLine().trim();		
				
				if (input.equals("EXIT")) break;
				
				GraphicObject g = new GraphicObject(input, new Rectangle(50,50,300,400),Color.red,Color.blue, false);
				System.out.println("Created graphical object");	
				wbm.newShape(g);		 				
			}
			
			s.close();
			
			wbm.unregister(wbc);
			
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	public static void main(String[] args) {
		
		try {
			new WhiteboardClient();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	public void ShowWhiteboard() {
		
		System.out.println("Whiteboard content");	
		 
		try {  			
			 Vector sList = wbm.allShapes();
			 for(int i=0; i < sList.size() ; i++) {
				 GraphicObject g = ((Shape)sList.elementAt(i)).getAllState();
				 g.Print();
			 }
		} 
		catch (Exception ioException) {     
		}    
	}
		
	class WhiteboardCallbackImpl extends UnicastRemoteObject implements WhiteboardCallback {

		public WhiteboardCallbackImpl() throws RemoteException {
			
		}
		
		public void Callback(int version) throws RemoteException {
			
			ShowWhiteboard();
		}
		
	}
}
