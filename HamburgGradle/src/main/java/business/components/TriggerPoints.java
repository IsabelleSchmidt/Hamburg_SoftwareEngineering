package business.components;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TriggerPoints {

	// TRIGGERWEST, TRIGGERNORTH, TRIGGEREAST, TRIGGERSOUTH, TURNWEST, TURNNORTH,
	// TURNEAST, TURNSOUTH

	// private int[3][3] TRIGGERWEST {{10,67},{33,67},{67,67}};

	Point triggerWest = new Point(10, 67);
	Point triggerNorth = new Point(33, 10);
	Point triggerEast = new Point(90, 33);
	Point triggerSouth = new Point(67, 90);

	int[][] turnsWest = { { 33, 67 }, { 67, 67 } };
	int[][] turnsNorth = { { 33, 33 }, { 33, 67 } };
	int[][] turnsEast = { { 67, 33 }, { 33, 33 } };
	int[][] turnsSouth = { { 67, 67 }, { 67, 33 } };

	protected List<Direction> directions = new ArrayList<>();

	public TriggerPoints() {

		directions.add(Direction.UP);
		directions.add(Direction.DOWN);
		directions.add(Direction.LEFT);
		directions.add(Direction.RIGHT);

	}

	public Direction chooseRandomDirection(Street street, Direction vehicleDirection, Vehicle vehicle) {

		Random random = new Random();

		List<Direction> directions = new ArrayList<Direction>();
		for (Direction dir : street.getDirections()) {
			directions.add(dir);
		}
		int count = 0;
		for (Direction dir : directions) {
			System.out.println(tellOposite(vehicleDirection));
			System.out.println(vehicle.getDirection());
			if (tellOposite(vehicleDirection).equals(dir)) {
				directions.remove(count);
				break;
			}
			count++;
		}
		System.out.println(directions.get(random.nextInt(directions.size())));
		return directions.get(random.nextInt(directions.size()));

//		if (street.toString().contains("Crossing")) {
//
//			do {
//				Collections.shuffle(directions);
//			} while (directions.get(0) == tellOposite(vehicleDirection));
//
//			vehicle.setNextDirection(directions.get(0));
//
//		} else if (street.toString().contains("Junction")) {
//
//			do {
//				Collections.shuffle(street.getDirections());
//			} while (tellOposite(vehicleDirection).equals(directions.get(0))
//					&& directions.get(0).equals(vehicleDirection));
//
//			vehicle.setNextDirection(directions.get(0));
//
//		} else if (street.toString().contains("Straight")) {
//
//			// nichts, einfach weitermachen
//
//		} else if (street.toString().contains("Curve")) {
//
//			if (vehicleDirection.equals(Direction.DOWN) || vehicleDirection.equals(Direction.UP)) {
//				do {
//					Collections.shuffle(directions);
//				} while (directions.get(0).equals(Direction.DOWN) || directions.get(0).equals(Direction.UP));
//				vehicle.setNextDirection(directions.get(0));
//
//			} else if (vehicleDirection.equals(Direction.LEFT) || vehicleDirection.equals(Direction.RIGHT)) {
//				do {
//					Collections.shuffle(directions);
//				} while (directions.get(0).equals(Direction.LEFT) || directions.get(0).equals(Direction.RIGHT));
//				vehicle.setNextDirection(directions.get(0));
//			}
//
//		}

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
		Direction nextDirection = vehicle.getNextDirection();
		// Point point = getFirstTunPoint(direction);
		Point carPoint = new Point(vehicle.getxPostionInt() % 100, vehicle.getyPositionInt() % 100);

		Point downRight = new Point(67, 67);
		Point upRight = new Point(67, 33);
		Point downLeft = new Point(33, 67);
		Point upLeft = new Point(33, 33);

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
