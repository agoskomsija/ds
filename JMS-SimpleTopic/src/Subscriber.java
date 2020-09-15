import javax.jms.*;
import javax.naming.*;

public class Subscriber {

	static Context icfx = null;
	public static void main(String[] args) {
		try {
			icfx = new InitialContext();
			TopicConnectionFactory tcf = (TopicConnectionFactory)icfx.lookup("tcf");
			Topic topic = (Topic)icfx.lookup("topic");
			icfx.close();
			
			TopicConnection tc = tcf.createTopicConnection();
			TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			TopicSubscriber tsub = ts.createSubscriber(topic);
			tsub.setMessageListener(new MsgListener("Topic"));
				
			tc.start();
			System.in.read();			
			tc.close();
		}
		catch(Exception e) {
			
		}
	}

}
