package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Manager;

public class MainMenuGUI {
	
	private Manager manager;
	private WelcomeGUI mainMenu;
	private AddPersonGUI addPerson;
	private GenerateDatabaseGUI database;
	private SearchGUI search;
	

    public MainMenuGUI(Manager manager, WelcomeGUI mainMenu) {
		this.manager = manager;
		this.mainMenu = mainMenu;
		addPerson = new AddPersonGUI(manager,mainMenu,this);
		database = new GenerateDatabaseGUI(manager);
		search = new SearchGUI(manager,mainMenu,this);
	}

	@FXML
    private Label labelTotalPeople;
	
    @FXML
    private Button searchButton;
    @FXML
    public void loadAddPerson(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/AddPersonScreen.fxml"));
    	fxmlLoader.setController(addPerson);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	mainMenu.getMainPane().setCenter(mainMenuPane);
    }

    @FXML
    public void loadGenerateDatabase(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/GenerateDatabaseScreen.fxml"));
    	fxmlLoader.setController(database);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	mainMenu.getMainPane().setCenter(mainMenuPane);
    }

    @FXML
    public void loadSearchPerson(ActionEvent event) throws IOException {
    	search.setThreadFlag(true);
    	mainMenu.setVisibleBar(false);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/SearchScreen.fxml"));
    	fxmlLoader.setController(search);
    	Parent mainMenuPane = fxmlLoader.load();	
    	mainMenu.getMainPane().setCenter(mainMenuPane);
    }
    
    public void initialize() {
    	if(manager.getPersonCounter()==0) {
    		labelTotalPeople.setText(String.valueOf(manager.getPersonCounter()));
    		searchButton.setDisable(true);
    	}else {
    		labelTotalPeople.setText(String.valueOf(manager.getPersonCounter()));
    		searchButton.setDisable(false);
    	}
    }

}
