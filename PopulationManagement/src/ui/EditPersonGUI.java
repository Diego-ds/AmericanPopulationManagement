 package ui;

import collections.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private TextField birthCamp;

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
    	birthCamp.setText("");
    	nacionalityCamp.setText("");
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Error");
		alert.setTitle("Alert");
		alert.setContentText("The record have been deleted");
		alert.showAndWait();
    }

    @FXML
    public void saveRecord(ActionEvent event) {
    	Node<String,Record> node = manager.searchValue(key);
    	node.getValue().setName(nameCamp.getText());
    	node.getValue().setLastname(lastNameCamp.getText());
    	node.getValue().setHeight(Double.parseDouble(heightCamp.getText()));
    	node.getValue().setBirthDate(birthCamp.getText());
    	node.getValue().setNacionality(nacionalityCamp.getText());
    	if(node.getValue().getGender().equals(Record.MALE)) {
    		node.getValue().setGender(Record.MALE);
    	}else {
    		node.getValue().setGender(Record.FEMALE);
    	}
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Error");
		alert.setTitle("Alert");
		alert.setContentText("The data has been saved");
		alert.showAndWait();
    }
    
    public void initialize() {
    	System.out.println(key);
    	Node<String,Record> node = manager.searchValue(key);
    	nameCamp.setText(node.getValue().getName());
    	lastNameCamp.setText(node.getValue().getLastname());
    	heightCamp.setText(String.valueOf(node.getValue().getHeight()));
    	birthCamp.setText(node.getValue().getBirthDate());
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