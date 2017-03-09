package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SampleController  {

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



	

}
