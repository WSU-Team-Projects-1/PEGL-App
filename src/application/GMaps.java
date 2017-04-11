package application;

import com.google.maps.model.LatLng;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class GMaps {
	
	private LatLng mapCenter;
	private LatLng gpsPoint;
	private LatLng proxyTopLeftPoint;
	private LatLng proxyBottomRightPoint;
	private StackPane node;
	private Pane pane;
	private Pane logPane = new Pane();
	private boolean addToPane = false;
	
	public void setMapCenter(LatLng point){
		this.mapCenter = point;
	}
	
	public void setGPSPoint(LatLng point){
		this.gpsPoint = point;
	}
	
	public void setProxyBorder(LatLng tLPoint, LatLng bRPoint){
		this.proxyTopLeftPoint = tLPoint;
		this.proxyBottomRightPoint = bRPoint;
	}
	
	public void setAddToPane(){
		this.addToPane = true;
	}
	
	public void setNode(StackPane node){
		this.node = node;
	}
	

	public static String getGoogleMap(LatLng point, int zoom) {
		int pixelWidth = 900;
		int pixelHeight = 900;
		
		String imageUrl = new String("http://maps.googleapis.com/maps/api/staticmap?zoom=" + zoom + "&" + "size=" + pixelWidth
				+ "x" + pixelHeight + "&center=" + point.lat + "," + point.lng + "&sensor=true");

		return imageUrl;
	};
	
	/*public GMaps(StackPane node){
		int zoom = 16;
		double PIXEL_SIZE = 256 * Math.pow(2, zoom);
		
		LatLng mapCenter = new LatLng(39.781484, -84.06358);
		LatLng gpsPoint = new LatLng(39.780651, -84.064937);
		LatLng proxyTopLeftPoint = new LatLng(39.785334, -84.067876);
		LatLng proxyBottomRightPoint = new LatLng (39.779084, -84.059422); 
		
		String imageSource = getGoogleMap(mapCenter, zoom);
		Image image = new Image(imageSource);
		ImageView imageView = new ImageView(image);
		
		Pane pane = new Pane(imageView, getProxy(mapCenter, proxyTopLeftPoint, proxyBottomRightPoint, (int) PIXEL_SIZE, image), getGPSLoc(mapCenter, gpsPoint, (int) PIXEL_SIZE, image));
	
		node.getChildren().addAll(pane);
	}*/

	public GMaps(StackPane node){//, LatLng mapCenter, LatLng gpsPoint, LatLng proxyTopLeftPoint, LatLng proxyBottomRightPoint) {		
		int zoom = 14;
		double PIXEL_SIZE = 256 * Math.pow(2, zoom);
		
		/*LatLng mapCenter = new LatLng(39.781484, -84.06358);
		LatLng gpsPoint = new LatLng(39.780651, -84.064937);
		LatLng proxyTopLeftPoint = new LatLng(39.785334, -84.067876);
		LatLng proxyBottomRightPoint = new LatLng (39.779084, -84.059422); 
		*/
		String imageSource = getGoogleMap(mapCenter, zoom);
		Image image = new Image(imageSource);
		ImageView imageView = new ImageView(image);
		
		this.pane = new Pane(imageView, getProxy(mapCenter, proxyTopLeftPoint, proxyBottomRightPoint, (int) PIXEL_SIZE, image), getGPSLoc(mapCenter, gpsPoint, (int) PIXEL_SIZE, image));
	
		node.getChildren().addAll(pane);
		
	}
	
	public  void drawMap(){
		int zoom = 16;
		double PIXEL_SIZE = 256 * Math.pow(2, zoom);
		
		/*LatLng mapCenter = new LatLng(39.781484, -84.06358);
		LatLng gpsPoint = new LatLng(39.780651, -84.064937);
		LatLng proxyTopLeftPoint = new LatLng(39.785334, -84.067876);
		LatLng proxyBottomRightPoint = new LatLng (39.779084, -84.059422); 
		*/
		if (mapCenter == null)
			return;
		String imageSource = getGoogleMap(mapCenter, zoom);
		Image image = new Image(imageSource);
		ImageView imageView = new ImageView(image);
		
		this.pane = new Pane(imageView
				, getProxy(mapCenter, proxyTopLeftPoint, proxyBottomRightPoint, (int) PIXEL_SIZE, image)
				, getGPSLoc(mapCenter, gpsPoint, (int) PIXEL_SIZE, image));
		createNewLogLocation(mapCenter, gpsPoint, (int) PIXEL_SIZE, image, addToPane);
		if(logPane != null){
			pane.getChildren().add(logPane);
		}
		node.getChildren().addAll(pane);
		
	}

	public GMaps() {
	}
	
	
	public static Circle getGPSLoc(LatLng mapCenter, LatLng gpsPoint, int pixelSize, Image image){
		double xCoordinate = (image.getWidth()/2) - (getXProj(mapCenter, (int) pixelSize) - getXProj(gpsPoint, (int) pixelSize));
		double yCoordinate = (image.getWidth()/2) - (getYProj(mapCenter, (int) pixelSize) - getYProj(gpsPoint, (int) pixelSize));
		
		Circle gpsLoc = new Circle();
		gpsLoc.setCenterX(xCoordinate);
		gpsLoc.setCenterY(yCoordinate);
		gpsLoc.setRadius(5);
		gpsLoc.setFill(Color.RED);
		
		return gpsLoc;
	}
	
	public Rectangle createNewLogLocation(LatLng mapCenter, LatLng point, int pixelSize, Image image, boolean addToPane){
		double xCoordinate = (image.getWidth()/2) - (getXProj(mapCenter, (int) pixelSize) - getXProj(point, (int) pixelSize)) - 3.5;
		double yCoordinate = (image.getWidth()/2) - (getYProj(mapCenter, (int) pixelSize) - getYProj(point, (int) pixelSize)) - 3.5;
		double width = 7;
		double height = 7;
		
		Rectangle newLogLocation = new Rectangle(xCoordinate, yCoordinate, width, height);
		newLogLocation.setFill(Color.GREEN);
		
		if(addToPane == true){
			logPane.getChildren().add(newLogLocation);
		this.addToPane = false;
		}
		return newLogLocation;
	}

	public Rectangle getProxy(LatLng mapCenter, LatLng topLeft, LatLng bottomRight, int pixelSize, Image image){
		double xCoordinate = (image.getWidth()/2) - (getXProj(mapCenter, (int) pixelSize) - getXProj(topLeft, (int) pixelSize));
		double yCoordinate = (image.getWidth()/2) - (getYProj(mapCenter, (int) pixelSize) - getYProj(topLeft, (int) pixelSize));
		
		double width = getXProj(bottomRight, (int) pixelSize) - getXProj(topLeft, (int) pixelSize);
		double height = getYProj(bottomRight, (int) pixelSize) - getYProj(topLeft, (int) pixelSize); 
		
		Rectangle proxy = new Rectangle(xCoordinate, yCoordinate , width, height);
		
		proxy.setFill(Color.TRANSPARENT);
		proxy.setStroke(Color.RED);
		
		return proxy;
	}
	
	public static double getXProj(LatLng point, int pixelSize) {
		double x = Math.floor((point.lng + 180) * (pixelSize / 360));
		
		return x;
	}

	public static double getYProj(LatLng point, int pixelSize) {
		double y = point.lat * Math.PI / 180;
		double mercN = Math.log(Math.tan((Math.PI / 4) + (y / 2)));
		
		y = Math.floor((pixelSize / 2) - (pixelSize * mercN / (2 * Math.PI)));
		
		return y;
	}

}
