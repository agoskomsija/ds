import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class IspitImpl extends UnicastRemoteObject implements Ispit {

	String sifra,ime;
	int broj;
	HashMap<Integer, Student> studenti;
	
	public IspitImpl(String sifra,String ime) throws RemoteException {
		super();
		
		this.sifra = sifra;
		this.ime = ime;
		this.broj = 0;
		
		studenti = new HashMap<Integer,Student>();
		
	}

	@Override
	public void PrijaviIspit(Student s) throws RemoteException {
		studenti.put(s.getBrIndeksa(), s);
	}

	@Override
	public String ImeIspita() throws RemoteException {
		// TODO Auto-generated method stub
		return this.ime;
	}

	@Override
	public String SifraIspita() throws RemoteException {
		// TODO Auto-generated method stub
		return this.sifra;
	}

	@Override
	public int BrojPrijavljenih() throws RemoteException {
		// TODO Auto-generated method stub
		return this.studenti.size();
	}

}
