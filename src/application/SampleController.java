package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SampleController implements Initializable {

	private List<GpsLog> logs = new ArrayList<>(50);

	public void saveLogsToFile() {
		// TODO Configure filename
		String fileName = "logs.csv";

		try (PrintWriter logFile = new PrintWriter(new File(fileName))) {

			for (GpsLog log : logs) {
				logFile.println(log.toString());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("logged");
	}

	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		// Test values
		logs.add(new GpsLog(new GpsLocation(1, 1), "test"));
		logs.add(new GpsLog(new GpsLocation(2, 2), "ed"));

		//
		MainModel model = MainModel.getInstance();

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

	@FXML
	private SplitPane defaultPane;

	@FXML
	private Pane toolbarPane;

	@FXML
	private Button loadSearchAreaButton;

	@FXML
	private Button createGPSLogButton;

	@FXML
	private TextField annotationTextField;

	@FXML
	private Button setSearchBoundariesButton;

	@FXML
	private Button connectToDJIButton;

	@FXML
	private Button saveGPSLogButton;

	@FXML
	private Button saveLogsToFileButton;

	@FXML
	private Label currentLocationLabel;

	@FXML
	private Label gpsLocationLabel;

	@FXML
	private Pane mapPane;

}
