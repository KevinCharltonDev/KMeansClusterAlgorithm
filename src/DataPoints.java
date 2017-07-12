import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataPoints {
	public List<Point> data = new ArrayList<Point>();
	
	public DataPoints(String input) throws IOException {
		String tokens[] = input.split(",");
		for (int i = 0; i < tokens.length; i+=2){
			int x = Integer.parseInt(tokens[i]);
			int y = Integer.parseInt(tokens[i+1]);
		data.add(new Point(x,y));
		}
	}

}
