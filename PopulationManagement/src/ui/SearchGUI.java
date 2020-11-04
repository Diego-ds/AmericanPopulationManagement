package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    @FXML
    private RadioButton nameOption;

    @FXML
    private ToggleGroup searchOptions;

    @FXML
    private RadioButton lastNameOption;

    @FXML
    private RadioButton codeOption;

    @FXML
    private RadioButton nameLastNameOption;

    @FXML
    public void editRecord(ActionEvent event) {

    }

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
    		
    	welcome.getMainPane().setCenter(mainMenuPane);
    }
	
    public int getOption() {
    	int option =1;
    	if(lastNameOption.isSelected()) {
    		option=2;
    		return option;
    	}else if(codeOption.isSelected()) {
    		option=3;
    		return option;
    	}else if(nameLastNameOption.isSelected()) {
    		option=4;
    		return option;
    	}else {
    		return option;
    	}
    }
    
    public String getLookFor() {
    	return lookFortxt.getText();
    }
    
    public void initializeSpinner(ArrayList<String> data) {
    	ObservableList<String> obs = FXCollections.observableArrayList(data);
    	SpinnerValueFactory<String> spData = new SpinnerValueFactory.ListSpinnerValueFactory<>(obs);
    	spinner.setValueFactory(spData);
    }
    
    @FXML
    public void initialize() {
    	
    }
}
