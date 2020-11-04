package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import model.Manager;

public class WelcomeGUI {

	private MainMenuGUI mainMenu;
	private Manager manager;
	
    @FXML
    private BorderPane mainPane;
    
    public WelcomeGUI(Manager manager) {
		this.manager = manager;
		mainMenu = new MainMenuGUI(manager,this);
	}

	@FXML
    public void aboutUs(ActionEvent event) {

    }

    @FXML
    public void goMenu(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/MainMenuScreen.fxml"));
    	fxmlLoader.setController(mainMenu);
    	
    	Parent searchPane = fxmlLoader.load();
    	mainPane.setCenter(searchPane);
    }
    
    public BorderPane getMainPane() {
    	return mainPane;
    }
}