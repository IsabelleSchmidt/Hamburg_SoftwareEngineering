package business.components;

public class Item {

	private int positionX;
	private int positionY;
	private int alignment;

	
	public Item(int positionX, int positionY, int alignment) {
		this.setPositionX(positionX);
		this.setPositionY(positionY);
		this.setAlignment(alignment);
		
	}
	
	

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}



	public int getAlignment() {
		return alignment;
	}



	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	
}
