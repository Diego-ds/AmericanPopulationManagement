package threads;

import java.util.ArrayList;

import javafx.application.Platform;
import model.Manager;
import ui.SearchGUI;

public class SearchThread extends Thread{
	private Manager manager;
	private SearchGUI searchScreen;
	private boolean flag;
	private ArrayList<String> spinnerData;
	public SearchThread(Manager manager, SearchGUI searchScreen) {
		this.manager = manager;
		this.searchScreen = searchScreen;
	}
	
	@Override
	public void run() {
		int option = searchScreen.getOption();
		String toLook = searchScreen.getLookFor();
		while(flag) {
			if(option==1) {
				spinnerData= manager.searchByName(toLook);
			}else if(option==2) {
				spinnerData= manager.searchByLastName(toLook);
			}else if(option==3) {
				spinnerData= manager.searchByCode(toLook);
			}else if(option==4) {
				spinnerData= manager.searchByNameAndLastname(toLook);
			}
			
			Platform.runLater(new Thread() {
				@Override
				public void run() {
					searchScreen.initializeSpinner(spinnerData);
				}
			});
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setFlag(boolean flag) {
		this.flag=flag;
	}
}
