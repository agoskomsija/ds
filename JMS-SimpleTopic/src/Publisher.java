import javax.jms.*;
import javax.naming.*;

public class Publisher {

	static Context icfx = null;
	public static void main(String[] args) {
		try {
			icfx = new InitialContext();
			TopicConnectionFactory tcf = (TopicConnectionFactory)icfx.lookup("tcf");
			Topic topic = (Topic)icfx.lookup("topic");
			icfx.close();
			
			TopicConnection tc = tcf.createTopicConnection();
			TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			TopicPublisher tp = ts.createPublisher(topic);
			TextMessage msg;
			
			for(int i = 0 ; i < 10;i++ ) {
				msg = ts.createTextMessage("Topic > " + i);
				tp.send(msg);
			}			
			tc.close();
		}
		catch(Exception e) {
			
		}
	}
}
