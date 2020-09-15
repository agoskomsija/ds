import javax.naming.*;
import javax.jms.*;

public class Producer {

	static Context icpx = null;
	
	public static void main(String[] args) {
		
		try {
			icpx = new InitialContext();
			QueueConnectionFactory qcf = (QueueConnectionFactory)icpx.lookup("qcf");
			Queue queue = (Queue)icpx.lookup("queue");
			icpx.close();
			QueueConnection qc = qcf.createQueueConnection();
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueSender qsnd = qs.createSender(queue);
			TextMessage msg = qs.createTextMessage();
			
			for(int i =0 ; i < 10 ; i++) {
				msg.setText("Queue > " + i);
				qsnd.send(msg);
			}
			
			qc.close();
			
		}
		catch(Exception e) {
			
		}
		
	}

}
