package ui;

import model.Manager;

public class SearchGUI {
	private Manager manager;
	private WelcomeGUI welcome;
	private MainMenuGUI mainMenu;
	
	public SearchGUI(Manager manager, WelcomeGUI welcome, MainMenuGUI mainMenu) {
		super();
		this.manager = manager;
		this.welcome = welcome;
		this.mainMenu = mainMenu;
	}
	
}
