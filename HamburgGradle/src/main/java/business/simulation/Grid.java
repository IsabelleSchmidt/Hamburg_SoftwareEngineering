package business.simulation;

import business.components.StreetType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Grid{
	
	
	//properties x und y , größe wird von gui übernommen, aufteilung...
	//durch gui wenn nicht null in 2d array hier speichern mit namen und position 
	//oder Funktion wird in passende methode eingegeben die dann über die gui aufgerufen wird 
	//methode setGrid wird in gui aufgerufen und alle parameter mitgegeben um hier eine "Kopie" zu erstellen
	// if equals curve -> placeCurve methode aufrufen
	//remove methoden
	
	final int GRIDSIZE = 5;
	
	StreetType [][] grid = new StreetType[GRIDSIZE][GRIDSIZE];
	 
	public  IntegerProperty xPos;
	public IntegerProperty yPos;
	
	public Grid() {
		xPos = new SimpleIntegerProperty(0);
		yPos = new SimpleIntegerProperty(0);
	}
	
	
	
	public void setGrid(String name, int x, int y) {
		if(name.equals("Straight")) {
			grid[x][y]= StreetType.STRAIGHT;
		}if(name.equals("Curve")) {
			grid[x][y]= StreetType.CURVE;
		}if(name.equals("Crossing")) {
			grid[x][y]= StreetType.CROSSING;
		}if(name.equals("Junction")) {
			grid[x][y]= StreetType.JUNCTION;
		}
		
	
		System.out.println("Grid test: "+name+" "+x+" "+ y);

		
	}
	
	
	public void removeItem(int x, int y) {
		grid[x][y] = null;
	}


	public IntegerProperty getxPos() {
		return xPos;
	}


	public void setxPos(IntegerProperty xPos) {
		this.xPos = xPos;
	}


	public IntegerProperty getyPos() {
		return yPos;
	}


	public void setyPos(IntegerProperty yPos) {
		this.yPos = yPos;
	}
}
