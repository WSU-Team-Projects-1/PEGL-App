package application;

import application.server.GpsServer;
import application.server.TestClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private boolean usingTestClient = true;
	private boolean runningServer = true;

	private GpsServer server;

	@Override
	public void start(Stage primaryStage) {
		try {
			SplitPane root = (SplitPane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					Platform.exit();
					System.exit(0);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
		}

		if (runningServer) {
			server = new GpsServer();
			server.setDaemon(true);
			server.start();

			if (usingTestClient) {
				TestClient client = new TestClient();
				client.start();
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
