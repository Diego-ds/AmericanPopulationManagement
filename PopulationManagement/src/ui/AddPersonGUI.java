 package ui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    	try {
    		//Creating necessary variables
    		String name;
    		String lastName;
    		String gender;
    		String birthDate;
    		String heightString;
    		String nationality;
    		
    		//Getting values
    		name = textFieldFirstName.getText();
  
    		lastName = textFieldLastName.getText();

    		gender = ((RadioButton)toogleGroupGender.getSelectedToggle()).getText();
    		
    		if (datePickerBirthDate.getValue() == null) {
    			throw new IllegalArgumentException("Birth Date");
    		}
    		
    		else {
    			birthDate = datePickerBirthDate.getValue().toString();
    		}
    		
    		heightString = textFieldHeight.getText();
    		
    		nationality = textFieldNationality.getText();
    		
    		//Control
    		if (name.length() == 0 || name.matches(".*\\d.*")) {
    			throw new IllegalArgumentException("First name");
    		}

    		if (lastName.length() == 0 || lastName.matches(".*\\d.*")) {
    			throw new IllegalArgumentException("Last name");
    		}

    		if (datePickerBirthDate.getValue().isAfter(LocalDate.now())) {
    			throw new IllegalArgumentException("Birth Date");
    		}
    		
    		double height = Double.parseDouble(heightString);

    		if (nationality.length() == 0 || nationality.matches(".*\\d.*")) {
    			throw new IllegalArgumentException("Nationality");
    		}

    		manager.addRecord(name, lastName, gender, birthDate, height, nationality);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Congrats!");
    		alert.setHeaderText("Successful operation");
    		alert.setContentText("Person added successfully!");

    		alert.showAndWait();
    	}	
    	
    	catch (IllegalArgumentException iae) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Something went wrong...");
    		alert.setHeaderText("Input error");
    		alert.setContentText("The following field is empty or has incorrect values: \n" +
    				iae.getMessage());

    		alert.showAndWait();
    	}
    }
}
