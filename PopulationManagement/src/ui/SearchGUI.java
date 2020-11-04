package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import model.Manager;

public class SearchGUI {
	private Manager manager;
	private WelcomeGUI welcome;
	private MainMenuGUI mainMenu;
	
    @FXML
    private TextField lookFortxt;

    @FXML
    private Spinner<String> spinner;
    @FXML
    private Label parameterToLooktxt;

	public SearchGUI(Manager manager, WelcomeGUI welcome, MainMenuGUI mainMenu) {
		super();
		this.manager = manager;
		this.welcome = welcome;
		this.mainMenu = mainMenu;
	}
	
    @FXML
    public void goBack(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/MainMenuScreen.fxml"));
    	fxmlLoader.setController(mainMenu);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	welcome.getMainPane().getChildren().clear();
    	welcome.getMainPane().setCenter(mainMenuPane);
    }
		
}
