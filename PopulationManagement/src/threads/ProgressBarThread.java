package threads;

import java.io.IOException;

import javafx.application.Platform;
import model.Manager;
import model.Record;
import ui.GenerateDatabaseGUI;

public class ProgressBarThread extends Thread{
	
	private Manager manager;
	private GenerateDatabaseGUI database;
	private int recordNumber;
	private double pr;
	private long time;
	
	public ProgressBarThread(Manager manager, GenerateDatabaseGUI database, int recordNumber) {
		this.manager = manager;
		this.database = database;
		this.recordNumber = recordNumber;
		pr=0.0;
	}


	@Override
	public void run() {
		long time1 = System.currentTimeMillis();
		long time2=System.currentTimeMillis();
		
		Record[] records = new Record[recordNumber];
		
		for(int i = 0;i<recordNumber;i++) {
			try {
				time2 =System.currentTimeMillis();
				if(time2-time1 > 1000) {
					Platform.runLater(new Thread() {
						@Override
						public void run() {
							database.setVisible(true);
						}
					});
				}
				double index = (double)i;
				double recordN = (double)recordNumber;
				pr = index/recordN;
				records[i] = manager.generateRecord();
				Platform.runLater(new Thread() {
					@Override
					public void run() {
						database.setProgressBar(pr);
					}
				});
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		manager.setRecords(records);
		time2 = System.currentTimeMillis();
		time=time2-time1;
		Platform.runLater(new Thread() {
			@Override
			public void run() {
				database.setTime(time);
			}
		});
		
	}	
	
	
}
