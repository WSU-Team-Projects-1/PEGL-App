package application;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
			MainModel model = MainModel.getInstance();
			if (portTextField.getText().trim().equals("")) {
				// default port number
				model.setPortNum(1605);
			} else {
				try {
					model.setPortNum(Integer.parseInt(portTextField.getText()));
				} catch (NumberFormatException nfe) {
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
			controller.configure("Unusable input! Please only input an integer port number.");
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
