package application;

import java.net.URL;
  
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.maps.model.LatLng;


public class GMaps {

	public static void main(String[] args) throws Exception {
		LatLng point1 = new LatLng(39.739795, -84.181692);
		LatLng cornerPoint1BR = new LatLng(0, 0);
		LatLng cornerPoint1BL = new LatLng(0, 0);
		LatLng cornerPoint1TR = new LatLng(0, 0);
		LatLng cornerPoint1TL = new LatLng(0, 0);
		int pixelWidth = 700;
		int pixelHeight = 700;
		cornerPoint1BR = getBottomRightBoundLatLng( getXProj(point1, pixelWidth), getYProj(point1, pixelWidth, pixelHeight), pixelWidth, pixelHeight, 16); 
		cornerPoint1BL = getBottomLeftBoundLatLng( getXProj(point1, pixelWidth), getYProj(point1, pixelWidth, pixelHeight), pixelWidth, pixelHeight, 16);
		cornerPoint1TR = getTopRightBoundLatLng( getXProj(point1, pixelWidth), getYProj(point1, pixelWidth, pixelHeight), pixelWidth, pixelHeight, 16);
		cornerPoint1TL = getTopLeftBoundLatLng( getXProj(point1, pixelWidth), getYProj(point1, pixelWidth, pixelHeight), pixelWidth, pixelHeight, 16);
	    JFrame frame = new JFrame("Test Map");
	    URL imageUrl = new URL("http://maps.googleapis.com/maps/api/staticmap?zoom=16&"
	    		+ "size=" + pixelWidth + "x" + pixelHeight + "&center=" + point1.lat + "," + point1.lng + "&markers=" + cornerPoint1BR.lat + "," + cornerPoint1BR.lng 
	    		+ "|" + cornerPoint1BL.lat + "," + cornerPoint1BL.lng + 
	    		"|" + cornerPoint1TR.lat + "," + cornerPoint1TR.lng +
	    		"|" + cornerPoint1TL.lat + "," + cornerPoint1TL.lng + "&sensor=true");
	    frame.add(new JLabel(new ImageIcon(new ImageIcon(imageUrl).getImage())));
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(pixelWidth, pixelHeight);
	    frame.setVisible(true);
	}
	
	
	public static LatLng getTopRightBoundLatLng( double xProj, double yProj, int mapWidth, int mapHeight,int zoom) {
		LatLng point = new LatLng(0, 0);
		xProj = xProj + (.000016 * xProj);
		yProj = yProj - (.0000013 * yProj);
		xProj = ((xProj * Math.pow(2, zoom)) + (mapWidth / 2)) / Math.pow(2, zoom);
		yProj = ((yProj * Math.pow(2, zoom)) - (mapHeight / 2)) / Math.pow(2, zoom); 
		point = convertProjToLatLng(xProj, yProj, mapWidth, mapHeight);
		return point;	
	} 
	
	public static LatLng getTopLeftBoundLatLng( double xProj, double yProj, int mapWidth, int mapHeight,int zoom) {
		LatLng point = new LatLng(0, 0);
		xProj = xProj - (.0000155 * xProj);
		yProj = yProj - (.0000013 * yProj);
		xProj = ((xProj * Math.pow(2, zoom)) - (mapWidth / 2)) / Math.pow(2, zoom);
		yProj = ((yProj * Math.pow(2, zoom)) - (mapHeight / 2)) / Math.pow(2, zoom); 
		point = convertProjToLatLng(xProj, yProj, mapWidth, mapHeight);
		return point;	
	}
	
	public static LatLng getBottomLeftBoundLatLng( double xProj, double yProj, int mapWidth, int mapHeight,int zoom) {
		LatLng point = new LatLng(0, 0);
		xProj = xProj - (.000016 * xProj);
		yProj = yProj + (.0000016 * yProj);
		xProj = ((xProj * Math.pow(2, zoom)) - (mapWidth / 2)) / Math.pow(2, zoom);
		yProj = ((yProj * Math.pow(2, zoom)) + (mapHeight / 2)) / Math.pow(2, zoom); 
		point = convertProjToLatLng(xProj, yProj, mapWidth, mapHeight);
		return point;	
	} 
	
	public static LatLng getBottomRightBoundLatLng( double xProj, double yProj, int mapWidth, int mapHeight,int zoom) {
		LatLng point = new LatLng(0, 0);
		xProj = xProj + (.000016 * xProj);
		yProj = yProj + (.0000016 * yProj);
		xProj = ((xProj * Math.pow(2, zoom)) + (mapWidth / 2)) / Math.pow(2, zoom);
		yProj = ((yProj * Math.pow(2, zoom)) + (mapHeight / 2)) / Math.pow(2, zoom); 
		point = convertProjToLatLng(xProj, yProj, mapWidth, mapHeight);
		return point;	
	} 
	
	
	public static double getXProj(LatLng point, int mapWidth){
		double x = 0.0; 
		x = (point.lng + 180) * (mapWidth/ 360);
		return x;
	}
	
	public static double getYProj(LatLng point, int mapWidth, int mapHeight){
		double y = 0.0;
		double mercN = 0.0;
		y = point.lat * Math.PI / 180;
		mercN = Math.log10(Math.tan((Math.PI/4) + (y/2)));
		y = (mapHeight/2) - (mapWidth * mercN / (2 * Math.PI));
		return y;
	}
	
	public static LatLng convertProjToLatLng(double xProj, double yProj, int mapWidth, int mapHeight){
		double latCoordinate = 0.0;
		double longCoordinate = 0.0;
		longCoordinate = (xProj / (mapWidth/360)) - 180;
		latCoordinate = (((Math.atan((Math.pow(10, (Math.PI * ((((-2.0 * yProj) + mapHeight)) / mapWidth))))) - ( Math.PI / 4)) * 360) / Math.PI);	
		LatLng point = new LatLng(latCoordinate, longCoordinate);	
		return point;
	}
	
}
