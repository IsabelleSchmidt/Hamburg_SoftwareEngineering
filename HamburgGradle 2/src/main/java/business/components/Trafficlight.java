package business.components;

import javafx.beans.property.SimpleObjectProperty;

public class Trafficlight {

	private SimpleObjectProperty<TrafficlightStatus> status;
	private Direction direction;

	public Trafficlight(Direction d) {
		status = new SimpleObjectProperty<TrafficlightStatus>(TrafficlightStatus.RED);
		direction = d;
	}

	public TrafficlightStatus switchLight() {

		switch (status.get()) {

		case RED:
			status.set(TrafficlightStatus.ORANGEGREEN);
			return status.get();

		case ORANGEGREEN:
			status.set(TrafficlightStatus.GREEN);
			return status.get();

		case GREEN:
			status.set(TrafficlightStatus.ORANGE);
			return status.get();

		case ORANGE:
			status.set(TrafficlightStatus.RED);
			return status.get();

		default:
			break;
		}

		return status.get();

	}

	public TrafficlightStatus reset() {
		status.set(TrafficlightStatus.RED);
		return status.get();
	}

	public TrafficlightStatus getTrafficlightStatus() {
		return status.get();
	}

	public SimpleObjectProperty<TrafficlightStatus> getStatus() {
		return status;
	}

	public Direction getDirection() {
		return direction;
	}

}
