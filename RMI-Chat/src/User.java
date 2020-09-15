import java.io.Serializable;

public class User implements Serializable {
	public int id;
	public String name;
	private ChatAppCallback call;
	
	public User(int id,String name,ChatAppCallback c) {
		this.id = id;
		this.name = name;
		this.call = c;
	}
	
	public void callback() {
		try {
			this.call.onChatMessage();
		}
		catch(Exception e) {
			
		}
	}
}
