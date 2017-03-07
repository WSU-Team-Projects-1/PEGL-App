package application;

import java.net.URL;
import java.util.TimeZone;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.maps.DirectionsApi;
import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;


public class GMaps {

	public static void main(String[] args) throws Exception {

		GeoApiContext context = 
				new GeoApiContext().setApiKey("AIzaSyB_7h93_Ok--LQjqYNS_h2euMxwarpCJaU");

		String address1 = "1600 Amphitheatre Parkway Mountain View, CA 94043";
		System.out.println(address1);

		GeocodingResult[] results =  GeocodingApi.geocode(context, address1).await();
		GeocodingResult result1 = results[0];
		
		System.out.println(result1.geometry.location.lat);
		System.out.println(result1.geometry.location.lng);

		LatLng point1 = new LatLng(result1.geometry.location.lat, 
				result1.geometry.location.lng);
		
		ElevationResult[] elevations = ElevationApi.getByPoints(context, point1).await();
		System.out.println(elevations[0].elevation);
		
		TimeZone timeZone = TimeZoneApi.getTimeZone(context, point1).await();
		System.out.println(timeZone.getDisplayName());
		
		String address2 = "701 1st Ave, Sunnyvale, CA 94089";
		results =  GeocodingApi.geocode(context, address2).await();
		GeocodingResult result2 = results[0];
		LatLng point2 = new LatLng(result2.geometry.location.lat, 
				result2.geometry.location.lng);
		
	    DirectionsResult directions = DirectionsApi.newRequest(context)
	            .mode(TravelMode.DRIVING)
	            .origin(point2)
	            .destination(point1).await();
	    
	    DirectionsRoute route = directions.routes[0];
	    DirectionsLeg[] legs = route.legs;
	    for (DirectionsLeg leg: legs) {
	    	DirectionsStep[] steps = leg.steps;
	    	for (DirectionsStep step: steps) {
	    		System.out.println(cleanHTML(step.htmlInstructions));
	    	}
	    }
	    
	    JFrame frame = new JFrame("Test Map");
	    URL imageUrl = new URL("http://maps.googleapis.com/maps/api/staticmap?zoom=8&"
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
