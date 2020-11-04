 package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Manager;

public class AddPersonGUI {
	private Manager manager;
	private WelcomeGUI welcome;
	private MainMenuGUI mainMenu;
	
    public AddPersonGUI(Manager manager, WelcomeGUI welcome,MainMenuGUI mainMenu) {
    	this.mainMenu=mainMenu;
		this.manager = manager;
		this.welcome=welcome;
	}

	@FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private RadioButton radioButtonMale;

    @FXML
    private ToggleGroup toogleGroupGender;

    @FXML
    private RadioButton radioButtonFemale;

    @FXML 
    private TextField textFieldBirthdate;

    @FXML
    private TextField textFieldHeight;

    @FXML
    private TextField textFieldNationality;

    @FXML
    public void addPerson(ActionEvent event) throws IOException {

      	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/MainMenuScreen.fxml"));
    	fxmlLoader.setController(mainMenu);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	welcome.getMainPane().getChildren().clear();
    	welcome.getMainPane().setCenter(mainMenuPane);
    }

}
