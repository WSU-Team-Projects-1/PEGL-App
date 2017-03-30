package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadBoundariesController {

	private void initializeStage2() {
		okButton.setOnAction((event) -> {
			MainModel model = MainModel.getInstance();
			// pass input
			if (!topLeftLatTextField.getText().trim().equals("") && !topLeftLongTextField.getText().trim().equals("")
					&& !botRightLatTextField.getText().trim().equals("")
					&& !botRightLongTextField.getText().trim().equals("")) {
				try {
					double topLeftLatitude = Double.parseDouble(topLeftLatTextField.getText().trim());
					double topLeftLongitude = Double.parseDouble(topLeftLongTextField.getText().trim());
					double botRightLatitude = Double.parseDouble(botRightLatTextField.getText().trim());
					double botRightLongitude = Double.parseDouble(botRightLongTextField.getText().trim());
					GpsLocation topLeft = new GpsLocation(topLeftLatitude, topLeftLongitude);
					GpsLocation botRight = new GpsLocation(botRightLatitude, botRightLongitude);
					Boundary bounds = new Boundary(topLeft, botRight);
					model.setBounds(bounds);
					// console output for testing
					System.out.println("(" + topLeft.toString() + ")" + " (" + botRight.toString() + ")");
				} catch (NumberFormatException e) {
					openInputError();
				}
			}
			// close window
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
		});

		cancelButton.setOnAction((event) -> {
			// close window
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		});
	}
	
	//opens warning message window if the user gives bad input
	private void openInputError() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WarningMessage.fxml"));
			Parent root = loader.load();
			WarningMessageController controller = (WarningMessageController) loader.getController();
			controller.configure("Unusable input! Please only input decimal values.");
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
			System.exit(0);
		}
	}

	@FXML
	void initialize() {
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert instructionLabel != null : "fx:id=\"instructionLabel\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert topLeftLatTextField != null : "fx:id=\"topLeftLatTextField\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert botRightLatTextField != null : "fx:id=\"botRightLatTextField\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert topLeftLongTextField != null : "fx:id=\"topLeftLongTextField\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert botRightLongTextField != null : "fx:id=\"botRightLongTextField\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert topLeftLatLabel != null : "fx:id=\"topLeftLatLabel\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert topLeftLongLabel != null : "fx:id=\"topLeftLongLabel\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert botRightLatLabel != null : "fx:id=\"botRightLatLabel\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";
		assert botRightLongLabel != null : "fx:id=\"botRightLongLabel\" was not injected: check your FXML file 'LoadBoundaries.fxml'.";

		initializeStage2();
	}

	@FXML
	private Pane mainPane;

	@FXML
	private Label instructionLabel;

	@FXML
	private Button okButton;

	@FXML
	private Button cancelButton;

	@FXML
	private TextField topLeftLatTextField;

	@FXML
	private TextField botRightLatTextField;

	@FXML
	private TextField topLeftLongTextField;

	@FXML
	private TextField botRightLongTextField;

	@FXML
	private Label topLeftLatLabel;

	@FXML
	private Label topLeftLongLabel;

	@FXML
	private Label botRightLatLabel;

	@FXML
	private Label botRightLongLabel;
}
