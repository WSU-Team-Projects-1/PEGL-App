package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SampleController implements Initializable {

	
	private List<GpsLog> logs = new ArrayList<>(50);

	public void saveLogsToFile() {
		// TODO Configure filename
		String fileName = "logs.csv";

		try (PrintWriter logFile = new PrintWriter(new File(fileName))) {

			for (GpsLog log : logs) {
				logFile.println(log.toString());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("logged");
	}

	// This method is called by the FXMLLoader when initialization is complete
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

		//Test values
		logs.add(new GpsLog(new GpsLocation(1,1), "test"));
		logs.add(new GpsLog(new GpsLocation(2,2), "ed"));
		
	}

	@FXML
	private SplitPane defaultPane;

	@FXML
	private Pane toolbarPane;

	@FXML
	private Button loadSearchAreaButton;

	@FXML
	private Button createGPSLogButton;

	@FXML
	private TextField annotationTextField;

	@FXML
	private Button setSearchBoundariesButton;

	@FXML
	private Button connectToDJIButton;

	@FXML
	private Button saveGPSLogButton;

	@FXML
	private Button saveLogsToFileButton;

	@FXML
	private Label currentLocationLabel;

	@FXML
	private Label gpsLocationLabel;

	@FXML
	private Pane mapPane;

}
