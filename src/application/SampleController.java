package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController {

	private MainModel model = MainModel.getInstance();
	private final String oobWarning = "The UAS is out of the specified boundary";

	/**
	 * ui updates based on changing gps location
	 */
	private ChangeListener<GpsLocation> gpsChangeListener = new ChangeListener<GpsLocation>() {
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

						// issue boundary warning
						try {
							Stage stage = new Stage();
							FXMLLoader loader = new FXMLLoader(getClass().getResource("WarningMessage.fxml"));
							Parent root = loader.load();
							WarningMessageController controller = (WarningMessageController) loader.getController();

							controller.configure(oobWarning);

							Scene scene = new Scene(root);

							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							stage.setScene(scene);
							stage.show();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (!model.shouldIssueBoundaryWarning() && bounds.inBounds(newValue)) {
						// Enable warnings after in bounds again
						model.setShouldIssueBoundaryWarning(true);
					}

				}

			}
		}

	};

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
	private void initializeStage2() {

		model.locationProperty().addListener(gpsChangeListener);

		connectToDJIButton.setOnAction((event) -> {
			try {
				Parent root1 = (Parent) FXMLLoader.load(getClass().getResource("ProxyPairing.fxml"));
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root1));
				stage.showAndWait();
			} catch (Exception e) {
				e.printStackTrace();
				Platform.exit();
				System.exit(0);
			}
		});

		loadSearchAreaButton.setOnAction((event) -> {
			try {
				Parent root1 = (Parent) FXMLLoader.load(getClass().getResource("LoadLocation.fxml"));
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root1));
				stage.showAndWait();
			} catch (Exception e) {
				e.printStackTrace();
				Platform.exit();
				System.exit(0);
			}
		});

		setSearchBoundariesButton.setOnAction((event) -> {
			try {
				Parent root1 = (Parent) FXMLLoader.load(getClass().getResource("LoadBoundaries.fxml"));
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root1));
				stage.showAndWait();
			} catch (Exception e) {
				e.printStackTrace();
				Platform.exit();
				System.exit(0);
			}
		});

		new GMaps(mapPane);

	}

	// This method is called by the FXMLLoader when initialization is complete
	public void initialize() {

		// generated
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
		// end generated

		initializeStage2();

	}

	// generated

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
