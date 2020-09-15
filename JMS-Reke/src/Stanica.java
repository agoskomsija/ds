import java.util.ArrayList;

import javax.jms.*;
import javax.naming.*;

public class Stanica {

	int x,y;
	Topic tSve;
	Queue qPz;
	QueueConnection qc;
	TopicConnection tc;
	QueueSession qs;
	TopicSession ts;
	
	ArrayList<String> pristiglo;
	
	public Stanica(int x,int y) throws Exception {
		InitialContext icfx = new InitialContext();
		tSve = (Topic)icfx.lookup("sveReke");
		qPz = (Queue)icfx.lookup("poslednjeZagadjenje");
		TopicConnectionFactory tcf = (TopicConnectionFactory)icfx.lookup("tcf");
		QueueConnectionFactory qcf = (QueueConnectionFactory)icfx.lookup("qcf");
		icfx.close();
		
		qc = qcf.createQueueConnection();
		tc = tcf.createTopicConnection();
		
		qs = qc.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		ts = tc.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		this.x = x;
		this.y = y;
		pristiglo = new ArrayList<String>();
		
	}
	
	void Start() throws Exception {
		TopicSubscriber tsub = ts.createSubscriber(tSve,"x <'"+this.x+"' AND y < '" + this.y +"'",false);
		tsub.setMessageListener(new ObradiObavestiListener());
		QueueReceiver qr = qs.createReceiver(qPz,"x <'"+this.x+"' AND y < '" + this.y +"'");
		qr.setMessageListener(new svoZagadjenjeListener());
		qc.start();
		tc.start();
	}
	
	void Prosledi(int koeficijentZagadjenja) throws Exception{
		TopicPublisher tpub = ts.createPublisher(tSve);
		TextMessage msg = ts.createTextMessage(koeficijentZagadjenja + "");
		msg.setIntProperty("x",this.x);
		msg.setIntProperty("y",this.y);
		tpub.send(msg);
		ts.commit();
	}
	
	void ProslediSvoZagadjenje() throws Exception {
		QueueSender qsnd = qs.createSender(qPz);
		TextMessage msg = qs.createTextMessage();
		String payload = "";
		for(String z : pristiglo)
			payload += z + "\n";
		System.out.println(payload);
		msg.setText(payload);
		msg.setIntProperty("x",this.x);
		msg.setIntProperty("y",this.y);
		qsnd.send(msg);
		qs.commit();
	}
	
	void obradiObavesti(Message msg) throws Exception {
		TextMessage zagadjenje = (TextMessage)msg;
		String info = "(" + msg.getIntProperty("x") + "," + msg.getIntProperty("y") + ") - " + zagadjenje;
		pristiglo.add(info);
		System.out.println(info);
	}
	
	void svoZagadjenje(Message msg) throws Exception {
		TextMessage m = (TextMessage)msg;
		System.out.println(m.getText());
	}
	
	class ObradiObavestiListener implements MessageListener {
		public void onMessage(Message msg) {
			try { 
				obradiObavesti(msg);
				
			}
			catch(Exception e) {
				
			}
		}
	}
	
	class svoZagadjenjeListener implements MessageListener {
		public void onMessage(Message msg) {
			try {
			svoZagadjenje(msg);
			}
			catch(Exception e ) {
				
			}
		}
	}
}
