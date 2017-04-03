package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WarningIssuer {

	public WarningIssuer(String msg) {
		Platform.runLater(() -> {
			
			try {
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("WarningMessage.fxml"));
				Parent root = loader.load();
				WarningMessageController controller = (WarningMessageController) loader.getController();

				controller.configure(msg);

				Scene scene = new Scene(root);

				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();

			} catch (IOException e2) {
				e2.printStackTrace();
				System.err.println("Failed to make warning window");
			}
		});
	}
}
