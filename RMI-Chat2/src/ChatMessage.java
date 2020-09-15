import java.io.Serializable;

public class ChatMessage implements Serializable {
	public User fromUser;
	public User toUser;
	public String message;
	public int hour;
	public int minute;
	
	public ChatMessage(User from,User to,String ms, int h, int m) {
		fromUser = from;
		toUser = to;
		message = ms;
		hour = h;
		minute = m;
	}
	
	
	
}
