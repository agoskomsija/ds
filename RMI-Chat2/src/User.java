import java.io.Serializable;

public class User implements Serializable {
	public int id;
	public String name;
	public ChatMessageCallback callback;
	public User(int id,String name,ChatMessageCallback cb) {
		this.id = id;
		this.name = name;
		this.callback = cb;
	}
}
