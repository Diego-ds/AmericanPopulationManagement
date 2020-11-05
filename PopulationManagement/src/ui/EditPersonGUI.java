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
    	
    }

    @FXML
    public void goBack(ActionEvent event) {

    }

    @FXML
    public void saveRecord(ActionEvent event) {
    	
    }
    
    public void initialize() {
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