 package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
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
    private DatePicker datePickerBirthDate;

    @FXML
    private TextField textFieldHeight;

    @FXML
    private TextField textFieldNationality;

    @FXML
    public void addPerson(ActionEvent event) throws IOException {
    	
    	//Getting values
    	String name = textFieldFirstName.getText();
    	String lastName = textFieldLastName.getText();
    	String gender = ((RadioButton)toogleGroupGender.getSelectedToggle()).getText();
    	String birthDate = datePickerBirthDate.getValue().toString();
    	String height = textFieldHeight.getText();
    	String nationality = textFieldNationality.getText();
    	
    	//Control
    	if (name.length() == 0 || name.contains("\\d+")) {
    		throw new IllegalArgumentException("First name");
    	}
    	
       	if (lastName.length() == 0 || lastName.contains("\\d+")) {
    		throw new IllegalArgumentException("Last name");
    	}
       	
       	if (birthDate.length() == 0) {
       		throw new IllegalArgumentException("Birth Date");
       	}
       	
       	if (height.length() == 0 || height.contains("\\D+")) {
       		throw new IllegalArgumentException("Height");
       	}
       	
       	if (nationality.length() == 0 || name.contains("\\d+")) {
       		throw new IllegalArgumentException("Nationality");
       	}
       	
       	//TERMINAR AGREGAR
       	
      	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/MainMenuScreen.fxml"));
    	fxmlLoader.setController(mainMenu);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	welcome.getMainPane().setCenter(mainMenuPane);
    }
}
