import java.util.Scanner;

import javax.jms.*;
import javax.naming.*;

public class Sestra {
	QueueSender obavesti;
	QueueReceiver primi;
	QueueSession qs;
	QueueConnection qc;
	public Sestra() throws Exception {
		
			Context icfx = new InitialContext();
			QueueConnectionFactory qcf = (QueueConnectionFactory)icfx.lookup("qcf");
			Queue obavestiDoktora = (Queue)icfx.lookup("obavestiDoktora");
			Queue stampa = (Queue)icfx.lookup("stampa");
			icfx.close();
			
			qc = qcf.createQueueConnection();
			qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			obavesti = qs.createSender(obavestiDoktora);
			primi = qs.createReceiver(stampa);
			primi.setMessageListener(new StampaListener());
			qc.start();
	}
	
	
	public void obavestiDoktora(String pacijent,String doktor) {
		try {
			TextMessage msg = qs.createTextMessage(pacijent);
			msg.setStringProperty("Doktor", doktor);
			obavesti.send(msg);
			System.out.println("Doktor obavesten!");
			qs.commit();
		}
		catch(Exception e) {
			
		}
	}
	
	class StampaListener implements MessageListener {
		public void onMessage(Message msg) {
			try {
				TextMessage m = (TextMessage)msg;
				System.out.println("> Pacijent " + m.getText() + " je zavrsio!");
			}
			catch(Exception e) {
			
			}
		}
	}

	public static void main(String[] args ) throws Exception { 
		Scanner in = new Scanner(System.in);
		Sestra s = new Sestra();
		String pacijent,doktor;
		
		pacijent = in.nextLine();
		doktor = in.nextLine();
		
		s.obavestiDoktora(pacijent,doktor);
		
		System.in.read();
		s.qc.close();
	}
		
}
