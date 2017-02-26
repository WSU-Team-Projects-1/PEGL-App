package application;

public class GpsLog {

	private final GpsLocation loc;

	private String annotation;

	public GpsLog(GpsLocation loc, String anotation) {
		super();
		this.loc = loc;
		this.annotation = anotation;
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
	 * Representation of a log suitable for a csv file.
	 * <longitude, latitude, annotation>
	 */
	@Override
	public String toString() {
		return loc.toString() + ", \"" + annotation.replaceAll("\"", "") + "\"";
	}
}
