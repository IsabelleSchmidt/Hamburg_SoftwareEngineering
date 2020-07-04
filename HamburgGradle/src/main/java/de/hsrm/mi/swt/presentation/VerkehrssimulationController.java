package de.hsrm.mi.swt.presentation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class VerkehrssimulationController implements Initializable{
	
	double mainSceneX, mainSceneY;
   
	@FXML
    private AnchorPane simulationPane;

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
    
    
   public void scrollToMenu(){
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
   
   public void moveStreet(ImageView imgView) {
	   imgView.setCursor(Cursor.HAND);
		imgView.setOnMousePressed((t) -> {
			mainSceneX = t.getSceneX();
			mainSceneY = t.getSceneY();
		});
		imgView.setOnMouseDragged((t) -> {
			double offsetX = t.getSceneX() - mainSceneX;
			double offsetY = t.getSceneY() - mainSceneY;

			ImageView r = (ImageView) (t.getSource());

			r.setX(r.getX() + offsetX);
			r.setY(r.getY() + offsetY);

			mainSceneX = t.getSceneX();
			mainSceneY = t.getSceneY();
		});
   }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		StreetElementStraight.setCursor(Cursor.HAND);
		StreetElementStraight.setOnMousePressed((t) -> {
			mainSceneX = t.getSceneX();
			mainSceneY = t.getSceneY();
		});
		StreetElementStraight.setOnMouseDragged((t) -> {
			double offsetX = t.getSceneX() - mainSceneX;
			double offsetY = t.getSceneY() - mainSceneY;

			ImageView r = (ImageView) (t.getSource());

			r.setX(r.getX() + offsetX);
			r.setY(r.getY() + offsetY);

			mainSceneX = t.getSceneX();
			mainSceneY = t.getSceneY();
			
		});
		
		
	}

}
