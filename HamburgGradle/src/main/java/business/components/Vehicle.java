package business.components;

import business.simulation.Grid;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Vehicle {

	private Color color;
	private Direction direction;
	private SimpleIntegerProperty xPosition, yPosition;
	private Grid grid;
	private int rotationCount;

	public Vehicle(int x, int y, Grid grid) {

		xPosition = new SimpleIntegerProperty();
		yPosition = new SimpleIntegerProperty();
		xPosition.set(x);
		yPosition.set(y);
		this.grid = grid;

	}

	public SimpleIntegerProperty getxPostion() {
		return xPosition;
	}

	public SimpleIntegerProperty getyPosition() {
		return yPosition;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	protected void rotate() {

		if (rotationCount < 3) {
			rotationCount++;
		} else {
			rotationCount = 0;
		}

		switch (rotationCount) {
		case 0:
			direction = Direction.RIGHT;
			break;
		case 1:
			direction = Direction.DOWN;
			break;
		case 2:
			direction = Direction.LEFT;
			break;
		case 3:
			direction = Direction.LEFT;
			break;

		default:
			break;
		}

	}

	public Color getColor() {
		return color;
	}

	public Direction getDirection() {
		return direction;
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

}
