package business.components;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;

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

	public ImageView loadImage(Street street, TrafficlightStatus trafficlightStatus) {

		ImageView image = null;

		if (trafficlightStatus.equals(TrafficlightStatus.GREEN)) {

			if (street.toString().contains(("Straight"))) {
				image = new ImageView("/gerade_gruen.png");
				image.setFitHeight(100);
				image.setFitWidth(100);

			}

			if (street.toString().contains(("Crossing"))) {
				image = new ImageView("/kreuzung_situation1.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
			}

			if (street.toString().contains(("Junction"))) {
				image = new ImageView("/abzweigung_ampel_gruen.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
			}

			if (street.toString().contains(("Curve"))) {
				image = new ImageView("/kurve_gruen.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
			}
		}

		if (trafficlightStatus.equals(TrafficlightStatus.RED)) {

			if (street.toString().contains(("Straight"))) {
				image = new ImageView("/gerade_rot.png");
				image.setFitHeight(100);
				image.setFitWidth(100);

			}

			if (street.toString().contains(("Crossing"))) {
				image = new ImageView("/kreuzung_situation2.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
			}

			if (street.toString().contains(("Junction"))) {
				image = new ImageView("/abzweigung_ampel_2_gruen.png");
				
				image.setFitHeight(100);
				image.setFitWidth(100);
			}

			if (street.toString().contains(("Curve"))) {
				image = new ImageView("/kurve_rot.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
			}
		}

		if (trafficlightStatus.equals(TrafficlightStatus.ORANGE)
				|| trafficlightStatus.equals(TrafficlightStatus.ORANGEGREEN)) {

			if (street.toString().contains(("Straight"))) {
				image = new ImageView("/gerade_gelb.png");
				image.setFitHeight(100);
				image.setFitWidth(100);

			}

			if (street.toString().contains(("Crossing"))) {
				image = new ImageView("/kreuzung_gelb.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
			}

			if (street.toString().contains(("Junction"))) {
				image = new ImageView("/abzweigung_ampel_gelb.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
			}

			if (street.toString().contains(("Curve"))) {
				image = new ImageView("kurve_gelb.png");
				image.setFitHeight(100);
				image.setFitWidth(100);
			}
		}

		return image;

	}

}
