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

	/**
	 * 
	 * @return schaltet die Ampel durch und gibt den nachfolgenden Wert in der
	 *         Schaltreihenfolge zurück
	 */
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

	/**
	 * 
	 * @return Methode um die Ampel zu resetten
	 */
	public TrafficlightStatus reset() {
		status.set(TrafficlightStatus.RED);
		return status.get();
	}

	/**
	 * 
	 * @return - gibt den aktuellen TrafficlightStatus der Ampel zurück
	 */
	public TrafficlightStatus getTrafficlightStatus() {
		return status.get();
	}

	/**
	 * 
	 * @return - gibt den aktuellen TrafficlightStatus der Ampel als Property zurück
	 */
	public SimpleObjectProperty<TrafficlightStatus> getStatus() {
		return status;
	}

	/**
	 * 
	 * @return direction - gibt die Direction zurück
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * 
	 * @param street             - bekommt eine Street übergeben, damit das passende
	 *                           Bild geladen werden kann
	 * @param trafficlightStatus - bekommt den Status mit, damit die passende Farbe
	 *                           geladen werden kann
	 * @return gibt das geladene Bild zurück
	 */
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
