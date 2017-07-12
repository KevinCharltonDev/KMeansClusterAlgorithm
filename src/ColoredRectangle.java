import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;


public class ColoredRectangle {
	public Rectangle rect;
	public Color color;
	
	public ColoredRectangle(Point point, int c) {
		rect = new Rectangle(point);
		rect.setSize(3,3);
		color = new Color((33*c)%255, (53*c)%255,(73*c)%255);
	}
}
