import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Cluster {
public Point meanPoint;
int error = 0;
public List<Point> data = new ArrayList<Point>();

public Cluster(int x, int y){
	meanPoint = new Point(x,y);
}

public Cluster(List<Point> data, List<Cluster> c) {
	int i  = (int) (Math.random() * data.size());
	meanPoint = new Point(data.get(i).x, data.get(i).y);
}

public void addDataPoint(Point p){
	data.add(p);
}

public void clearAllPoints(){
	data.clear();
}

public void calculateError() {
	error = 0;
	for(Point p: data){
		error += Math.pow(p.distance(meanPoint),2);
	}
}

}
