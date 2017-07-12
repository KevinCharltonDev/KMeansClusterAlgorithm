import java.awt.Point;
import java.util.List;

public class Algorithm {

	public void assignDataToClusters(DataPoints points, List<Cluster> clusters) {

		for (Cluster c : clusters) {
			c.clearAllPoints();
		}

		for (Point p : points.data) {
			Cluster closest = clusters.get(0);
			for (Cluster cl : clusters) {
				if (p.distance(cl.meanPoint) < p.distance(closest.meanPoint))
					closest = cl;
			}
			closest.addDataPoint(p);
		}
	}

	public void updateMeans(List<Cluster> clusters) {
		for (Cluster c : clusters) {
			int x = 0;
			int y = 0;
			for (int i = 0; i < c.data.size(); i++){
			x += c.data.get(i).x;
			y += c.data.get(i).y;
			}
			if (c.data.size() != 0){
			x /= c.data.size();
			y /= c.data.size();
			c.meanPoint = new Point(x,y);
			}
		}
	}

	public void calcError(List<Cluster> clusters) {
		for (Cluster c: clusters){
			c.calculateError();
		}
	}

	public String clusterString(List<Cluster> clusters) {
		String s = "Error: ";
		int sum = 0;
		for (int i = 1; i <= clusters.size(); i++) {
			s += "(" + i + ": " + clusters.get(i-1).error + ") ";
			sum += clusters.get(i-1).error;
		}
		
		s +=  "(" + "Total: " + sum + ") ";
		return s;
	}

}
