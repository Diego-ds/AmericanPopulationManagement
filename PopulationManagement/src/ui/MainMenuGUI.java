package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    void loadAddPerson(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/AddPersonScreen.fxml"));
    	fxmlLoader.setController(addPerson);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	mainMenu.getMainPane().setCenter(mainMenuPane);
    }

    @FXML
    void loadGenerateDatabase(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/GenerateDatabaseScreen.fxml"));
    	fxmlLoader.setController(database);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	mainMenu.getMainPane().setCenter(mainMenuPane);
    }

    @FXML
    void loadSearchPerson(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/SearchScreen.fxml"));
    	fxmlLoader.setController(search);
    	
    	Parent mainMenuPane = fxmlLoader.load();
    		
    	mainMenu.getMainPane().setCenter(mainMenuPane);
    }

}
