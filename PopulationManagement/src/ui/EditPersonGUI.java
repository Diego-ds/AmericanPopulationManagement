 package ui;

import java.time.LocalDate;

import collections.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import model.Manager;
import model.Record;

public class EditPersonGUI {
	private Manager manager;
	private String key;

    public EditPersonGUI(Manager manager) {
		super();
		this.manager = manager;
	}

	@FXML
    private TextField nameCamp;

    @FXML
    private TextField lastNameCamp;

    @FXML
    private TextField heightCamp;

    @FXML
    private DatePicker datePickerBirthDate;

    @FXML
    private TextField nacionalityCamp;

    @FXML
    private RadioButton maleRButton;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton femaleRButton;
    
    @FXML
    public void deleteRecord(ActionEvent event) {
    	manager.deleteValue(key);
    	nameCamp.setText("");
    	lastNameCamp.setText("");
    	heightCamp.setText("");
    	datePickerBirthDate.setValue(null);
    	nacionalityCamp.setText("");
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Congrats!");
		alert.setTitle("Successful operation");
		alert.setContentText("Person deleted successfully!");
		alert.showAndWait();
    }

    @FXML
    public void saveRecord(ActionEvent event) {
    	try {
    		
    		Node<String,Record> node = manager.searchValue(key);
    		//Creating necessary variables
    		String name;
    		String lastName;
    		String birthDate;
    		String heightString;
    		String nationality;
    		
    		//Getting values
    		name = nameCamp.getText();
    		lastName = lastNameCamp.getText();

    		if (datePickerBirthDate.getValue() == null) {
    			throw new IllegalArgumentException("Birth Date");
    		}
    		
    		else {
    			birthDate = datePickerBirthDate.getValue().toString();
    		}
    		
    		heightString = heightCamp.getText();
    		
    		nationality = nacionalityCamp.getText();
    		
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

    		//Sets
    		node.getValue().setName(name);
        	node.getValue().setLastname(lastName);
        	node.getValue().setHeight(height);
        	node.getValue().setBirthDate(birthDate);
        	node.getValue().setNacionality(nationality);
        	if(node.getValue().getGender().equals(Record.MALE)) {
        		node.getValue().setGender(Record.MALE);
        	}else {
        		node.getValue().setGender(Record.FEMALE);
        	}
        	
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Congrats!");
    		alert.setHeaderText("Successful operation");
    		alert.setContentText("Data updated successfully!");

    		alert.showAndWait();
    	} catch (IllegalArgumentException iae) {
        		Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("Something went wrong...");
        		alert.setHeaderText("Input error");
        		alert.setContentText("The following field is empty or has incorrect values: \n" +
        				iae.getMessage());

        		alert.showAndWait();
        	}
    }
    
    public void initialize() {
    	Node<String,Record> node = manager.searchValue(key);
    	nameCamp.setText(node.getValue().getName());
    	lastNameCamp.setText(node.getValue().getLastname());
    	heightCamp.setText(String.valueOf(node.getValue().getHeight()));
    	datePickerBirthDate.setValue(LocalDate.parse(node.getValue().getBirthDate()));
    	nacionalityCamp.setText(node.getValue().getNacionality());
    	
    	if(node.getValue().getGender().equals(Record.MALE)) {
    		maleRButton.setSelected(true);
    	}else {
    		femaleRButton.setSelected(true);
    	}
    }
    
    public void setKey(String key) {
    	this.key=key;
    }
}