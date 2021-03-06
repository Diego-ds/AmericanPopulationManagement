package threads;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import model.Manager;
import model.Record;
import ui.GenerateDatabaseGUI;

public class ProgressBarThread extends Thread{
	
	private Manager manager;
	private GenerateDatabaseGUI database;
	private int recordNumber;
	private double pr;
	private String time;
	
	public ProgressBarThread(Manager manager, GenerateDatabaseGUI database, int recordNumber) {
		this.manager = manager;
		this.database = database;
		this.recordNumber = recordNumber;
		pr=0.0;
	}


	@Override
	public void run() {
		database.setVisible(false);
		long time1 = System.currentTimeMillis();
		long time2 = System.currentTimeMillis();
		
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		manager.setRecords(records);
		time2 = System.currentTimeMillis();
		long time3 =time2-time1;
		time = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(time3),
	            TimeUnit.MILLISECONDS.toMinutes(time3) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time3)),
	            TimeUnit.MILLISECONDS.toSeconds(time3) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time3)));
		Platform.runLater(new Thread() {
			@Override
			public void run() {
				database.setTime(time);
			}
		});
		
	}	
}
