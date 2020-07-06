package business.components;

/**
 * 
 * extended Street, weil die Ampel teil der Straße ist. Quasi nur eine besondere
 * Art von Straße
 *
 */
public class Trafficlight extends Street {
	
	private String status;
	private int duration;

	public Trafficlight(int positionX, int positionY, int alignment, String status, int duration) {
		super(positionX, positionY, alignment);
		this.status = status;
		this.duration = duration;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
