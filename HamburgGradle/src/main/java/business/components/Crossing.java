package business.components;

public class Crossing extends Street {

	public Crossing() {
		directions.add(Direction.LEFT);
		directions.add(Direction.RIGHT);
		directions.add(Direction.UP);
		directions.add(Direction.DOWN);
	}

	/**
	 * roate ist bei einer Kreuzung egal.
	 */
	@Override
	public void rotate() {

	}

}
