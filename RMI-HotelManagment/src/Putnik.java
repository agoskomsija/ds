import java.io.Serializable;

public class Putnik implements Serializable {
	String ime,prezime;
	public Putnik(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
	}
}
