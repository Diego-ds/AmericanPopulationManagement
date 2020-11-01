package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class EditPersonGUI {

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
    void deleteRecord(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void saveRecord(ActionEvent event) {

    }
}
