package business.components;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;


public abstract class Street {

	protected SimpleIntegerProperty rotationCount = new SimpleIntegerProperty();
	protected List<Direction> directions = new ArrayList<>();
	protected List<Trafficlight> trafficlights = new ArrayList<>();

/**
 * 
 * @return rotationCount - gibt die Anzahl an Roationen des Elements als Property zurück
 */
	public SimpleIntegerProperty getRotationCount() {
		return rotationCount;
	}

	/**
	 * 
	 * @param rotationCount - zum setzen des rotationcount als Property
	 */
	public void setRotationCount(SimpleIntegerProperty rotationCount) {
		this.rotationCount = rotationCount;
	}

	/**
	 * 
	 * @return directions - gibt eine Liste von Direction zurück
	 */
	public List<Direction> getDirections() {
		return directions;
	}

	/**
	 * 
	 * @param trafficlights - zum Hinzufügen von Trafficlight zu einer Street als Liste
	 */
	public void addTrafficlights(List<Trafficlight> trafficlights) {

		this.trafficlights = trafficlights;
	}

	/**
	 * 
	 * @return trafficlights - gibt eine Liste von Trafficlight zurück
	 */
	public List<Trafficlight> getTrafficlights() {
		return trafficlights;
	}

	/**
	 * abstract roatate Methode zum überschreiben in den erbenden Klassen
	 */
	protected abstract void rotate();

}
