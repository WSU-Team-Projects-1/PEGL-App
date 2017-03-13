package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SampleController implements Initializable {

	private MainModel model = MainModel.getInstance();

	@FXML
	public void newLog() {
		String annotation = annotationTextField.getText();
		if (annotation == null) {
			annotation = "";
		}
		GpsLog log = new GpsLog(model.getLocation(), annotation);
		model.getLogs().add(log);
	}

	@FXML
	public void saveLogsToFile() {
		// TODO Configure filename
		String fileName = "logs.csv";

		try (PrintWriter logFile = new PrintWriter(new File(fileName))) {

			for (GpsLog log : model.getLogs()) {
				logFile.println(log.toString());
				System.out.println(log.toString());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Non generated initialization code
	 */
	private void initializeStage2(){
		
		model.locationProperty().addListener(new ChangeListener<GpsLocation>() {

			@Override
			public void changed(ObservableValue<? extends GpsLocation> observable, GpsLocation oldValue,
					GpsLocation newValue) {

				if (newValue != null) {

					// Update position on gui
					Platform.runLater(() -> {
						gpsLocationLabel.setText(newValue.toString());
					});

					// Check bounds
					Boundary bounds = model.getBounds();
					if (bounds != null) {
						if (model.shouldIssueBoundaryWarning() && !bounds.inBounds(newValue)) {

							// TODO issue boundary warning

						} else if (!model.shouldIssueBoundaryWarning() && bounds.inBounds(newValue)) {
							// Enable warnings after in bounds again
							model.setShouldIssueBoundaryWarning(true);
						}

					}

				}
			}

		});
		
	}
	
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		//generated
		assert defaultPane != null : "fx:id=\"defaultPane\" was not injected: check your FXML file 'Sample.fxml'.";
		assert toolbarPane != null : "fx:id=\"toolbarPane\" was not injected: check your FXML file 'Sample.fxml'.";
		assert locationVBox != null : "fx:id=\"locationVBox\" was not injected: check your FXML file 'Sample.fxml'.";
		assert currentLocationLabel != null : "fx:id=\"currentLocationLabel\" was not injected: check your FXML file 'Sample.fxml'.";
		assert gpsLocationLabel != null : "fx:id=\"gpsLocationLabel\" was not injected: check your FXML file 'Sample.fxml'.";
		assert connectAndSearchStackPane != null : "fx:id=\"connectAndSearchStackPane\" was not injected: check your FXML file 'Sample.fxml'.";
		assert loadSearchAreaButton != null : "fx:id=\"loadSearchAreaButton\" was not injected: check your FXML file 'Sample.fxml'.";
		assert setSearchBoundariesButton != null : "fx:id=\"setSearchBoundariesButton\" was not injected: check your FXML file 'Sample.fxml'.";
		assert connectToDJIButton != null : "fx:id=\"connectToDJIButton\" was not injected: check your FXML file 'Sample.fxml'.";
		assert gpsLoggingStackPane != null : "fx:id=\"gpsLoggingStackPane\" was not injected: check your FXML file 'Sample.fxml'.";
		assert createGPSLogButton != null : "fx:id=\"createGPSLogButton\" was not injected: check your FXML file 'Sample.fxml'.";
		assert saveGPSLogButton != null : "fx:id=\"saveGPSLogButton\" was not injected: check your FXML file 'Sample.fxml'.";
		assert annotationTextField != null : "fx:id=\"annotationTextField\" was not injected: check your FXML file 'Sample.fxml'.";
		assert saveLogsToFileButton != null : "fx:id=\"saveLogsToFileButton\" was not injected: check your FXML file 'Sample.fxml'.";
		assert mapPane != null : "fx:id=\"mapPane\" was not injected: check your FXML file 'Sample.fxml'.";
		//end generated
		
		initializeStage2();

	}

	//generated
	
	@FXML
	private SplitPane defaultPane;

	@FXML
	private Pane toolbarPane;

	@FXML
	private VBox locationVBox;

	@FXML
	private Label currentLocationLabel;

	@FXML
	private Label gpsLocationLabel;

	@FXML
	private StackPane connectAndSearchStackPane;

	@FXML
	private Button loadSearchAreaButton;

	@FXML
	private Button setSearchBoundariesButton;

	@FXML
	private Button connectToDJIButton;

	@FXML
	private StackPane gpsLoggingStackPane;

	@FXML
	private Button createGPSLogButton;

	@FXML
	private Button saveGPSLogButton;

	@FXML
	private TextArea annotationTextField;

	@FXML
	private Button saveLogsToFileButton;

	@FXML
	private Pane mapPane;

}
