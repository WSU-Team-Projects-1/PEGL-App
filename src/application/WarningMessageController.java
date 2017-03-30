package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WarningMessageController {

	private void initializeStage2() {
		okButton.setOnAction((event) -> {
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
		});
	}

	public void configure(String message) {
		// could be done without runlater because this is before the ui is drawn
		// but its there for safety
		Platform.runLater(() -> {
			warningContentsLabel.setText(message);
		});
	}

	@FXML
	void initialize() {
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'WarningMessage.fxml'.";
		assert warningContentsLabel != null : "fx:id=\"warningContentsLabel\" was not injected: check your FXML file 'WarningMessage.fxml'.";
		assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'WarningMessage.fxml'.";
		assert warningTitleLabel != null : "fx:id=\"warningTitleLabel\" was not injected: check your FXML file 'WarningMessage.fxml'.";

		initializeStage2();
	}

	@FXML
	private Pane mainPane;

	@FXML
	private Label warningContentsLabel;

	@FXML
	private Button okButton;

	@FXML
	private Label warningTitleLabel;

}
