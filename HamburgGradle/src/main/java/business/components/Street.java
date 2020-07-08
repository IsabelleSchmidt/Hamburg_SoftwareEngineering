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

	private StreetType type;
	protected SimpleIntegerProperty rotationCount = new SimpleIntegerProperty();
	protected List<Direction> directions = new ArrayList<>();

	public void setType(StreetType type) {
		this.type = type;
	}

	public StreetType getType() {
		return type;
	}

	protected abstract void rotate();

	public SimpleIntegerProperty getRotationCount() {
		return rotationCount;
	}

	public void setRotationCount(SimpleIntegerProperty rotationCount) {
		this.rotationCount = rotationCount;
	}
}
