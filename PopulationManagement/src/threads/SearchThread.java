package threads;

import java.util.ArrayList;

import javafx.application.Platform;
import model.Manager;
import ui.SearchGUI;

public class SearchThread extends Thread{
	private Manager manager;
	private SearchGUI searchScreen;
	private boolean flag;
	private String controller;
	private ArrayList<String> spinnerData;
	
	public SearchThread(Manager manager, SearchGUI searchScreen) {
		spinnerData = new ArrayList<String>();
		this.manager = manager;
		this.searchScreen = searchScreen;
		flag=true;
		controller = "";
	}
	
	@Override
	public void run() {
		
		while(flag) {
			
			String toLook = searchScreen.getLookFor();
			if(controller.equals(toLook)) {
				
			}else {
				int option = searchScreen.getOption();
				if(option==1) {
					spinnerData= manager.searchByName(toLook);
					controller=toLook;
				}else if(option==2) {
					spinnerData= manager.searchByLastName(toLook);
					controller=toLook;
				}else if(option==3) {
					spinnerData= manager.searchByCode(toLook);
					controller=toLook;
				}else if(option==4) {
					spinnerData= manager.searchByNameAndLastname(toLook);
					controller=toLook;
				}
				
				Platform.runLater(new Thread() {
					@Override
					public void run() {
						searchScreen.initializeSpinner(spinnerData);
					}
				});
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setFlag(boolean flag) {
		this.flag=flag;
	}
}
