import java.util.ArrayList;

import javax.jms.*;
import javax.naming.*;

public class Korisnik {
	static int id = 0;
	int myId;
	Queue nizZaSortiranje,nizSortiran;
	QueueConnection qc;
	QueueSession qs;
	ArrayList<Integer> sacuvan = null;
	
	public Korisnik() throws Exception {
		myId = id++;
		InitialContext icfx = new InitialContext();
		QueueConnectionFactory qcf = (QueueConnectionFactory)icfx.lookup("qcf");
		nizZaSortiranje = (Queue) icfx.lookup("nizZaSortiranje");
		nizSortiran = (Queue) icfx.lookup("nizSortiran");
		icfx.close();
		
		qc = qcf.createQueueConnection();
		qs = qc.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
	
		QueueReceiver qr = qs.createReceiver(nizZaSortiranje);
		qr.setMessageListener(new PrimiNesortiranListener());
	}
	
	public void ZapocniSortiranje(ArrayList<Integer> niz,ArrayList<Integer> nizIds) throws Exception {

		QueueSender qsend = qs.createSender(nizZaSortiranje);
		Message msg = qs.createMessage();
		msg.setObjectProperty("polovina1", new ArrayList<Integer>(niz.subList(0, niz.size()/2)));
		msg.setObjectProperty("polovina2", new ArrayList<Integer>(niz.subList(niz.size()/2 + 1, niz.size())));
		nizIds.add(myId);
		msg.setObjectProperty("nizIds", nizIds);
		
		qsend.send(msg);
		qs.commit();
		
		QueueReceiver qrecv = qs.createReceiver(nizSortiran,"msgFor = '" + this.myId + "'");
		qrecv.setMessageListener(new PrimiSortiranNiz());
		
	}
	
	void PosaljiSortiran(ArrayList<Integer> niz, ArrayList<Integer> nizIds) throws Exception {
		QueueSender qsend = qs.createSender(nizSortiran);
		Message msg = qs.createMessage();
		msg.setIntProperty("msgFor",nizIds.get(nizIds.size() - 1));
		msg.setObjectProperty("sortiranNiz",niz);
		nizIds.remove(nizIds.size() - 1);
		msg.setObjectProperty("nizIds",nizIds);
		
		qsend.send(msg);
		qs.commit();
	}
	
	class PrimiNesortiranListener implements MessageListener {
		public void onMessage(Message msg) {
			try {
				ArrayList<Integer> primljeniNiz1 = (ArrayList<Integer>)msg.getObjectProperty("polovina1");
				ArrayList<Integer> primljeniNiz2 = (ArrayList<Integer>)msg.getObjectProperty("polovina2");
				ArrayList<Integer> nizIds = (ArrayList<Integer>)msg.getObjectProperty("nizIds");
				
				if(primljeniNiz1.size() > 1) {
					ZapocniSortiranje(primljeniNiz1, nizIds);
				}
				else {
					PosaljiSortiran(primljeniNiz1, nizIds);
				}
				
				if(primljeniNiz2.size() > 1) {
					ZapocniSortiranje(primljeniNiz2, nizIds);
				}
				else {
					PosaljiSortiran(primljeniNiz2, nizIds);					
				}
			}
			catch(Exception e) {}
		}
	}
	
	class PrimiSortiranNiz implements MessageListener {
		public void onMessage(Message msg) {
			try {
				
				ArrayList<Integer> nizIds = (ArrayList<Integer>)msg.getObjectProperty("nizIds");
				if(nizIds.size() == 0) {
					System.out.println("Sortiran niz:");
					sacuvan = (ArrayList<Integer>)msg.getObjectProperty("niz");
					for(Integer a : sacuvan) {
						System.out.println(a + " ");
					}
				
					return;
				}
				if(sacuvan == null) {
					sacuvan = (ArrayList<Integer>)msg.getObjectProperty("niz");
				}
				else {
					ArrayList<Integer> niz1 = sacuvan;
					sacuvan = null;
					ArrayList<Integer> niz2 = (ArrayList<Integer>)msg.getObjectProperty("niz");
					
					ArrayList<Integer> sortiran = new ArrayList<Integer>();
					int i=0,j=0;
					while(i < niz1.size() || j < niz2.size()) {
						int a = niz1.get(i);
						int b = niz2.get(j);
						if(a > b) {
							sortiran.add(b);
							j++;
						}
						else {
							sortiran.add(a);
							i++;
						}
					}
					
					if(i == niz1.size()) {
						while(j < niz2.size()) {
							sortiran.add(niz2.get(j));
							j++;
						}
					}
					else {
						while(i < niz1.size()) {
							sortiran.add(niz1.get(i));
							i++;
						}
					}
					
					PosaljiSortiran(sortiran,nizIds);
					
				}
			}
			catch(Exception e) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		//Mrzi me iskreno 
	}
	
}
