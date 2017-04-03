package application;

import java.util.ArrayList;
import java.util.List;

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
		logs = new ArrayList<>(50);
		portNum = 1605;
	}

	//

	/**
	 * Written to from server thread and read from other threads. TODO: Look
	 * into if this needs thread safety measures.
	 */
	private final GpsLocationProperty location;

	private Boundary bounds;

	private boolean shouldIssueBoundaryWarning = true;
	private boolean shouldIssueComunicationWarning = true;

	private final List<GpsLog> logs;
	
	private int portNum;
	
	public List<GpsLog> getLogs() {
		return logs;
	}

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
	
	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}
	
	public int getPortNum() {
		return portNum;
	}

}
