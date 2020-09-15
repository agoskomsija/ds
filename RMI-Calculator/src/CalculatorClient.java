import java.rmi.Naming;

public class CalculatorClient {
	public static void main(String args[]) {
		try {
			Calculator c = (Calculator)Naming.lookup("rmi://localhost:3333/Calculator");
			System.out.println("3+4=" + c.add(3,4));
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
