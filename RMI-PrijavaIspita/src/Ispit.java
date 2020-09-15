import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ispit extends Remote{
	public void PrijaviIspit(Student s) throws RemoteException;
	public String ImeIspita() throws RemoteException;
	public String SifraIspita() throws RemoteException;
	public int BrojPrijavljenih() throws RemoteException;
}
