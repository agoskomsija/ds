import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class CardGameManagerImpl extends UnicastRemoteObject implements CardGameManager {
	
	HashMap<String,Player> players;
	HashMap<String,Integer> krugovi;
	HashMap<String,Boolean> igra;
	ArrayList<Card> cards;
	int krug,odigrano;
	
	
	public CardGameManagerImpl() throws RemoteException {
		super();
		players = new HashMap<String, Player>();

		//Inicijalizujemo sve karte random
		cards = new ArrayList<Card>();
		initCards();
		
		
		//Krug
		krug = 0;
		krugovi = new HashMap<String, Integer>();
		igra = new HashMap<String, Boolean>();
		
	}
	
	private void initCards() {
		for(int i = 0 ; i < 52; i ++) {
			cards.add(new Card(Math.random() > 0.5 ? "RED" : "BLACK",
					(int) Math.random() *20 % 14 + 1 ));
		}
	}
	
	public Card requestCard(Player p) throws RemoteException {
		
		Card result = null;
		
		if(igra.get(p.id) && krugovi.get(p.id) == krug) {
			result = getRandomCard();
			odigrano++;
		}
		
		proveriKrug();

		
		return result;
	}
	
	private Card getRandomCard() {
		int index = (int) ((Math.random() * 100) % cards.size());
		return cards.remove(index);
	}
	
	private void proveriKrug() {
		if(odigrano == players.size()) {
			if(cards.size() == 0 || cards.size() < players.size()) {
				
				for(Player player : players.values()) {
					int poeni = player.getPoints();
					player.setPoints(poeni == 21 ? 31 : poeni);
				}
				
			}
			else {
				krug++;				
			}
		}
	}
	
	public void pass(Player p) throws RemoteException {
		Boolean result = igra.get(p.id);
		if(result != null) {
			result = false;
			odigrano++;
		}
	}
	
	public void registerPlayer(Player p) throws RemoteException {
		players.put(p.id,p);
		krugovi.put(p.id,0);
		igra.put(p.id,true);
	}
	
}
