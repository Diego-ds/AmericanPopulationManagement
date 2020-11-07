package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Manager;
import threads.ProgressBarThread;

public class GenerateDatabaseGUI {
	
	private Manager manager;
	private ProgressBarThread thread;
	
    public GenerateDatabaseGUI(Manager manager) {
		
		this.manager = manager;
		
	}

	@FXML
    private TextField textFieldRegistersNo;

    @FXML
    private ProgressBar progressBarGeneration;

    @FXML
    private Label labelGenerationTime;

    @FXML
    public void generateDatabase(ActionEvent event) {
    	if(textFieldRegistersNo.getText().equals("")) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setTitle("Alert");
			alert.setContentText("Enter the number of records that you want to generate");
			alert.showAndWait();
    	}else {
    		int number = Integer.parseInt(textFieldRegistersNo.getText());
    		thread = new ProgressBarThread(manager, this, number);
    		thread.setDaemon(true);
    		thread.start();
    	}
    }

    @FXML
    public void saveDatabase(ActionEvent event) throws IOException {
    	manager.saveData();
		
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Congrats!");
		alert.setHeaderText("Successful operation");
		alert.setContentText("Database saved successfully!");
		
		alert.showAndWait();
    }
    
    
    public void initialize() {
    	progressBarGeneration.setProgress(0.0);
    }
    
    public void setProgressBar(double progress) {
    	progressBarGeneration.setProgress(progress);
    }
    
    public void setTime(String time) {
    	labelGenerationTime.setText(time);
    }
    
    public void setVisible(boolean val) {
    	progressBarGeneration.setVisible(val);
    }

}
