package ui;



import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Manager;

public class Main extends Application {
	
	private Manager manager;
	private WelcomeGUI welcome;
	
	public Main() throws IOException {
		manager=new Manager();
		welcome = new WelcomeGUI(manager);
		manager.loadData();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/WelcomeScreen.fxml"));
		fxmlLoader.setController(welcome);
		
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		    	//SERIALIZAR ANTES DE CERRAR
		        Platform.exit();
		        System.exit(0);
		    }
		});
		primaryStage.show();
	}
}
