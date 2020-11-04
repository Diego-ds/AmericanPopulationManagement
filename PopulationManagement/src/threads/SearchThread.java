package threads;

import model.Manager;
import ui.SearchGUI;

public class SearchThread extends Thread{
	private Manager manager;
	private SearchGUI searchScreen;
	public SearchThread(Manager manager, SearchGUI searchScreen) {
		this.manager = manager;
		this.searchScreen = searchScreen;
	}
	
	@Override
	public void run() {
		
	}
}
