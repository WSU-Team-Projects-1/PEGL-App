package application;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

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
		//logs = new ArrayList<>(50);
		logs = FXCollections.observableArrayList(new ArrayList<>(50));
		portNum = 1605;
	}

	//

	

	/**
	 * Written to from server thread and read from other threads. TODO: Look
	 * into if this needs thread safety measures.
	 */
	private final GpsLocationProperty location;
	private GMaps map;
	private Boundary bounds;

	private boolean shouldIssueBoundaryWarning = true;
	private boolean shouldIssueComunicationWarning = true;

	private GpsLocation searchLocation;
	
	private final ObservableList<GpsLog> logs;
	
	private int portNum;
	
	public ObservableList<GpsLog> getLogs() {
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

	public void setSearchLocation(GpsLocation searchLocation) {
		this.searchLocation = searchLocation;
	}

	public GpsLocation getSearchLocation() {
		return searchLocation;
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

	public GMaps getMap() {
		return map;
	}

	public void setMap(GMaps map) {
		this.map = map;
	}
}
