package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class WarningController {

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
		assert warningTitleLabel != null : "fx:id=\"warningTitleLabel\" was not injected: check your FXML file 'WarningMessage.fxml'.";

	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Pane mainPane;

	@FXML
	private Label warningContentsLabel;

	@FXML
	private Label warningTitleLabel;
}