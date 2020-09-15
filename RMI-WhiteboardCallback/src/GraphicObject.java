import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;

public class GraphicObject implements Serializable {
	public String type;
	public Rectangle enclosing;
	public Color line;
	public Color fill;
	public boolean isFilled;
	
	public GraphicObject() {
		
	}
	
	public GraphicObject(String type,Rectangle enclosing,Color line,Color fill,boolean isFilled) {
		this.type = type;
		this.enclosing = enclosing;
		this.line = line;
		this.fill = fill;
		this.isFilled = isFilled;
	}
	
	public void Print() {
		System.out.print("> " + type + ",");
		System.out.print(enclosing.x + " , " + enclosing.y + " , " + enclosing.width + " , " + enclosing.height);
		if (isFilled) System.out.println(", filled"); else System.out.println(", not filled");
	}
}
