import java.rmi.Naming;
import java.util.Vector;

public class BingoClient {
	public static void main(String[] args) {
		try {
			BingoManager bm = (BingoManager)Naming.lookup("rmi://localhost:1099/Bingo");
			
			Vector<Integer> numbers = new Vector<Integer>();
			numbers.add(1);
			numbers.add(5);
			numbers.add(11);
			numbers.add(36);
			numbers.add(5);
			numbers.add(48);
			
			Ticket t = bm.playTicket(numbers);
			
			System.in.read();

			
		}
		catch(Exception e) {
			
		}
	}
}
