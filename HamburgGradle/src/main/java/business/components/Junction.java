package business.components;

public class Junction extends Street {

	public Junction() {

		directions.add(Direction.LEFT);
		directions.add(Direction.RIGHT);
		directions.add(Direction.DOWN);
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
			directions.add(Direction.LEFT);
			break;
		case 1:
			directions.clear();
			directions.add(Direction.DOWN);
			directions.add(Direction.LEFT);
			directions.add(Direction.UP);
			break;
		case 2:
			directions.clear();
			directions.add(Direction.RIGHT);
			directions.add(Direction.LEFT);
			directions.add(Direction.UP);
			break;
		case 3:
			directions.clear();
			directions.add(Direction.DOWN);
			directions.add(Direction.RIGHT);
			directions.add(Direction.UP);
			break;

		default:
			break;
		}

	}

}
