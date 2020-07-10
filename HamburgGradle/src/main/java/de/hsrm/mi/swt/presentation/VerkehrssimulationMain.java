package de.hsrm.mi.swt.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VerkehrssimulationMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/Verkehrssimulation.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(VerkehrssimulationMain.class, args);
	}
}
