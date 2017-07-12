import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

class DisplayHandler extends JPanel {

		private static final long serialVersionUID = 2896160711316110276L;
		private static final int PREF_W = 1280;
		private static final int PREF_H = 720;
		private List<ColoredRectangle> pointRects = new ArrayList<ColoredRectangle>();
		private List<Rectangle> clusterRects = new ArrayList<Rectangle>();
		private String infoString = "";
		private int iter = 0;

		
		public void addClusterPoints(List<Cluster> clusters) {
			iter += 1;
			pointRects.clear();
			for(int i = 0; i < clusters.size(); i++){
				Cluster c = clusters.get(i);
				for(int j = 0; j < c.data.size(); j++){
					ColoredRectangle cr = new ColoredRectangle(c.data.get(j), i);
					pointRects.add(cr);
				}
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(PREF_W, PREF_H);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			for (int i = 0; i < pointRects.size(); i++){
				g2.setColor(pointRects.get(i).color);
				g2.draw(pointRects.get(i).rect);
			}
			
			for (int i = 0; i < clusterRects.size(); i++){
				g2.setColor(Color.red);
				g2.draw(clusterRects.get(i));
				g2.fill(clusterRects.get(i));
			}
			g2.setColor(Color.black);
			String s = "Iterations: " + iter;
			g2.drawString(s, 24, 24);
			g2.drawString(infoString, 24, 48);
		}

		public void addMeans(List<Cluster> clusters) {
			clusterRects.clear();
			for(int i = 0; i < clusters.size(); i++){
				Point p = new Point(clusters.get(i).meanPoint.x, clusters.get(i).meanPoint.y);
				clusterRects.add(new Rectangle(p));
				clusterRects.get(i).setSize(5, 5);
			}
		}

		public void changeInfo(String string) {
			infoString = string;
		}
	}