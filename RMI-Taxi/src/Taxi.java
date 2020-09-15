import java.io.Serializable;

public class Taxi implements Serializable
{
	public int id;
	public String address;
	public boolean isFree;
	public TaxiCallback cb;
	public Taxi(int id,String address,boolean isFree) {
		this.id = id;
		this.address = address;
		this.isFree = isFree;
	}
}
