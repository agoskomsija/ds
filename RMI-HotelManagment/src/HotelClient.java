import java.rmi.Naming;

public class HotelClient {

	public static void main(String args[]) {
		try {
			HotelManager hm = (HotelManager)Naming.lookup("rmi://localhost:3030/HotelManager");
			
			Putnik p = new Putnik("Mihajlo","Radosavljevic");
			
			Soba s = hm.nadjiSobu(400, 2);
			System.out.println(s.getInfo());
			
			if(s != null) {
				s.reserve(p);
				System.out.println(s.getInfo());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
