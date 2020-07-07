package business.components;

public class Curve extends Street {

	public Curve() {
		directions.add(Direction.DOWN);
		directions.add(Direction.RIGHT);
	}

	@Override
	public void rotate() {

		if (rotationCount < 3) {
			rotationCount++;
		} else {
			rotationCount = 0;
		}

		switch (rotationCount) {
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

}
