package business.components;

import business.simulation.Grid;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Vehicle {

	private Direction direction = Direction.RIGHT;
	private Direction nextDirection = null;
	private SimpleIntegerProperty xPosition, yPosition;
	private SimpleObjectProperty<Direction> directionProperty;
	private Grid grid;
	private SimpleIntegerProperty rotationCount;
	private final int XRIGHTSTART = 90;
	private final int YRIGHTSTART = 67;
	private final int XLEFTSTART = 10;
	private final int YLEFTSTART = 33;
	private final int XUPSTART = 67;
	private final int YUPSTART = 10;
	private final int XDOWNSTART = 33;
	private final int YDOWNSTART = 90;

	/**
	 * 
	 * @param x    - Startkoordidate auf einer Street
	 * @param y    - Startkoordianate auf einer Street
	 * @param grid - Grid mit allen Straßen
	 */
	public Vehicle(int x, int y, Grid grid) {

		rotationCount = new SimpleIntegerProperty();
		rotationCount.set(0);

		xPosition = new SimpleIntegerProperty(x + XRIGHTSTART);
		yPosition = new SimpleIntegerProperty(y + YRIGHTSTART);

		directionProperty = new SimpleObjectProperty<>(Direction.RIGHT);

		this.grid = grid;

	}

	public void rotate() {

		if (rotationCount.get() < 3) {
			rotationCount.set(rotationCount.get() + 1);

		} else {
			rotationCount.set(0);
		}

		switch (rotationCount.get()) {
		case 0:
			direction = Direction.RIGHT;
			xPosition.set((xPosition.get() - xPosition.get() % 100) + XRIGHTSTART);
			yPosition.set((yPosition.get() - yPosition.get() % 100) + YRIGHTSTART);
			break;
		case 1:
			direction = Direction.DOWN;
			xPosition.set((xPosition.get() - xPosition.get() % 100) + XDOWNSTART);
			yPosition.set((yPosition.get() - yPosition.get() % 100) + YDOWNSTART);

			break;
		case 2:
			direction = Direction.LEFT;
			xPosition.set((xPosition.get() - xPosition.get() % 100) + XLEFTSTART);
			yPosition.set((yPosition.get() - yPosition.get() % 100) + YLEFTSTART);
			break;
		case 3:
			direction = Direction.UP;
			xPosition.set((xPosition.get() - xPosition.get() % 100) + XUPSTART);
			yPosition.set((yPosition.get() - yPosition.get() % 100) + YUPSTART);
			break;

		default:
			break;
		}
	}

	public void drive() {
		switch (direction) {
		case UP:
			yPosition.set(yPosition.get() - 1);
			break;
		case DOWN:
			yPosition.set(yPosition.get() + 1);
			break;
		case LEFT:
			xPosition.set(xPosition.get() - 1);
			break;
		case RIGHT:
			xPosition.set(xPosition.get() + 1);
			break;
		default:
			break;
		}
	}
	
/**
 * 
 * @param streetDirection - bekommt eine Direction übergeben
 * @return - gibt die Gegenüberliegende Direction zurück
 */
	public Direction tellOposite(Direction streetDirection) {

		if (streetDirection.equals(Direction.UP)) {
			return Direction.DOWN;
		}

		if (streetDirection.equals(Direction.DOWN)) {
			return Direction.UP;
		}

		if (streetDirection.equals(Direction.LEFT)) {
			return Direction.RIGHT;
		}

		if (streetDirection.equals(Direction.RIGHT)) {
			return Direction.LEFT;
		}

		return null;

	}

	/**
	 * 
	 * @return - gibt die aktuelle X Position des Fahrzeugs als property
	 */
	public SimpleIntegerProperty getXCarProperty() {
		return xPosition;
	}

	/**
	 * 
	 * @return yPosition - gibt die aktuelle Y Position als property
	 */
	public SimpleIntegerProperty getYCarProperty() {
		return yPosition;
	}

	/**
	 * 
	 * @return - gibt die nächste Richtung
	 */
	public Direction getNextDirection() {
		return nextDirection;
	}

	/**
	 * 
	 * @param nextDirection - um die nächste direction zu setzen
	 */
	public void setNextDirection(Direction nextDirection) {
		this.nextDirection = nextDirection;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * 
	 * @return - gibt Direction zurück
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * 
	 * @return xPosition - gibt die aktuelle xPosition zurück
	 */
	public int getXPosition() {
		return xPosition.get();
	}

	/**
	 * 
	 * @return - gibt yPosition zurück
	 */
	public int getYPosition() {
		return yPosition.get();
	}

	/**
	 * 
	 * @return - gibt die Direction als property zurück
	 */
	public SimpleObjectProperty<Direction> getDirectionProperty() {
		return directionProperty;
	}

	/**
	 * 
	 * @param directionProperty - property zum setzen einer Direction
	 */
	public void setDirectionProperty(SimpleObjectProperty<Direction> directionProperty) {
		this.directionProperty = directionProperty;
	}

	
	/**
	 * 
	 * @return gibt die Anzahl an Street Rotationen zurück
	 */
	public SimpleIntegerProperty getRotationCount() {
		return rotationCount;
	}

}
