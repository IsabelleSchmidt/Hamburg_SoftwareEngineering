package business.components;

public class Curve extends Street {

	/**
	 * Hinzufügen der Richtungen, welche eine Street dieses Typen hat
	 */
	public Curve() {
		directions.add(Direction.DOWN);
		directions.add(Direction.RIGHT);
	}

	@Override
	public void rotate() {

		if (rotationCount.get() < 3) {
			rotationCount.set(rotationCount.get() + 1);
			;
		} else {
			rotationCount.set(0);
		}

		switch (rotationCount.get()) {
		case 0:
			directions.clear();
			directions.add(Direction.DOWN);
			directions.add(Direction.RIGHT);
			break;
		case 1:
			directions.clear();
			directions.add(Direction.LEFT);
			directions.add(Direction.DOWN);
			break;
		case 2:
			directions.clear();
			directions.add(Direction.UP);
			directions.add(Direction.LEFT);
			break;
		case 3:
			directions.clear();
			directions.add(Direction.UP);
			directions.add(Direction.RIGHT);
			break;

		default:
			break;
		}

	}

	@Override
	public String toString() {
		return "Curve";
	}

}
