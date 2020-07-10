package business.components;

public class Straight extends Street {

	/**
	 * Hinzufügen der Richtungen, welche eine Street dieses Typen hat
	 */
	public Straight() {
		directions.add(Direction.DOWN);
		directions.add(Direction.UP);
	}

	@Override
	public void rotate() {

		if (rotationCount.get() < 3) {
			rotationCount.set(rotationCount.get() + 1);
		} else {
			rotationCount.set(0);
			;
		}

		switch (rotationCount.get()) {
		case 0:
			directions.clear();
			directions.add(Direction.DOWN);
			directions.add(Direction.UP);
			break;
		case 1:
			directions.clear();
			directions.add(Direction.RIGHT);
			directions.add(Direction.LEFT);
			break;
		case 2:
			directions.clear();
			directions.add(Direction.DOWN);
			directions.add(Direction.UP);
			break;
		case 3:
			directions.clear();
			directions.add(Direction.RIGHT);
			directions.add(Direction.LEFT);
			break;

		default:
			break;
		}

	}

	@Override
	public String toString() {
		return "Straight";
	}

}
