package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddPersonGUI {

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
    public void addPerson(ActionEvent event) {

    }

}