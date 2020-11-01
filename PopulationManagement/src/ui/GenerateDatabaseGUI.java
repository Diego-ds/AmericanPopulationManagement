package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import model.Manager;

public class GenerateDatabaseGUI {
	
	private Manager manager;
	private WelcomeGUI welcome;
	private MainMenuGUI mainMenu;
	
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

    }

    @FXML
    public void saveDatabase(ActionEvent event) throws IOException {
    	

      	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/MainMenuScreen.fxml"));
    	fxmlLoader.setController(mainMenu);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	welcome.getMainPane().getChildren().clear();
    	welcome.getMainPane().setCenter(mainMenuPane);
    }

}
