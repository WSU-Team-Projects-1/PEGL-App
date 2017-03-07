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
	public GpsLocation(String s) {
		super();
		String[] split = s.split(",");
		this.latitude = Double.parseDouble(split[0].trim());
		this.longitude = Double.parseDouble(split[1].trim());
	}

	/**
	 * latitude + ", " + longitude
	 */
	@Override
	public String toString() {
		return latitude + ", " + longitude;
	}

}
