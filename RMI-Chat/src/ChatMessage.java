import java.io.Serializable;

public class ChatMessage implements Serializable {
	public User fromUser;
	public User toUser;
	public String msg;
	public int hour;
	public int minute;
	
	public ChatMessage(User f,User t,String m,int h,int min) {
		fromUser = f;
		toUser = t;
		msg = m;
		hour = h;
		minute = min;
	}
	
}
