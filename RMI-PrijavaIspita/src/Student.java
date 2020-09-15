import java.io.Serializable;

public class Student implements Serializable {
	private int brIndeksa;
	private String ime,prezime;
	
	public Student(int brIndeksa,String ime,String prezime) {
		this.brIndeksa = brIndeksa;
		this.ime = ime;
		this.prezime = prezime;
	}

	public int getBrIndeksa() {
		return brIndeksa;
	}

	
	
	
}
