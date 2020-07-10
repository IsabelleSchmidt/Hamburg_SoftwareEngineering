package business.components;

public class Crossing extends Street {
	
	/**
	 * Hinzufügen der Richtungen, welche eine Street dieses Typen hat
	 */
	public Crossing() {
		directions.add(Direction.LEFT);
		directions.add(Direction.RIGHT);
		directions.add(Direction.UP);
		directions.add(Direction.DOWN);
	}

	@Override
	public void rotate() {

	}

	@Override
	public String toString() {
		return "Crossing";
	}
}
