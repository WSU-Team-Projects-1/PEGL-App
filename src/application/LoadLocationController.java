package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadLocationController {

	private void initializeStage2() {
		okButton.setOnAction((event) -> {
			// pass input
			System.out.println("TODO: pass starting area input!");
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
		});
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
