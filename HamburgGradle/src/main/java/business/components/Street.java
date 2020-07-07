package business.components;

import java.util.ArrayList;
import java.util.List;

public abstract class Street {

	// muss sich merken welche stra�e und wo es gesetzt wurde

	private StreetType type;
	protected int rotationCount = 0;
	protected List<Direction> directions = new ArrayList<>();

	public void setType(StreetType type) {
		this.type = type;
	}

	public StreetType getType() {
		return type;
	}

	protected abstract void rotate();
}
