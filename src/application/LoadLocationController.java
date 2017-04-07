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

public class LoadLocationController {

	private void initializeStage2() {
		okButton.setOnAction((event) -> {
			// pass input
			MainModel model = MainModel.getInstance();
			if (!latTextField.getText().trim().equals("") && !longTextField.getText().trim().equals("")) {
				try {
					double latitude = Double.parseDouble(latTextField.getText().trim());
					double longitude = Double.parseDouble(longTextField.getText().trim());
					GpsLocation center = new GpsLocation(latitude, longitude);
					model.setSearchLocation(center);
					// console output for testing
					System.out.println(model.getLocation().toString());
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
	
	public void initialize() {
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'LoadLocation.fxml'.";
		assert instructionLabel != null : "fx:id=\"instructionLabel\" was not injected: check your FXML file 'LoadLocation.fxml'.";
		assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'LoadLocation.fxml'.";
		assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'LoadLocation.fxml'.";
		assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'LoadLocation.fxml'.";
		assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'LoadLocation.fxml'.";
		assert latTextField != null : "fx:id=\"latTextField\" was not injected: check your FXML file 'LoadLocation.fxml'.";
		assert longTextField != null : "fx:id=\"longTextField\" was not injected: check your FXML file 'LoadLocation.fxml'.";

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
	private Label latLabel;

	@FXML
	private Label longLabel;

	@FXML
	private TextField latTextField;

	@FXML
	private TextField longTextField;

}
