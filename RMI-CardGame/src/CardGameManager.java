import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CardGameManager extends Remote {
	public Card requestCard(Player p) throws RemoteException;
	public void pass(Player p) throws RemoteException;
	public void registerPlayer(Player p) throws RemoteException;
}
