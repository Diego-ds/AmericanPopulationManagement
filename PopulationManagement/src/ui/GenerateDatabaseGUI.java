package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import model.Manager;
import threads.ProgressBarThread;

public class GenerateDatabaseGUI {
	
	private Manager manager;
	private WelcomeGUI welcome;
	private MainMenuGUI mainMenu;
	private ProgressBarThread thread;
	
    public GenerateDatabaseGUI(Manager manager, WelcomeGUI welcome, MainMenuGUI mainMenu) {
		
		this.manager = manager;
		this.welcome = welcome;
		this.mainMenu = mainMenu;
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
    		thread.start();
    	}
    }

    @FXML
    public void saveDatabase(ActionEvent event) throws IOException {
    	

      	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/MainMenuScreen.fxml"));
    	fxmlLoader.setController(mainMenu);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	welcome.getMainPane().getChildren().clear();
    	welcome.getMainPane().setCenter(mainMenuPane);
    }
    
    
    public void initialize() {
    	progressBarGeneration.setProgress(0.0);
    }
    
    public void setProgressBar(double progress) {
    	progressBarGeneration.setProgress(progress);
    }

}
