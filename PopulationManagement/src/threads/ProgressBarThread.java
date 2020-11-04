package threads;

import java.io.IOException;

import model.Manager;
import model.Record;
import ui.GenerateDatabaseGUI;

public class ProgressBarThread extends Thread{
	
	private Manager manager;
	private GenerateDatabaseGUI database;
	private int recordNumber;
	
	public ProgressBarThread(Manager manager, GenerateDatabaseGUI database, int recordNumber) {
		this.manager = manager;
		this.database = database;
		this.recordNumber = recordNumber;
	}


	@Override
	public void run() {
		Record[] records = new Record[recordNumber];
		
		for(int i = 0;i<recordNumber;i++) {
			try {
				records[i] = manager.generateRecord();
				database.setProgressBar(i/recordNumber);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		manager.setRecords(records);
	}	
	
	
}
