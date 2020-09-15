import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

public class BingoManagerImpl extends UnicastRemoteObject implements BingoManager {
	
	Boolean izvrsenoIzvlacenje = false;
	ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	Vector<Integer> izvuceni = new Vector<Integer> ();
	int id = 0;
	public BingoManagerImpl() throws RemoteException {
		super();
	}
	
	@Override
	public Ticket playTicket(Vector<Integer> numbers) throws RemoteException {
		//if(!izvrsenoIzvlacenje)
		//	return null;
		
		
		Ticket result = new Ticket(String.valueOf(id++),numbers);
		tickets.add(result);
		
		System.out.print(" > Ticket :" + result.id + " :: ");
		for(Integer x : numbers) {
			System.out.print(x + " ");
		}
		System.out.println();
		
		return result;
	}

	@Override
	public void drawNumber() throws RemoteException {
		// TODO Auto-generated method stub
		izvrsenoIzvlacenje = true;
		//izvuceni.add((int) Math.random() * 100 % 90);
		System.out.println("Drawing a number");
		izvuceni.add(1);
		izvuceni.add(5);
		izvuceni.add(11);
		izvuceni.add(36);
		izvuceni.add(5);
		izvuceni.add(48);
		for(Integer x : izvuceni) {
			System.out.print(x + " ");
		}
		for(Ticket t : tickets) {
			if(t.numbers.size() == izvuceni.size()) 
			{
				int num = 0;
				for(Integer broj : t.numbers) {
					num = izvuceni.contains(broj) ? num + 1 : num;
					System.out.println(izvuceni.contains(broj));
				}
				
				if(num == izvuceni.size()) {
					System.out.println("Calling isWinnter");
					t.isWinner();
				}
			}
		}
		
	}
	
	
}
