import java.io.Serializable;

public class ParkingTicket implements Serializable {
	String reg;
	int zone;
	int hour,minute;
	
	public ParkingTicket(String reg,int zone,int hour,int minute) {
		this.reg = reg;
		this.zone = zone;
		this.hour = hour;
		this.minute = minute;
	}
	
	
}
