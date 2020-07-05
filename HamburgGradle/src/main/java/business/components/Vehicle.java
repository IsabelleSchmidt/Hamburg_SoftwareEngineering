package business.components;

import javafx.animation.AnimationTimer;

public class Vehicle {

	int positionX;
	int positionY;
	int alignment;

	double width;
	double length;
	
	boolean running;
	
	String color;
	
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
	
	public Vehicle() {		
	}
	
	private void remove() {
		
	}
	
	private void drive() {
		
		while(running = true) {
			
		}
	}
}
