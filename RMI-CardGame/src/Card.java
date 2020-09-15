import java.io.Serializable;

public class Card implements Serializable {
	private String color;
	private int value;
	public Card(String color,int value) {
		if(color.compareTo("RED") == 0 && color.compareTo("BLACK") == 0 && value > 0 && value < 15) {
			this.color = color;
			this.value = value;
		}
		else
		{
			this.color = "RED";
			this.value = 1;
		}
	}
	
	public String getColor() {return color;} 
	public int getValue() {return value;}
}
