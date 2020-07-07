package de.hsrm.mi.swt.presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.ui.Clipboard;

import business.components.Crossing;
import business.components.Curve;
import business.components.Item;
import business.components.Junction;
import business.components.Straight;
import business.components.Street;
import business.simulation.Grid;
import business.simulation.Simulation;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class VerkehrssimulationController implements Initializable {

	private static final int GRIDSIZE = 5;
//TODO: private machen
	MenuItem rightClickMenu;
	ContextMenu contextMenu;
	Simulation simulation;
	Grid grid = new Grid(GRIDSIZE);

	private FileInputStream inputstream;
	private Node car;

	// @FXML
	// private AnchorPane loadSimulationPane;

	@FXML
	private Button loadiSimulationButton;

	@FXML
	private Button loadSimulationBackToMenu;

	@FXML
	private AnchorPane menuPane;

	@FXML
	private Button menuNewSimulationButton;

	@FXML
	private Button menuLoadSimulationButton;

	@FXML
	private Button quitSimulationButton;

	@FXML
	private AnchorPane simulationPane;

	@FXML
	private AnchorPane simulationGrid;

	@FXML
	private ImageView ImageGrid_0_0;

	@FXML
	private ImageView ImageGrid_0_1;

	@FXML
	private ImageView ImageGrid_0_2;

	@FXML
	private ImageView ImageGrid_0_3;

	@FXML
	private ImageView ImageGrid_0_4;

	@FXML
	private ImageView ImageGrid_1_0;

	@FXML
	private ImageView ImageGrid_1_1;

	@FXML
	private ImageView ImageGrid_1_2;

	@FXML
	private ImageView ImageGrid_1_3;

	@FXML
	private ImageView ImageGrid_1_4;

	@FXML
	private ImageView ImageGrid_2_0;

	@FXML
	private ImageView ImageGrid_2_1;

	@FXML
	private ImageView ImageGrid_2_2;

	@FXML
	private ImageView ImageGrid_2_3;

	@FXML
	private ImageView ImageGrid_2_4;

	@FXML
	private ImageView ImageGrid_3_0;

	@FXML
	private ImageView ImageGrid_3_1;

	@FXML
	private ImageView ImageGrid_3_2;

	@FXML
	private ImageView ImageGrid_3_3;

	@FXML
	private ImageView ImageGrid_3_4;

	@FXML
	private ImageView ImageGrid_4_0;

	@FXML
	private ImageView ImageGrid_4_1;

	@FXML
	private ImageView ImageGrid_4_2;

	@FXML
	private ImageView ImageGrid_4_3;

	@FXML
	private ImageView ImageGrid_4_4;

	@FXML
	private Text showInfoLabelOne;

	@FXML
	private Text showInfoLabelTwo;

	@FXML
	private Text showInfoLabelThree;

	@FXML
	private Button controllButtonStart;

	@FXML
	private Button controllButtonPause;

	@FXML
	private Button controllButtonStop;

	@FXML
	private Button controllButtonIncrease;

	@FXML
	private Button controllButtonDrecease;

	@FXML
	private ImageView StreetElementStraight;

	@FXML
	private ImageView StreetElementCurve;

	@FXML
	private ImageView StreetElementCrossing;

	@FXML
	private ImageView StreetElementJunction;

	@FXML
	private ImageView StreetElementCar;

	@FXML
	private ImageView StreetElementTrafficLight;

	@FXML
	private Button functionButtonSafe;

	@FXML
	private Button functioButtonLoad;

	@FXML
	private Button functionButtonMenuBack;

	@FXML
	private Button functionButtonQuit;

	@FXML
	void getGridPosition(MouseEvent event) {

	}

	@FXML
	void handleDragDetection(MouseEvent event) {

		String id = null;

		ImageView picked = (ImageView) event.getPickResult().getIntersectedNode();

		if (picked.getId().equals("StreetElementStraight")) {
			id = "Straight";
		} else if (picked.getId().equals("StreetElementCrossing")) {
			id = "Crossing";
		} else if (picked.getId().equals("StreetElementCurve")) {
			id = "Curve";
		} else if (picked.getId().equals("StreetElementJunction")) {
			id = "Junction";
		}

		Dragboard db = picked.startDragAndDrop(TransferMode.ANY);
		ClipboardContent cb = new ClipboardContent();

		cb.putImage(picked.getImage());
		cb.putString(id);

		db.setContent(cb);

		event.consume();

	}

	@FXML
	void handleImageDragOver(DragEvent event) {
		if (event.getDragboard().hasImage()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}

	@FXML
	void handleImageDrop(DragEvent event) {

		Node node = event.getPickResult().getIntersectedNode();

		double doubleX = event.getSceneX();
		double doubleY = event.getSceneY();

		System.out.println(doubleY + " " + doubleX);

		Integer cIndex = GridPane.getColumnIndex(node);
		Integer rIndex = GridPane.getRowIndex(node);
		int x = cIndex == null ? 0 : cIndex;
		int y = rIndex == null ? 0 : rIndex;

		// grid.placeStreet(new, x, y);

		System.out.println(x + " " + y);

		Image img = event.getDragboard().getImage();
		String id = event.getDragboard().getString();

		((ImageView) event.getPickResult().getIntersectedNode()).setImage(img); // TODO: Listener schreiben, hierfür
																				// muss ein listener auf das Grid
																				// laufen, welches dann an der passenden
																				// Stelle ein Bild platziert

		switch (id) {
		case "Straight":

			grid.placeStreet(new Straight(), x, y);

			break;

		case "Curve":
			grid.placeStreet(new Curve(), x, y);
			break;

		case "Junction":
			grid.placeStreet(new Junction(), x, y);
			break;

		case "Crossing":
			grid.placeStreet(new Crossing(), x, y);
			break;

		case "StreetElementCar":

			grid.placeStreet(new Crossing(), x, y);

			try {
				inputstream = new FileInputStream("smallcar.png");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Autobild laden fehlgeschlagen");
			}

			Image image = new Image(inputstream);
			car = new ImageView(image);
			simulationGrid.getChildren().add(car);
			
			break;

		default:
			break;
		}

		initStreetListener(grid.getStreet(x, y));

	}

	private void initStreetListener(Street street) {

		// TODO: Listener schreiben, der auf Listener hört. street.rotation.addListener,
		// so etwa

	}

	@FXML
	void onMouseClickedGrid(MouseEvent event) {

		Node node = event.getPickResult().getIntersectedNode();

		Integer cIndex = GridPane.getColumnIndex(node);
		Integer rIndex = GridPane.getRowIndex(node);
		int x = cIndex == null ? 0 : cIndex;
		int y = rIndex == null ? 0 : rIndex;

		if (event.getButton() == MouseButton.PRIMARY) {
			int counter = event.getClickCount(); // TODO: Clicks müssen schnell hintereinander erfolgen, sonst keine

			System.out.println(grid.getStreet(x, y).getClass().toString());

			Street street = grid.getStreet(x, y);

			// TODO: gucken ob anzahl mit Drehungen stimmt.

			if (street.getClass().toString().contains("Straight")) {
				((Straight) street).rotate();
			} else if (street.getClass().toString().contains("Curve")) {
				((Curve) street).rotate();
			} else if (street.getClass().toString().contains("Junction")) {
				((Junction) street).rotate();
			} else if (street.getClass().toString().contains("Crossing")) {
				((Crossing) street).rotate();
			}

			node.setRotate(90 * counter); // TODO: Ersetzen durch listener
			counter = 0;
		}

		if (event.getButton() == MouseButton.SECONDARY) {

			ImageView img = (ImageView) node;
			img.setImage(null);

		}

	}

	@FXML
	void carControls(KeyEvent event) {

		System.out.println(event.getCharacter());

	}

	@FXML
	void handleCarDragDetection(MouseEvent event) {

	}

	@FXML
	void backToMenu(ActionEvent event) {
		scrollToMenu();
	}

	@FXML
	void endSimulation(ActionEvent event) {

	}

	@FXML
	void increaseSpeed(ActionEvent event) {

	}

	@FXML
	void loadSimulationFile(ActionEvent event) {
		VerkehrssimulationMain.load();
	}

	@FXML
	void newSimulation(ActionEvent event) {
		scrollToSimulation();
	}

	@FXML
	void pauseSimulation(ActionEvent event) {

	}

	@FXML
	void quitSimulation(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void quitSiumulation(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void remove(ActionEvent event) {

	}

	@FXML
	void safeSimulation(ActionEvent event) {
		VerkehrssimulationMain.save();
	}

	@FXML
	void showProcets(ActionEvent event) {

	}

	@FXML
	void startSimulation(ActionEvent event) {

//		event.getScreenX(), event.getScreenY()

//		Group group = new Group();
//		Path path = new Path();

//		path.getElements().add(new MoveTo(20, 20));
//		path.getElements().add(new CubicCurveTo(30, 30, 30, 100, 500,100));
//		path.getElements().add(new CubicCurveTo(200, 500, 110, 240, 10, 240));
//		path.setOpacity(0.1);
//
//		group.getChildren().add(path);
//		group.getChildren().add(car);
//		simulationGrid.getChildren().add(group);
//
//		PathTransition pathTransition = new PathTransition();
//
//		pathTransition.setDuration(Duration.seconds(8.0));
//		pathTransition.setDelay(Duration.seconds(.5));
//		pathTransition.setPath(path);
//		pathTransition.setNode(car);
//		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//		pathTransition.setCycleCount(Timeline.INDEFINITE);
//		pathTransition.setAutoReverse(true);
//		pathTransition.play();

	}

	public void scrollToMenu() {
		/*
		 * TranslateTransition tr1 = new TranslateTransition();
		 * tr1.setDuration(Duration.millis(300)); tr1.setToX(0); tr1.setToY(-700);
		 * tr1.setNode(loadSimulationPane);
		 */
		TranslateTransition tr3 = new TranslateTransition();
		tr3.setDuration(Duration.millis(300));
		tr3.setToX(0);
		tr3.setToY(-700);
		tr3.setNode(simulationPane);
		TranslateTransition tr2 = new TranslateTransition();
		tr2.setDuration(Duration.millis(300));
		tr2.setFromX(0);
		tr2.setFromY(700);
		tr2.setToX(0);
		tr2.setToY(0);
		tr2.setNode(menuPane);
		ParallelTransition pt = new ParallelTransition(tr2, tr3);
		pt.play();
	}

	public void scrollToSimulation() {
		TranslateTransition tr1 = new TranslateTransition();
		tr1.setDuration(Duration.millis(300));
		tr1.setToX(0);
		tr1.setToY(-700);
		tr1.setNode(menuPane);
		/*
		 * TranslateTransition tr3 = new TranslateTransition();
		 * tr3.setDuration(Duration.millis(300)); tr3.setToX(0); tr3.setToY(-700);
		 * tr3.setNode(loadSimulationPane);
		 */
		TranslateTransition tr2 = new TranslateTransition();
		tr2.setDuration(Duration.millis(300));
		tr2.setFromX(0);
		tr2.setFromY(700);
		tr2.setToX(0);
		tr2.setToY(0);
		tr2.setNode(simulationPane);
		ParallelTransition pt = new ParallelTransition(tr1, tr2);
		pt.play();
	}

	public VerkehrssimulationController() {
		rightClickMenu = new MenuItem("Element löschen");
		contextMenu = new ContextMenu();
		simulation = new Simulation();

//		aktGrid.xPos.addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				System.out.println("Neuer Wert Property: " + newValue);
//				
//			}
//			
//		});
//
//		aktGrid.yPos.addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				System.out.println("Neuer Wert Property: " + newValue);
//				
//			}
//			
//		});
		// initialize();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO: hier den Listener aufs Grid, wenn ein neues Image ins Feld platziert
		// wird
		// grid.getCurren..

	}

}