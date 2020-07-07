package de.hsrm.mi.swt.presentation;

import java.io.File;
import java.util.ArrayList;

import business.components.Item;
import business.components.Street;
import business.components.StreetType;
import business.simulation.Simulation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.XMLFile;

public class VerkehrssimulationMain extends Application {

	private ArrayList<Item> itemList;
	private Simulation simulation;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			this.simulation = new Simulation();

			this.itemList = simulation.getElemente();

			Parent root = FXMLLoader.load(getClass().getResource("/Verkehrssimulation.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

//			Scene scene = new Scene(root);
//			primaryStage.initStyle(StageStyle.UNIFIED);
//			primaryStage.setScene(scene);
//			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Application.launch(VerkehrssimulationMain.class, args);
	}

	private static FileChooser getFile() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(exFilter);
		return fileChooser;
	}

	public void save() {

		System.out.println("safeMain");
		File file = getFile().showSaveDialog(null);

		if (file != null) {
			System.out.println("safeMain2");
			System.out.println("LIST: " + itemList);
			XMLFile.saveAsXML(file.getPath(), itemList);

		}
	}

	public void load() {
		File file = getFile().showOpenDialog(null);
		if (file != null) {
			itemList = XMLFile.loadFromXML(file.getPath());
		}
	}
}
