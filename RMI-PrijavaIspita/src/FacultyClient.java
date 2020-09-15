import java.rmi.Naming;

public class FacultyClient {
	public static void main(String[] args) {
		try {
			FacultyManager fm = (FacultyManager)Naming.lookup("rmi://localhost:3030/Faculty");
			Student s = new Student(16835,"Milan","Radosavljevic");
			Ispit i = null;
			i = fm.NadjiIspit("1");
			if(i != null) {
				System.out.println("Broj prijavljenih" + i.BrojPrijavljenih());
				i.PrijaviIspit(s);
				System.out.println(i.BrojPrijavljenih());

			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
