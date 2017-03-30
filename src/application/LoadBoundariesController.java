package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoadBoundariesController {

	private void initializeStage2() {
		okButton.setOnAction((event) -> {
			// pass input
			System.out.println("TODO: pass boundaries input!");
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
		});
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
