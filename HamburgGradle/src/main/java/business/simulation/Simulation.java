package business.simulation;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Simulation {

	private static final int SPEEDFACTOR = 1;
	private SimpleDoubleProperty playbackSpeed;

	public Simulation() {
		playbackSpeed = new SimpleDoubleProperty();
	}

	public SimpleDoubleProperty getPlaybackSpeed() {
		return playbackSpeed;
	}

	public void increasePlaybackSpeed() {
		playbackSpeed.set(playbackSpeed.get() + SPEEDFACTOR);
	}

	public void decreasePlaybackSpeed() {
		
			playbackSpeed.set(playbackSpeed.get() - SPEEDFACTOR);
		

	}

}
