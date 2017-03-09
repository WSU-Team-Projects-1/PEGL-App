package application;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.maps.model.LatLng;


public class GMaps {

	public static void main(String[] args) throws Exception {
		
		LatLng point1 = new LatLng(39.781225, -84.064250);
	    
	    JFrame frame = new JFrame("Test Map");
	    URL imageUrl = new URL("http://maps.googleapis.com/maps/api/staticmap?zoom=18&"
	    		+ "size=600x600&markers=" + point1.lat + "," + point1.lng + "&sensor=true");
	    frame.add(new JLabel(new ImageIcon(new ImageIcon(imageUrl).getImage())));
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(600, 600);
	    frame.setVisible(true);
	}
	
	public static String cleanHTML(String s) {
		while (s.contains("<")) {
			s = s.substring(0, s.indexOf("<")) + s.substring(s.indexOf(">")+1, s.length());
		}
		return s;
	}
}
