package application;

public class GpsLog {

	private final GpsLocation loc;

	private String annotation;

	/**
	 * Creates a new log. Throws RuntimeException if location is null.
	 * 
	 * @param loc
	 *            Cannot be null.
	 * @param annotation
	 */
	public GpsLog(GpsLocation loc, String annotation) {
		super();
		if (loc == null) {
			throw new RuntimeException("location cannot be null");
		}
		if (annotation == null) {
			annotation = "";
		}

		this.loc = loc;
		this.annotation = annotation;
	}

	public GpsLog(double lon, double lat, String ann) {
		loc = new GpsLocation(lon, lat);
		annotation = ann;
	}

	public String getAnotation() {
		return annotation;
	}

	public void setAnotation(String anotation) {
		this.annotation = anotation;
	}

	public GpsLocation getLoc() {
		return loc;
	}

	/**
	 * Representation of a log suitable for a csv file. <longitude, latitude,
	 * annotation>
	 */
	@Override
	public String toString() {
		return loc.toString() + ", \"" + annotation.replaceAll("\"", "") + "\"";
	}
}
