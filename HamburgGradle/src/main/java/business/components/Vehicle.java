package business.components;

import javafx.animation.AnimationTimer;

public class Vehicle extends Item{


	//double width;
	//double length;
	
	boolean running;
	
	private Color color;
	
	AnimationTimer timer;
	
	/*fahren-methode muss hier rein womit wir die autos auf den straﬂen fahren lassen 
	 * 
	 */
	
	private class MyTimer extends AnimationTimer {

		@Override
		public void handle(long now) {
			start();
			
		}
		
	}
	
	public Vehicle(int positionX, int positionY,int alignment, Color color) {		
		super(positionX, positionY,alignment);
		this.color = color;
	
	}
	

	
	private void drive() {
		
		while(running = true) {
			
		}
	}
	public Color getColor() {
		return color;
	}
}
