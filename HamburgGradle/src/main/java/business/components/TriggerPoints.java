package business.components;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriggerPoints {

	private Point triggerWest = new Point(10, 67);
	private Point triggerNorth = new Point(33, 10);
	private Point triggerEast = new Point(90, 33);
	private Point triggerSouth = new Point(67, 90);

	private Point downRight = new Point(67, 67);
	private Point upRight = new Point(67, 33);
	private Point downLeft = new Point(33, 67);
	private Point upLeft = new Point(33, 33);

	protected List<Direction> directions = new ArrayList<>();

	public TriggerPoints() {

		directions.add(Direction.UP);
		directions.add(Direction.DOWN);
		directions.add(Direction.LEFT);
		directions.add(Direction.RIGHT);

	}

	public Direction chooseRandomDirection(Street street, Direction vehicleDirection) {

		Random random = new Random();
		List<Direction> directions = new ArrayList<Direction>();
		int index = 0;

		for (Direction dir : street.getDirections()) {
			directions.add(dir);
		}

		for (Direction d : directions) {
			if (tellOposite(vehicleDirection).equals(d)) {
				directions.remove(index);
				break;
			}

			index++;
		}

		return directions.get(random.nextInt(directions.size()));


	}

	public boolean isTriggered(Vehicle car, int x, int y) {

		Point currentPoint = new Point(x % 100, y % 100);

		if (currentPoint.equals(triggerNorth)) {
			return true;

		} else if (currentPoint.equals(triggerEast)) {
			return true;

		} else if (currentPoint.equals(triggerSouth)) {
			return true;

		} else if (currentPoint.equals(triggerWest)) {
			return true;

		}

		return false;
	}

	public Direction canTurnTo(Vehicle vehicle) {

		Direction direction = vehicle.getDirection();
		Point carPoint = new Point(vehicle.getXPosition() % 100, vehicle.getYPosition() % 100);

		switch (direction) {
		case UP:

			if (carPoint.equals(downRight)) {
				return Direction.RIGHT;
			}

			if (carPoint.equals(upRight)) {
				return Direction.LEFT;
			}

			return Direction.UP;

		case RIGHT:

			if (carPoint.equals(downLeft)) {
				return Direction.DOWN;
			}
			if (carPoint.equals(downRight)) {
				return Direction.UP;
			}
			return Direction.RIGHT;

		case DOWN:

			if (carPoint.equals(upLeft)) {
				return Direction.LEFT;
			}

			if (carPoint.equals(downLeft)) {
				return Direction.RIGHT;
			}
			return Direction.DOWN;

		case LEFT:

			if (carPoint.equals(upRight)) {
				return Direction.UP;
			}

			if (carPoint.equals(upLeft)) {
				return Direction.DOWN;
			}
			return Direction.LEFT;

		default:
			break;
		}

		return direction;

	}

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

}
