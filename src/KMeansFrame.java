import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class KMeansFrame extends JFrame {
	protected Algorithm alg = new Algorithm();
	protected List<Cluster> clusters = new ArrayList<Cluster>();
	protected DisplayHandler displayer = new DisplayHandler();
	private static final long serialVersionUID = 5431095344210049871L;

	public KMeansFrame() throws IOException, InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(displayer, BorderLayout.CENTER);
		BufferedReader data = new BufferedReader(new FileReader("data.txt"));

		String lineData = data.readLine();
		String inData = "";
		while (lineData != null) {
			inData += lineData + ",";
			lineData = data.readLine();
		}

		final DataPoints points = new DataPoints(inData);
		for (int i = 0; i < 5; i++) {
			clusters.add(new Cluster(points.data,clusters));
		}
		
		
		
		
		final Timer timer = new Timer(1000 / 1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				alg.assignDataToClusters(points, clusters);
				alg.updateMeans(clusters);
				alg.calcError(clusters);
				displayer.addClusterPoints(clusters);
				displayer.addMeans(clusters);
				String string = alg.clusterString(clusters);
				displayer.changeInfo(string);
				repaint();
			}
		});

		timer.setRepeats(true);
		timer.start();

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		data.close();
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		new KMeansFrame();
	}
}