import java.util.ArrayList;
import java.util.Scanner;

import javax.jms.*;
import javax.naming.*;

public class Klijent {
	Topic tNovi;
	Queue qOdgovor,qPomoc,qPomozi;
	TopicConnection tconn;
	QueueConnection qconn;
	TopicSession tsession;
	QueueSession qsession;
	
	int id;
	
	public Klijent(int id) throws Exception {
		this.id = id;
		Context icfx = new InitialContext();
		
		tNovi = (Topic)icfx.lookup("tNovi");
		qOdgovor = (Queue)icfx.lookup("qOdgovor");
		qPomoc = (Queue)icfx.lookup("qPomoc");
		qPomozi = (Queue)icfx.lookup("qPomozi");
		
		TopicConnectionFactory tcf = (TopicConnectionFactory)icfx.lookup("tcf");
		QueueConnectionFactory qcf = (QueueConnectionFactory)icfx.lookup("qcf");
		icfx.close();
		System.out.println("Pribavljeno sve..");
		
		tconn = tcf.createTopicConnection();
		qconn = qcf.createQueueConnection();
		
		tsession = tconn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		qsession = qconn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		System.out.println("Napravljene sesije i konekcije..");
	}
	
	
	public void Start(ArrayList<String> poslovi) throws Exception {

		System.out.println("Start connection..");
		
		TopicSubscriber ts = tsession.createSubscriber(tNovi);
		tconn.start();
		
		//Obavestimo sve koji slusaju
		TopicPublisher tp = tsession.createPublisher(tNovi);
		Message msg = tsession.createMessage();
		msg.setIntProperty("noviID",this.id);
		tp.send(msg);
		tsession.commit();
		System.out.println("Obavesteni svi..");
		//Pocnemo i mi da slusamo

		ts.setMessageListener(new NoviListener());
		
		
		//Primamo da postoje ostali klijenti
		QueueReceiver qr = qsession.createReceiver(qOdgovor);
		qr.setMessageListener(new OdgovorListener());
		System.out.println("Slusamo odgovore na novog..");
		
		//Slusamo za poso dal treba nekom pomoc
		for(String poso : poslovi) {
			QueueReceiver posoReceiver = qsession.createReceiver(qPomoc,"poso = '"+ poso +"'");
			posoReceiver.setMessageListener(new PomocListener());
		}
		System.out.println("Postavljeno da slusamo za pomoc za poslove..");
		qconn.start();
	}
	
	void potraziPomoc(String poso) throws Exception {
		QueueSender qs = qsession.createSender(qPomoc);
		Message msg = qsession.createMessage();
		msg.setStringProperty("poso",poso);
		msg.setIntProperty("id",this.id);
		qs.send(msg);
		qsession.commit();
		
		
		QueueReceiver qr = qsession.createReceiver(qPomozi,"za = '"+this.id+"'");
		qr.setMessageListener(new PomocStiglaListener());
	}
	

	void obradiNovuPoruku(Message msg) throws Exception {
		System.out.println("Novi klijent sa ID: " + msg.getIntProperty("noviID"));
		QueueSender qs = qsession.createSender(qOdgovor);
		Message response = qsession.createMessage();
		response.setIntProperty("responseID",this.id);
		qs.send(response);
		qsession.commit();
	}
	
	void obradiOdgovor(Message msg) throws Exception { 
		System.out.println("Postojeci klijent sa ID-jem: " + msg.getIntProperty("mojID") );
	}
	
	void obradiPomoc(Message msg) throws Exception {
		System.out.println("Klijent sa id-jem " + msg.getIntProperty("id") + " trazi pomoc za poso: " + msg.getStringProperty("poso"));
		QueueSender qs = qsession.createSender(qPomozi);
		
		Message response = qsession.createMessage();
		response.setIntProperty("id",msg.getIntProperty("id"));
		response.setIntProperty("pomocOd",this.id);
		response.setStringProperty("poso",msg.getStringProperty("poso"));
		
		qs.send(response);
		qsession.commit();
	}
	
	void StiglaPomoc(Message msg) throws Exception {
		System.out.println("Pomoc koju ste trazili za "+msg.getStringProperty("poso")+" poso stize od klijenta sa id-jem :" + msg.getIntProperty("pomocOd"));
	}
	
	class NoviListener implements MessageListener {
		public void onMessage(Message msg) {
			try {
				obradiNovuPoruku(msg);
			}
			catch(Exception e) {
				
			}
		}
	}
	
	class OdgovorListener implements MessageListener {
		public void onMessage(Message msg) {
			try {
				obradiOdgovor(msg);
			}
			catch(Exception e) {
				
			}
		}
	}
	
	class PomocListener implements MessageListener {
		public void onMessage(Message msg) {
			try {
				obradiPomoc(msg);
			}
			catch(Exception e) {
				
			}
		}
	}
	
	class PomocStiglaListener implements MessageListener {
		public void onMessage(Message msg) {
			try {
				StiglaPomoc(msg);
			}
			catch(Exception e) {
				
			}
		}
	}

	
	public static void main(String[] args) {
		try {
			Scanner in = new Scanner(System.in);
			System.out.println("Unesite svoj id:");
			int id = Integer.parseInt(in.nextLine());
			ArrayList<String> poslovi = new ArrayList<String>();
			
			System.out.println("Unesite svoje poslove:");
			poslovi.add(in.nextLine());
			poslovi.add(in.nextLine());
			poslovi.add(in.nextLine());
			
			Klijent k = new Klijent(id);
			k.Start(poslovi);
			
			if(id < 10 ) {
				k.potraziPomoc(poslovi.get(0));
			}
			
			System.out.println(" > Cekam na izlaz <");
			in.nextLine();
			k.qconn.close();
			k.tconn.close();
			in.close();
		}
		catch(Exception e) {
			
		}
		
	}

}
