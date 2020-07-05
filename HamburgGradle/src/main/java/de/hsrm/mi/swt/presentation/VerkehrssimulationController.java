package de.hsrm.mi.swt.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.ui.Clipboard;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class VerkehrssimulationController implements Initializable {

	MenuItem rightClickMenu;
	ContextMenu contextMenu;
	int sum;

	@FXML
	private AnchorPane loadSimulationPane;

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
	private Button toolbarButtonRight;

	@FXML
	private Button toolbarButtonLeft;

	@FXML
	private Button toolbarButtonDown;

	@FXML
	private Button toolbarButtonRotateLeft;

	@FXML
	private Button toolbarButtonRotateRight;

	@FXML
	private Button toolbarButtonUp;

	@FXML
	private Button toolbarButtonDelete;

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

		// ImageView img = (ImageView) event.getTarget();

		// Data dropped
		// If there is an image on the dragboard, read it and use it
		// Dragboard db = event.getDragboard();

		// target.setText(db.getImage()); --- must be changed to target.add(source, col,
		// row)
		// target.add(source, 5, 5, 1, 1);
		// Places at 0,0 - will need to take coordinates once that is implemented
//			ImageView image = new ImageView(db.getImage());

		// TODO: set image size; use correct column/row span
//			Board.add(image, x, y, 1, 1);
//			success = true;

		// let the source know whether the image was successfully transferred and used

		event.consume();

	}

	@FXML
	void handleDragDetection(MouseEvent event) {

		ImageView picked = (ImageView) event.getPickResult().getIntersectedNode();

		Dragboard db = picked.startDragAndDrop(TransferMode.ANY);
		ClipboardContent cb = new ClipboardContent();

		cb.putImage(picked.getImage());
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
	void onMouseClickedGrid(MouseEvent event) {

		Node node = event.getPickResult().getIntersectedNode();

		if (event.getButton() == MouseButton.PRIMARY) {
			int counter = event.getClickCount(); //TODO: Clicks müssen schnell hintereinander erfolgen, sonst keine Roation
			node.setRotate(90 * counter); 
			counter = 0;
		}

		if (event.getButton() == MouseButton.SECONDARY) {
			ImageView img = (ImageView) node;
			img.setImage(null);
//			contextMenu.getItems().addAll(rightClickMenu);
//			contextMenu.show(node, event.getScreenX(), event.getScreenY());

		}

		Integer cIndex = GridPane.getColumnIndex(node);
		Integer rIndex = GridPane.getRowIndex(node);
		int x = cIndex == null ? 0 : cIndex;
		int y = rIndex == null ? 0 : rIndex;

		// System.out.println(x + " " + y);

	}

	@FXML
	void handleImageDrop(DragEvent event) {

		Node node = event.getPickResult().getIntersectedNode();

		Integer cIndex = GridPane.getColumnIndex(node);
		Integer rIndex = GridPane.getRowIndex(node);
		int x = cIndex == null ? 0 : cIndex;
		int y = rIndex == null ? 0 : rIndex;

		System.out.println(x + " " + y);

		Image img = event.getDragboard().getImage();

		String buildImageString = "ImageGrid_" + x + "_" + y;

		System.out.println(buildImageString);

		// TODO: Bin schon müde und komme nicht mehr drauf, switch case ist kacke.
		// Vorallem wenn es mehr Felder werden sollen. Man muss die ImageView irgendwie
		// bauen können

		switch (buildImageString) {
		case "ImageGrid_0_0":
			ImageGrid_0_0.setImage(img);
			break;
		case "ImageGrid_0_1":
			ImageGrid_0_1.setImage(img);
			break;
		case "ImageGrid_0_2":
			ImageGrid_0_2.setImage(img);
			break;
		case "ImageGrid_0_3":
			ImageGrid_0_3.setImage(img);
			break;
		case "ImageGrid_0_4":
			ImageGrid_0_4.setImage(img);
			break;
		case "ImageGrid_1_0":
			ImageGrid_1_0.setImage(img);
			break;
		case "ImageGrid_1_1":
			ImageGrid_1_1.setImage(img);
			break;
		case "ImageGrid_1_2":
			ImageGrid_1_2.setImage(img);
			break;
		case "ImageGrid_1_3":
			ImageGrid_1_3.setImage(img);
			break;
		case "ImageGrid_1_4":
			ImageGrid_1_4.setImage(img);
			break;
		case "ImageGrid_2_0":
			ImageGrid_2_0.setImage(img);
			break;
		case "ImageGrid_2_1":
			ImageGrid_2_1.setImage(img);
			break;
		case "ImageGrid_2_2":
			ImageGrid_2_2.setImage(img);
			break;
		case "ImageGrid_2_3":
			ImageGrid_2_3.setImage(img);
			break;
		case "ImageGrid_2_4":
			ImageGrid_2_4.setImage(img);
			break;
		case "ImageGrid_3_0":
			ImageGrid_3_0.setImage(img);
			break;
		case "ImageGrid_3_1":
			ImageGrid_3_1.setImage(img);
			break;
		case "ImageGrid_3_2":
			ImageGrid_3_2.setImage(img);
			break;
		case "ImageGrid_3_3":
			ImageGrid_3_3.setImage(img);
			break;
		case "ImageGrid_3_4":
			ImageGrid_3_4.setImage(img);
			break;
		case "ImageGrid_4_0":
			ImageGrid_4_0.setImage(img);
			break;
		case "ImageGrid_4_1":
			ImageGrid_4_1.setImage(img);
			break;
		case "ImageGrid_4_2":
			ImageGrid_4_2.setImage(img);
			break;
		case "ImageGrid_4_3":
			ImageGrid_4_3.setImage(img);
			break;
		case "ImageGrid_4_4":
			ImageGrid_4_4.setImage(img);
			break;

		default:
			break;
		}

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
	void loadSimulation(ActionEvent event) {
		scrollToloadSimulation();
	}

	@FXML
	void newSimulation(ActionEvent event) {
		scrollToSimulation();
	}

	@FXML
	void pauseSimulation(ActionEvent event) {

	}

	@FXML
	void placeDown(ActionEvent event) {

	}

	@FXML
	void placeLeft(ActionEvent event) {

	}

	@FXML
	void placeRight(ActionEvent event) {

	}

	@FXML
	void placeUp(ActionEvent event) {

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
	void rotateLeft(ActionEvent event) {

	}

	@FXML
	void rotateRight(ActionEvent event) {

	}

	@FXML
	void safeSimulation(ActionEvent event) {

	}

	@FXML
	void showProcets(ActionEvent event) {

	}

	@FXML
	void startSimulation(ActionEvent event) {

	}

	public void scrollToMenu() {
		TranslateTransition tr1 = new TranslateTransition();
		tr1.setDuration(Duration.millis(300));
		tr1.setToX(0);
		tr1.setToY(-700);
		tr1.setNode(loadSimulationPane);
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
		ParallelTransition pt = new ParallelTransition(tr1, tr2, tr3);
		pt.play();
	}

	public void scrollToloadSimulation() {
		TranslateTransition tr1 = new TranslateTransition();
		tr1.setDuration(Duration.millis(300));
		tr1.setToX(0);
		tr1.setToY(-700);
		tr1.setNode(menuPane);
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
		tr2.setNode(loadSimulationPane);
		ParallelTransition pt = new ParallelTransition(tr1, tr2, tr3);
		pt.play();
	}

	public void scrollToSimulation() {
		TranslateTransition tr1 = new TranslateTransition();
		tr1.setDuration(Duration.millis(300));
		tr1.setToX(0);
		tr1.setToY(-700);
		tr1.setNode(menuPane);
		TranslateTransition tr3 = new TranslateTransition();
		tr3.setDuration(Duration.millis(300));
		tr3.setToX(0);
		tr3.setToY(-700);
		tr3.setNode(loadSimulationPane);
		TranslateTransition tr2 = new TranslateTransition();
		tr2.setDuration(Duration.millis(300));
		tr2.setFromX(0);
		tr2.setFromY(700);
		tr2.setToX(0);
		tr2.setToY(0);
		tr2.setNode(simulationPane);
		ParallelTransition pt = new ParallelTransition(tr1, tr2, tr3);
		pt.play();
	}

	public VerkehrssimulationController() {
		rightClickMenu = new MenuItem("Element löschen");
		contextMenu = new ContextMenu();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		

//		rightClickMenu.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//
//				// Node node = event.getPickResult().getIntersectedNode();
//				ImageView node = (ImageView) event.getTarget();
//				node.setImage(null);
//
//			}
//		});

	}

}
