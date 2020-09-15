import javax.naming.*;
import javax.jms.*;

public class Consumer {

	static Context icpx = null;
	
	public static void main(String[] args) {
		try {
			icpx = new InitialContext();
			QueueConnectionFactory qcf = (QueueConnectionFactory)icpx.lookup("qcf");
			Queue queue = (Queue)icpx.lookup("queue");
			icpx.close();
			QueueConnection qc = qcf.createQueueConnection();
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueReceiver qr = qs.createReceiver(queue);
			TextMessage msg;
			qc.start();
			for(int i =0 ; i < 10;i++) {
				msg = (TextMessage)qr.receive();
				System.out.println(msg.getText());
			}
			
			qc.close();
			
		}
		catch(Exception e) {
			
		}

	}

}
