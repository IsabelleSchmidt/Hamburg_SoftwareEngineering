package business.components;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;

/**
 * 
 * Street
 *
 */
public abstract class Street {

	protected SimpleIntegerProperty rotationCount = new SimpleIntegerProperty();
	protected List<Direction> directions = new ArrayList<>();
	protected List<Trafficlight> trafficlights = new ArrayList<>();


	public SimpleIntegerProperty getRotationCount() {
		return rotationCount;
	}

	public void setRotationCount(SimpleIntegerProperty rotationCount) {
		this.rotationCount = rotationCount;
	}

	public List<Direction> getDirections() {
		return directions;
	}

	public void addTrafficlights(List<Trafficlight> trafficlights) {

		this.trafficlights = trafficlights;
	}

	public List<Trafficlight> getTrafficlights() {
		return trafficlights;
	}

	protected abstract void rotate();

}
