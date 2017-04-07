package application;

import com.google.maps.model.LatLng;

import javafx.scene.shape.Rectangle;


public class Boundary {
	
	private final GpsLocation topLeft;
	private final GpsLocation bottomRight;

	private Rectangle bounds;

	public Boundary(GpsLocation topLeft, GpsLocation bottomRight) {
		super();
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;

		double width = bottomRight.longitude - topLeft.longitude;
		double height = bottomRight.latitude - topLeft.latitude;

		bounds = new Rectangle(topLeft.longitude, topLeft.latitude, width, height);

	}
	
	public LatLng getTopLeft(){
		return GpsLocation.convertGPSLocation(topLeft);
	}
	
	public LatLng getBottomRight(){
		return GpsLocation.convertGPSLocation(bottomRight);
	}

	public boolean inBounds(GpsLocation loc) {

		return bounds.contains(loc.latitude, loc.longitude);
	}

}
