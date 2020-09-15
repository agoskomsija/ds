import java.io.Serializable;

public class ParkingTicket implements Serializable {
	public String reg;
	public int zone,hour,minute;
	
	public ParkingTicket(String reg,int zone,int hour,int minute) {
		this.reg = reg;
		this.zone = zone;
		this.hour = hour;
		this.minute = minute;
	}
}
