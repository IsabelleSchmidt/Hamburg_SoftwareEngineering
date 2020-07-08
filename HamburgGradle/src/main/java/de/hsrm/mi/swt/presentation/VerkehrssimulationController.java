package de.hsrm.mi.swt.presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import business.components.Crossing;
import business.components.Curve;
import business.components.Junction;
import business.components.Straight;
import business.components.Street;
import business.components.Vehicle;
import business.simulation.Grid;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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
	private ImageView[][] imageViewField;
	private FileInputStream inputstream;
	private HashMap<Vehicle, ImageView> vehicles = new HashMap<>();

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

			vehicleImgV.setX(v.getxPostion().get() - vehicleImgV.getFitHeight() / 2);
			vehicleImgV.setY(v.getyPosition().get() - vehicleImgV.getFitWidth() / 2);
			vehicles.put(v, vehicleImgV);
			initVehicleListener(v);

			break;

		default:
			break;
		}
		System.out.println(id);
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

		v.getxPostion().addListener((observable, oldValue, newV) -> {
			ImageView imageV = vehicles.get(v);
			imageV.setX(v.getxPostionInt() - imageV.getFitWidth() / 2);
		});

		v.getyPosition().addListener((observable, oldValue, newV) -> {
			ImageView imageV = vehicles.get(v);
			imageV.setY(v.getyPositionInt() - imageV.getFitHeight() / 2);

		});

	}

	private void initStreetListener(Street street) {

		street.getRotationCount().addListener((observable, oldValue, newV) -> {
			System.out.println(newV);

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
		for (Vehicle v : vehicles.keySet()) {

			v.drive();

		}
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

		grid.getGrid().addListener((observable, oldValue, newV) -> {

			for (int i = 0; i < GRIDSIZE; i++) {
				for (int j = 0; j < GRIDSIZE; j++) {

					// String[][] streets = new String[GRIDSIZE][GRIDSIZE];

					if (newV[i][j] != null) {

						System.out.println(newV[i][j].getClass().toString());

					}

				}
			}

		});

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