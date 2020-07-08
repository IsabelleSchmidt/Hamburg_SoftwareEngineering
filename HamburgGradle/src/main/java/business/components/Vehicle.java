package business.components;

import business.simulation.Grid;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Vehicle {

	private Color color;
	private Direction direction = Direction.RIGHT;
	private SimpleIntegerProperty xPosition, yPosition;
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

	public Vehicle(int x, int y, Grid grid) {

		rotationCount = new SimpleIntegerProperty();
		rotationCount.set(0);

		xPosition = new SimpleIntegerProperty();
		yPosition = new SimpleIntegerProperty();
		xPosition.set(x + XRIGHTSTART);
		yPosition.set(y + YRIGHTSTART);
		this.grid = grid;

	}
	
	//TODO: sauber bennen

	public SimpleIntegerProperty getxPostion() {
		return xPosition;
	}

	public SimpleIntegerProperty getyPosition() {
		return yPosition;
	}

	public int getxPostionInt() {
		return xPosition.get();
	}

	public int getyPositionInt() {
		return yPosition.get();
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
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
