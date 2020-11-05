package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Hey");
		alert.setTitle("Hope you having a great day!");
		alert.setContentText("Greetings from Alejandro Fonseca and Diego Garcia");
		alert.showAndWait();
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