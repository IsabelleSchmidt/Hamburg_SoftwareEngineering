package de.hsrm.mi.swt.presentation;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import business.components.Crossing;
import business.components.Curve;
import business.components.Direction;
import business.components.Junction;
import business.components.Straight;
import business.components.Street;
import business.components.Trafficlight;
import business.components.TrafficlightStatus;
import business.components.TriggerPoints;
import business.components.Vehicle;
import business.simulation.Grid;
import business.simulation.Simulation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class VerkehrssimulationController implements Initializable {

	private static final int GRIDSIZE = 5;
	private Grid grid = new Grid(GRIDSIZE);
	private Simulation simulation;
	// private ImageView[][] imageViewField;
	private FileInputStream inputstream;
	private HashMap<Vehicle, ImageView> vehicles = new HashMap<>();
	private Timeline timelineTrafficLights, timelineVehicle;
	private AnimationTimer timerTrafficLights, timerVehicle;

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
		} else if (picked.getId().equals("StreetElementCar")) {
			id = "StreetElementCar";
		} else if (picked.getId().equals("StreetElementTrafficLight")) {
			id = "TrafficLight";
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

		// System.out.println(doubleY + " " + doubleX);

		Integer cIndex = GridPane.getColumnIndex(node);
		Integer rIndex = GridPane.getRowIndex(node);
		int x = cIndex == null ? 0 : cIndex;
		int y = rIndex == null ? 0 : rIndex;

		// grid.placeStreet(new, x, y);

		Image img = event.getDragboard().getImage();
		String id = event.getDragboard().getString();

		// System.out.println(id);

		switch (id) {
		case "Straight":

			Straight straight = new Straight();
			grid.placeStreet(straight, x, y);
			initStreetListener(straight);

			break;

		case "Curve":

			Curve curve = new Curve();
			grid.placeStreet(curve, x, y);
			initStreetListener(curve);
			break;

		case "Junction":
			Junction junction = new Junction();
			grid.placeStreet(junction, x, y);
			initStreetListener(junction);
			break;

		case "Crossing":
			Crossing crossing = new Crossing();
			grid.placeStreet(crossing, x, y);
			break;

		case "TrafficLight":
			List<Trafficlight> trafficlights = new ArrayList<>();
			for (Direction d : grid.getStreet(x, y).getDirections()) {
				Trafficlight trafficlight = new Trafficlight(d);
				trafficlights.add(trafficlight);
			}

			grid.getStreet(x, y).addTrafficlights(trafficlights);

			// gui anzeigen

			break;

		case "StreetElementCar":

			try {
				inputstream = new FileInputStream("smallcar.png");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Autobild laden fehlgeschlagen");
			}

			Image image = new Image(inputstream);
			ImageView vehicleImgV = new ImageView(image);

			simulationGrid.getChildren().add(vehicleImgV);
			Vehicle v = new Vehicle(x * 100, y * 100, grid);
			vehicleImgV.setOnMouseClicked(e -> {

				if (e.getButton() == MouseButton.PRIMARY) {
					v.rotate();
				}
			});

			vehicleImgV.setFitHeight(24);
			vehicleImgV.setFitWidth(24);

			vehicleImgV.setX(v.getXCarProperty().get() - vehicleImgV.getFitHeight() / 2);
			vehicleImgV.setY(v.getYCarProperty().get() - vehicleImgV.getFitWidth() / 2);
			vehicles.put(v, vehicleImgV);
			initVehicleListener(v);

			break;

		default:
			break;
		}
		// System.out.println(id);
		if (!id.equals("StreetElementCar")) {
			((ImageView) event.getPickResult().getIntersectedNode()).setImage(img);
		}

		// TODO: Listener schreiben, hierfür
		// muss ein listener auf das Grid
		// laufen, welches dann an der passenden
		// Stelle ein Bild platziert

	}

	private void initVehicleListener(Vehicle v) {

		// TODO: Bild verschieben

		v.getXCarProperty().addListener((observable, oldValue, newV) -> {
			ImageView imageV = vehicles.get(v);
			imageV.setX(v.getXPosition() - imageV.getFitWidth() / 2);

//			if (newV.equals(400)) {
//				v.setDirection(Direction.DOWN);
//			}

			// System.out.println("Gridpos x: " + newV.intValue() / 100);
		});

		v.getYCarProperty().addListener((observable, oldValue, newV) -> {
			ImageView imageV = vehicles.get(v);
			imageV.setY(v.getYPosition() - imageV.getFitHeight() / 2);

		});

	}

	private void initStreetListener(Street street) {

		street.getRotationCount().addListener((observable, oldValue, newV) -> {
			System.out.println(newV);

		});

	}

	private void initSimulationListener(Simulation simulation) {

		simulation.getPlaybackSpeed().addListener((observable, oldValue, newV) -> {

			double currentRate = timelineTrafficLights.getRate();
			System.out.println(currentRate);
			timelineTrafficLights.setRate(newV.doubleValue()); // speed up count down
			System.out.println(timelineTrafficLights.getRate());

		});
	}

	@FXML
	void onMouseClickedGrid(MouseEvent event) {

		Node node = event.getPickResult().getIntersectedNode();

		Integer cIndex = GridPane.getColumnIndex(node);
		Integer rIndex = GridPane.getRowIndex(node);
		int x = cIndex == null ? 0 : cIndex;
		int y = rIndex == null ? 0 : rIndex;

		if (event.getButton() == MouseButton.PRIMARY) {

			Street street = grid.getStreet(x, y);
			// Vehicle car = new Vehicle(x, y, grid)

			if (street.getClass().toString().contains("Straight")) {
				((Straight) street).rotate();
			} else if (street.getClass().toString().contains("Curve")) {
				((Curve) street).rotate();
			} else if (street.getClass().toString().contains("Junction")) {
				((Junction) street).rotate();
			} else if (street.getClass().toString().contains("Crossing")) {
				((Crossing) street).rotate();
			}

			node.setRotate(90 * street.getRotationCount().get());
		}

		if (event.getButton() == MouseButton.SECONDARY) {
//TODO: gleiches spiel für remove machen
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
		// TODO: timer.stop(), alles in der Business auf null.
	}

	@FXML
	void increaseSpeed(ActionEvent event) {
		simulation.increasePlaybackSpeed();

	}

	@FXML
	void decreaseSpeed(ActionEvent event) {
		simulation.decreasePlaybackSpeed();
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
		timerTrafficLights.stop();
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

		TriggerPoints trigger = new TriggerPoints();
		simulation = new Simulation();
		initSimulationListener(simulation);
		double playbackSpeed = simulation.getPlaybackSpeed().get();

		timelineTrafficLights = new Timeline();
		timelineTrafficLights.setCycleCount(Timeline.INDEFINITE);
		timelineTrafficLights.setAutoReverse(true);

		timerTrafficLights = new AnimationTimer() {

			@Override
			public void handle(long now) {

				for (Vehicle car : vehicles.keySet()) {
					boolean drive = true;

					// trigger.chooseRandomDirection(street, streetDirection, car.getDirection());
					// street wie folgt ermitteln: akutellen Koorditen/100, damit im grid x,y
					// raussuchen und direction ziehen

					/*
					 * Um Abbiegung zu erkennen, folgendes:
					 * 
					 * - triggerPunkt festlegen - ermittel das Stück Straße unter dir - in welche
					 * Richtungen kannst du dort abbiegen? - aus welcher Richtung kommst du? Die
					 * muss abgezogen werden - nächste richtungsänderung speichern - Richtung ändern
					 * an stelle x/y -
					 * 
					 */

					int x = car.getXPosition();
					int y = car.getYPosition();

					if (trigger.isTriggered(car, x, y)) {
						car.setNextDirection(
								trigger.chooseRandomDirection(grid.getStreet(x / 100, y / 100), car.getDirection()));

						for (Trafficlight t : grid.getStreet(x / 100, y / 100).getTrafficlights()) {

							if (t.getDirection().equals(car.tellOposite(car.getDirection()))) {

								if (!(t.getStatus().get() == TrafficlightStatus.GREEN)) {

									drive = false;
								}
							}
						}

					}

					if (trigger.canTurnTo(car) == car.getNextDirection()) {
						car.setDirection(car.getNextDirection());

					}

					if (drive) {
						car.drive();
					}

				}
			}
		};

//		Duration duration = Duration.millis(10);
//		KeyFrame keyFrame = new KeyFrame(duration);
//
//		timeline.getKeyFrames().add(keyFrame);
//		timeline.play();s
		timerTrafficLights.start();
		
		

		Duration durationTrafficLight = Duration.millis(3000);
		KeyFrame trafficLightFrame = new KeyFrame(durationTrafficLight, e -> {

			for (int x = 0; x < GRIDSIZE; x++) {
				for (int y = 0; y < GRIDSIZE; y++) {
					if (grid.getStreet(x, y) != null) {
						for (Trafficlight t : grid.getStreet(x, y).getTrafficlights()) {

							t.switchLight();
							// System.out.println(t.getStatus());
						}
					}

				}

			}
		});

		timelineTrafficLights = new Timeline();
		timelineTrafficLights.getKeyFrames().add(trafficLightFrame);
		timelineTrafficLights.setCycleCount(Timeline.INDEFINITE);

		timelineTrafficLights.play();

	}

	public void scrollToMenu() {
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

	public void initialize(URL arg0, ResourceBundle arg1) {

//		grid.getGrid().addListener((observable, oldValue, newV) -> {
//
//			for (int i = 0; i < GRIDSIZE; i++) {
//				for (int j = 0; j < GRIDSIZE; j++) {
//
//					// String[][] streets = new String[GRIDSIZE][GRIDSIZE];
//
//					if (newV[i][j] != null) {
//
//						//System.out.println(newV[i][j].getClass().toString());
//
//					}
//
//				}
//			}
//
//		});

//		for (int y = 0, k = 0; y <= GRIDSIZE - 1; y++) {
//			for (int x = 0; x <= GRIDSIZE - 1; x++) {
//				ImageView iv = (ImageView) simulationGrid.getChildren().get(k); // TODO: parsing mistake
//
//				imageViewField[x][y] = (ImageView) iv;
//				k++;
//			}
//		}

		// TODO: hier den Listener aufs Grid, wenn ein neues Image ins Feld platziert
		// wird
		// grid.getCurren..

		grid.getGrid().addListener((observable, oldV, newV) -> {

			int x = 0, y = 0;
			for (Street[] newRow : newV) {
				for (Street newCol : newRow) {
					Street[] oldRow = oldV[x];
					Street oldCol = oldRow[y];
					if (newCol != oldCol) {

						break;
					}
					y++;
				}
				y = 0;
				x++;
			}

		});

	}

}