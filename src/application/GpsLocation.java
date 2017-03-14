package application;

/**
 * 
 * Simple struct for capturing latitude and longitude.
 *
 */
public class GpsLocation {
	public final double latitude;
	public final double longitude;

	public GpsLocation(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Parse a string given as `latitude + ", " + longitude`.
	 * 
	 * @param s
	 */
	public static GpsLocation parse(String s) {
		
		String[] split = s.split(",");
		double lat = Double.parseDouble(split[0].trim());
		double lon = Double.parseDouble(split[1].trim());
		return new GpsLocation(lat, lon);
	}

	/**
	 * latitude + ", " + longitude
	 */
	@Override
	public String toString() {
		return latitude + ", " + longitude;
	}

}
