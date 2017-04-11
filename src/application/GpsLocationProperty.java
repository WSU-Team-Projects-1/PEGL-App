package application;

import javafx.beans.property.ObjectPropertyBase;

public class GpsLocationProperty extends ObjectPropertyBase<GpsLocation> {

	//methods not used, but needed to be overwritten
	
	@Override
	public Object getBean() {
		return null;
	}

	@Override
	public String getName() {
		return "Location";
	}

}
