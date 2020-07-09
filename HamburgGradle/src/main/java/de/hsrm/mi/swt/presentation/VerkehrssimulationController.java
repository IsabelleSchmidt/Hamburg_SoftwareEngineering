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
import javafx.beans.property.SimpleObjectProperty;
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
	private FileInputStream inputstream;
	private HashMap<Vehicle, ImageView> vehicles = new HashMap<>();
	private Timeline timelineTrafficLights, timelineVehicle;
	private int playbackspeed = 5;

	// TODO: Label in der GUI oben rechts füllen
	// 1. mit Anzahl an Fahrzeugen auf GUI
	// 2. mit Zeit, seit dem die Animation gestartet wurde

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
	private GridPane simulationGridPane;

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
		try {
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

		} catch (Exception e) {
			System.out.println("Wohl abgerutscht beim platzieren - halb so wild. Einfach weitermachen.");
		}

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

		Integer cIndex = GridPane.getColumnIndex(node);
		Integer rIndex = GridPane.getRowIndex(node);
		int x = cIndex == null ? 0 : cIndex;
		int y = rIndex == null ? 0 : rIndex;

		Image img = event.getDragboard().getImage();
		String id = event.getDragboard().getString();

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

				if (d.equals(Direction.UP) || d.equals(Direction.DOWN)) {
					trafficlight.switchLight();
					trafficlight.switchLight();
				}
				trafficlights.add(trafficlight);
			}

			grid.getStreet(x, y).addTrafficlights(trafficlights);

			Street street = grid.getStreet(x, y);

			if (street.toString().contains("Straight")) {

				ImageView image = new ImageView("/gerade_rot.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
				image.setRotate(90 * grid.getStreet(x, y).getRotationCount().doubleValue());
				simulationGridPane.add(image, x, y);

			} else if (street.toString().contains("Crossing")) {

				ImageView image = new ImageView("/kreuzung_situation1.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
				image.setRotate(90 * grid.getStreet(x, y).getRotationCount().doubleValue());
				simulationGridPane.add(image, x, y);

			} else if (street.toString().contains("Junction")) {

				ImageView image = new ImageView("/abzweigung_ampel_2_gruen.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
				image.setRotate(90 * grid.getStreet(x, y).getRotationCount().doubleValue());
				simulationGridPane.add(image, x, y);

			} else if (street.toString().contains("Curve")) {

				ImageView image = new ImageView("/gerade_rot.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
				image.setRotate(90 * grid.getStreet(x, y).getRotationCount().doubleValue());
				simulationGridPane.add(image, x, y);

			}

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

		if (!id.contains(("TrafficLight"))) {
			if (!id.equals("StreetElementCar")) {
				((ImageView) event.getPickResult().getIntersectedNode()).setImage(img);
			}

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

	@FXML
	void onMouseClickedGrid(MouseEvent event) {

		ImageView img = (ImageView) event.getPickResult().getIntersectedNode();

		System.out.println(img);

		Integer cIndex = GridPane.getColumnIndex(img);
		Integer rIndex = GridPane.getRowIndex(img);
		int x = cIndex == null ? 0 : cIndex;
		int y = rIndex == null ? 0 : rIndex;

		if (event.getButton() == MouseButton.PRIMARY) {

			Street street = grid.getStreet(x, y);

			if (street.getClass().toString().contains("Straight")) {
				((Straight) street).rotate();
			} else if (street.getClass().toString().contains("Curve")) {
				((Curve) street).rotate();
			} else if (street.getClass().toString().contains("Junction")) {
				((Junction) street).rotate();
			} else if (street.getClass().toString().contains("Crossing")) {
				((Crossing) street).rotate();
			}

			img.setRotate(90 * street.getRotationCount().get());
		}

		if (event.getButton() == MouseButton.SECONDARY) {

			grid.removeItem(x, y);
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
		timelineVehicle.stop();
		timelineTrafficLights.stop();
	}

	@FXML
	void increaseSpeed(ActionEvent event) {
		playbackspeed++;
		timelineVehicle.stop();
		timelineVehicle.setRate(playbackspeed);
		timelineVehicle.play();
		timelineTrafficLights.stop();
		timelineTrafficLights.setRate(playbackspeed);
		timelineTrafficLights.play();
	}

	@FXML
	void decreaseSpeed(ActionEvent event) {

		if (playbackspeed > 1) {
			playbackspeed--;
			timelineVehicle.stop();
			timelineVehicle.setRate(playbackspeed);
			timelineVehicle.play();
			timelineTrafficLights.stop();
			timelineTrafficLights.setRate(playbackspeed);
			timelineTrafficLights.play();
		}

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
		// TODO: RAUSWERFEN
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
		// RAUSWERFEN
	}

	@FXML
	void safeSimulation(ActionEvent event) {
		VerkehrssimulationMain.save();
	}

	@FXML
	void showProcets(ActionEvent event) {

	}

	private Node getNodeFromGridPane(AnchorPane imageGrid, int col, int row) {
		for (Node node : imageGrid.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return (ImageView) node;
			}
		}
		return null;
	}

	@FXML
	void startSimulation(ActionEvent event) {

		TriggerPoints trigger = new TriggerPoints();

		Duration durationVehilce = Duration.millis(50);
		KeyFrame vehicleFrame = new KeyFrame(durationVehilce, ev -> {
			for (Vehicle car : vehicles.keySet()) {
				boolean drive = true;

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
		});

		timelineVehicle = new Timeline();
		timelineVehicle.getKeyFrames().add(vehicleFrame);
		timelineVehicle.setCycleCount(Timeline.INDEFINITE);
		timelineVehicle.setRate(playbackspeed);

		timelineVehicle.play();

		Duration durationTrafficLight = Duration.millis(3000);
		KeyFrame trafficLightFrame = new KeyFrame(durationTrafficLight, e -> {

			ImageView newStreetImg = null;

			for (int x = 0; x < GRIDSIZE; x++) {
				for (int y = 0; y < GRIDSIZE; y++) {
					if (grid.getStreet(x, y) != null) {
						for (Trafficlight t : grid.getStreet(x, y).getTrafficlights()) {

							t.switchLight();

							Street street = grid.getStreet(x, y);

							if (t.getTrafficlightStatus().equals(TrafficlightStatus.GREEN)) {
								newStreetImg = t.loadImage(street, t.getTrafficlightStatus());
								newStreetImg.setRotate(90 * grid.getStreet(x, y).getRotationCount().doubleValue());
								simulationGridPane.add(newStreetImg, x, y);
							}

							if (t.getTrafficlightStatus().equals(TrafficlightStatus.ORANGE)) {
								newStreetImg = t.loadImage(street, t.getTrafficlightStatus());
								newStreetImg.setRotate(90 * grid.getStreet(x, y).getRotationCount().doubleValue());
								simulationGridPane.add(newStreetImg, x, y);
							}

							if (t.getTrafficlightStatus().equals(TrafficlightStatus.ORANGEGREEN)) {
								newStreetImg = t.loadImage(street, t.getTrafficlightStatus());
								newStreetImg.setRotate(90 * grid.getStreet(x, y).getRotationCount().doubleValue());
								simulationGridPane.add(newStreetImg, x, y);
							}

							if (t.getTrafficlightStatus().equals(TrafficlightStatus.RED)) {
								newStreetImg = t.loadImage(street, t.getTrafficlightStatus());
								newStreetImg.setRotate(90 * grid.getStreet(x, y).getRotationCount().doubleValue());
								simulationGridPane.add(newStreetImg, x, y);
							}
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

//		grid.getGrid().addListener((observable, oldV, newV) -> {
//
//			int x = 0, y = 0;
//			for (Street[] newRow : newV) {
//				for (Street newCol : newRow) {
//					Street[] oldRow = oldV[x];
//					Street oldCol = oldRow[y];
//					if (newCol != oldCol) {
//
//						break;
//					}
//					y++;
//				}
//				y = 0;
//				x++;
//			}
//
//		});

	}

}