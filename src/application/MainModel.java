package application;

public class MainModel {

	private static MainModel instance;

	public static MainModel getInstance() {
		if (instance == null) {
			instance = new MainModel();
		}
		return instance;
	}

	private MainModel() {
		location = new GpsLocationProperty();
	}

	//

	/**
	 * Written to from server thread and read from other threads. TODO: Look
	 * into if this needs thread safety measures.
	 */
	private GpsLocationProperty location;

	private Boundary bounds;

	private boolean shouldIssueBoundaryWarning = true;
	private boolean shouldIssueComunicationWarning = true;

	public boolean shouldIssueBoundaryWarning() {
		return shouldIssueBoundaryWarning;
	}

	public void setShouldIssueBoundaryWarning(boolean shouldIssueBoundaryWarning) {
		this.shouldIssueBoundaryWarning = shouldIssueBoundaryWarning;
	}

	public boolean shouldIssueComunicationWarning() {
		return shouldIssueComunicationWarning;
	}

	public void setShouldIssueComunicationWarning(boolean shouldIssueComunicationWarning) {
		this.shouldIssueComunicationWarning = shouldIssueComunicationWarning;
	}

	public Boundary getBounds() {
		return bounds;
	}

	public void setBounds(Boundary bounds) {
		this.bounds = bounds;
	}

	/**
	 * Returns the most recent GpsLocation received by the server.
	 * 
	 * @return GpsLocation
	 */
	public GpsLocation getLocation() {
		return location.get();
	}

	public void setLocation(GpsLocation gpsLocation) {
		location.set(gpsLocation);
	}

	public GpsLocationProperty locationProperty() {
		return location;
	}
}