import javax.naming.*;

import java.util.Scanner;

import javax.jms.*;

public class Doktor {
	QueueReceiver obavesti;
	QueueSender primi;
	QueueSession qs;
	String ime;
	QueueConnection qc;
	
	public Doktor(String ime) throws Exception {
		this.ime = ime;
		Context icfx = new InitialContext();
		QueueConnectionFactory qcf = (QueueConnectionFactory)icfx.lookup("qcf");
		Queue obavestiDoktora = (Queue)icfx.lookup("obavestiDoktora");
		Queue stampa = (Queue)icfx.lookup("stampa");
		icfx.close();
		
		qc = qcf.createQueueConnection();
		qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		obavesti = qs.createReceiver(obavestiDoktora,"Doktor = '" + ime + "'");
		primi = qs.createSender(stampa);
		qc.start();
		obavesti.setMessageListener(new ObavestenjeListener());
	}
	
	class ObavestenjeListener implements MessageListener {
		public void onMessage(Message msg) {
			try {
				TextMessage m = (TextMessage)msg;
				System.out.println("> Doktor " + ime + " primio pacijenta: " + m.getText());
				ObavestiSestru(m.getText());
			}
			catch(Exception e) {
				
			}
		}
	}
	
	void ObavestiSestru(String pacijent) throws Exception {
		TextMessage tm = qs.createTextMessage(pacijent);
		primi.send(tm);
		qs.commit();
	}	
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		String imeDoktora = in.nextLine();
		Doktor d = new Doktor(imeDoktora);
		
		System.in.read();
		d.qc.close();
	}
}