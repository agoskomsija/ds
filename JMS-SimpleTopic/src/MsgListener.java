import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MsgListener implements MessageListener {
	String id;
	public MsgListener() {
		
	}
	public MsgListener(String id) {
		this.id = id;
	}
	
	public void onMessage(Message msg) {
		try {
		TextMessage m = (TextMessage)msg;
			System.out.println(m.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
