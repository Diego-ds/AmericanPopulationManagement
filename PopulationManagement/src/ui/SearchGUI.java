package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import model.Manager;
import threads.SearchThread;

public class SearchGUI {
	private Manager manager;
	private WelcomeGUI welcome;
	private MainMenuGUI mainMenu;
	private EditPersonGUI edit;
	private SearchThread thread;
	
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
    public void editRecord(ActionEvent event) throws IOException {
    	if(spinner.getValue()==null) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setTitle("Alert");
			alert.setContentText("Search a valid record first");
			alert.showAndWait();
    	}else {
    		String key [] = spinner.getValue().split(",");
        	edit.setKey(key[0]);
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/EditPersonScreen.fxml"));
        	fxmlLoader.setController(edit);
        	Parent mainMenuPane = fxmlLoader.load();
        	welcome.getMainPane().setCenter(mainMenuPane);
    	}
    	
    }

	public SearchGUI(Manager manager, WelcomeGUI welcome, MainMenuGUI mainMenu) {
		this.manager = manager;
		this.welcome = welcome;
		this.mainMenu = mainMenu;
		this.edit=new EditPersonGUI(manager);
		thread= new SearchThread(manager,this);
	}
	
    @FXML
    public void goBack(ActionEvent event) throws IOException {
    	setThreadFlag(false);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/MainMenuScreen.fxml"));
    	fxmlLoader.setController(mainMenu);
    	Parent mainMenuPane = fxmlLoader.load();
    	welcome.getMainPane().setCenter(mainMenuPane);
    }
	
    public int getOption() {
    	int option =1;
    	if(lastNameOption.isSelected()) {
    		option=2;
    	}else if(codeOption.isSelected()) {
    		option=3;
    	}else if(nameLastNameOption.isSelected()) {
    		option=4;
    	}else if(nameOption.isSelected()){
    		option=1;
    	}
    	return option;
    }
    
    public String getLookFor() {
    	return lookFortxt.getText();
    }
    
    public void initializeSpinner(ArrayList<String> data) {
    	ObservableList<String> obs = FXCollections.observableArrayList(data);
    	SpinnerValueFactory<String> spData = new SpinnerValueFactory.ListSpinnerValueFactory<>(obs);
    	spinner.setValueFactory(spData);
    }
    
 
    public void initialize() {
    	thread= new SearchThread(manager,this);
    	thread.start();
    }
    
    public void setThreadFlag(boolean val) {
    	thread.setFlag(val);
    }
    
}
