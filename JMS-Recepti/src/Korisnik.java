import java.util.ArrayList;
import java.util.HashMap;

import javax.jms.*;
import javax.naming.*;

public class Korisnik {
	Topic tPrijavi;
	Queue qPozdravi,qTraziRecept,qOdgovorRecept;
	QueueConnection qconn;
	TopicConnection tconn;
	QueueSession qsess;
	TopicSession tsess;
	HashMap<String,Recept> mojiRecepti;
	
	int id;
	public Korisnik(int id) throws Exception {
		this.id = id;
		mojiRecepti = new HashMap<String,Recept>();
		Context icfx = new InitialContext();
		
		QueueConnectionFactory qcf = (QueueConnectionFactory)icfx.lookup("qcf");
		TopicConnectionFactory tcf = (TopicConnectionFactory)icfx.lookup("tcf");
		
		tPrijavi = (Topic)icfx.lookup("prijavi");
		qPozdravi = (Queue)icfx.lookup("pozdravi");
		qTraziRecept = (Queue)icfx.lookup("traziRecept");
		qOdgovorRecept = (Queue)icfx.lookup("odgovorRecept");
		icfx.close();
		
		qconn = qcf.createQueueConnection();
		tconn = tcf.createTopicConnection();
		
		qsess = qconn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		tsess = tconn.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		
	}
	
	void Start(ArrayList<Recept> recepti) throws Exception {
		TopicSubscriber ts = tsess.createSubscriber(tPrijavi);
		tconn.start();
		
		TopicPublisher tp = tsess.createPublisher(tPrijavi);
		Message msg = tsess.createMessage();
		msg.setIntProperty("newId",this.id);
		tp.send(msg);
		tsess.commit();
		
		ts.setMessageListener(new PrijaviListener(this));
		QueueReceiver qr = qsess.createReceiver(qPozdravi,"forId='"+this.id+"'");
		qr.setMessageListener(new PrimiPozdravListener(this));
		
		for(Recept recept : recepti) {
			mojiRecepti.put(recept.naziv,recept);
			QueueReceiver qrr = qsess.createReceiver(qTraziRecept,"recept = '"+ recept.naziv +"'");
			qrr.setMessageListener(new NekoTraziReceptListener(this));
		}
		
		qconn.start();
	}

	void TraziRecept(String naziv) throws Exception {
		QueueSender trazi = qsess.createSender(qTraziRecept);
		Message tmsg = qsess.createMessage();
		tmsg.setStringProperty("recept",naziv);
		tmsg.setIntProperty("fromId",this.id);
		trazi.send(tmsg);
		
		QueueReceiver odgovor = qsess.createReceiver(qOdgovorRecept,"recept = '"+naziv+"' AND forId = "+this.id+"");
		odgovor.setMessageListener(new OdgovorNaTrazenjeListener(this));
	}
	
	void obradiDobijenRecepta(Message msg) throws Exception {
		TextMessage odgovor = (TextMessage)msg;
		System.out.println(odgovor.getText());
	}
	
	void PozdraviNovog(Message msg) throws Exception {
		System.out.println("Korisnik sa id-jem: " + msg.getIntProperty("newId") + " se prijavio na sistem!");
		QueueSender qs = qsess.createSender(qPozdravi);
		Message ms = qsess.createMessage();
		ms.setIntProperty("forId",msg.getIntProperty("newId"));
		ms.setIntProperty("fromId",this.id);
		qs.send(ms);
		qsess.commit();
	}
	
	void primiPozdrav(Message msg) throws Exception{
		System.out.println("Potvrdjeno od strane " + msg.getIntProperty("fromId") + "");
	}
	
	void obradiTrazenjeRecepta(Message msg) throws Exception {
		QueueSender qs = qsess.createSender(qOdgovorRecept);
		TextMessage response = qsess.createTextMessage();
		Recept recept = mojiRecepti.get(msg.getStringProperty("recept"));
		
		response.setIntProperty("forId",msg.getIntProperty("fromId"));
		response.setStringProperty("recept",msg.getStringProperty("recept"));
		response.setText(recept.naziv + "\n\n" + recept.recept);
		qs.send(response);
		qsess.commit();
	}
	
	class PrijaviListener implements MessageListener {
		Korisnik k;
		public PrijaviListener(Korisnik k) {
			this.k = k;
		}
		
		public void onMessage(Message msg) {
			try {
				this.k.PozdraviNovog(msg);
			}
			catch(Exception e) {}
		}
	}
	
	class PrimiPozdravListener implements MessageListener {
		Korisnik k;
		public PrimiPozdravListener(Korisnik k) {
			this.k = k;
		}
		
		public void onMessage(Message msg) {
			try {
				this.k.primiPozdrav(msg);
			}
			catch(Exception e) {}
		}
	}

	class NekoTraziReceptListener implements MessageListener {
		Korisnik k;
		public NekoTraziReceptListener(Korisnik k) {
			this.k = k;
		}
		
		public void onMessage(Message msg) {
			try {
				this.k.obradiTrazenjeRecepta(msg);
			}
			catch(Exception e) {}
		}
	}
	
	class OdgovorNaTrazenjeListener implements MessageListener {
		Korisnik k;
		public OdgovorNaTrazenjeListener(Korisnik k) {
			this.k = k;
		}
		
		public void onMessage(Message msg) {
			try {
				this.k.obradiDobijenRecepta(msg);
			}
			catch(Exception e) {}
		}
	}
	
	class Recept {
		public String naziv;
		public String recept;
		public Recept(String naziv,String recept) 
		{
			this.naziv = naziv;
			this.recept = recept;
		}
	}

}
