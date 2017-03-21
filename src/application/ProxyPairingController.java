package application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProxyPairingController {

	// Non-generated initialization code
	private void initializeStage2() {
		InetAddress IP;
		try {
			IP = InetAddress.getLocalHost();
			ipContentsLabel.setText(IP.getHostAddress());
		} catch (UnknownHostException e) {
			System.err.println("Error obtaining IP address.");
		}

		okButton.setOnAction((event) -> {
			// pass input or default value
			System.out.println("TODO: pass port input or default value!");
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
		});
	}

	public void initialize() {
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'ProxyPairing.fxml'.";
		assert instructionLabel != null : "fx:id=\"instructionLabel\" was not injected: check your FXML file 'ProxyPairing.fxml'.";
		assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'ProxyPairing.fxml'.";
		assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'ProxyPairing.fxml'.";
		assert portTextField != null : "fx:id=\"portTextField\" was not injected: check your FXML file 'ProxyPairing.fxml'.";
		assert ipLabel != null : "fx:id=\"ipLabel\" was not injected: check your FXML file 'ProxyPairing.fxml'.";
		assert ipContentsLabel != null : "fx:id=\"ipContentsLabel\" was not injected: check your FXML file 'ProxyPairing.fxml'.";

		initializeStage2();
	}

	// generated

	@FXML
	private Pane mainPane;

	@FXML
	private Label instructionLabel;

	@FXML
	private Button okButton;

	@FXML
	private Button cancelButton;

	@FXML
	private TextField portTextField;

	@FXML
	private Label ipLabel;

	@FXML
	private Label ipContentsLabel;
}
