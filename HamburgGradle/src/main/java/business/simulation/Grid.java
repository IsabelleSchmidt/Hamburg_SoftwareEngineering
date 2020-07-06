package business.simulation;

import java.util.ArrayList;

import business.components.Item;
import business.components.Street;
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
	int x;
	int y;
	
	Street street;
	
	StreetType [][] grid = new StreetType[GRIDSIZE][GRIDSIZE];
	
	ArrayList<Item> elemente;
	 
	public  IntegerProperty xPos;
	public IntegerProperty yPos;
	
	public Grid() {
		xPos = new SimpleIntegerProperty(0);
		yPos = new SimpleIntegerProperty(0);
		
		elemente = new ArrayList<>();
		
	}	
	
	public void setGrid(String name) {
		
		street = new Street(null, x, y, GRIDSIZE);
		x = xPos.get();
		y = yPos.get();
		
		if(name.equals("Straight")) {
			grid[x][y]= StreetType.STRAIGHT;
			street.setType(StreetType.STRAIGHT);
			street.setPositionX(x);
			street.setPositionY(y);
			elemente.add(street);
			System.out.println("liste: " + elemente.toString());
		}if(name.equals("Curve")) {
			grid[x][y]= StreetType.CURVE;
			street.setType(StreetType.CURVE);
			street.setPositionX(x);
			street.setPositionY(y);
			elemente.add(street);
			System.out.println("liste: " + elemente.toString());
		}if(name.equals("Crossing")) {
			grid[x][y]= StreetType.CROSSING;
			street.setType(StreetType.CROSSING);
			street.setPositionX(x);
			street.setPositionY(y);
			elemente.add(street);
			System.out.println("liste: " + elemente.toString());
		}if(name.equals("Junction")) {
			grid[x][y]= StreetType.JUNCTION;
			street.setType(StreetType.JUNCTION);
			street.setPositionX(x);
			street.setPositionY(y);
			elemente.add(street);
			System.out.println("liste: " + elemente.toString());
		}
		
	
		System.out.println("Grid test: "+name+" "+ x +" "+ y);

		
	}
	
	
	public void removeItem() {
		
		x = xPos.get();
		y = yPos.get();
		grid[x][y] = null;
		
		
		System.out.println("Print: ");
		for(int i = 0; i< grid.length;i++) {
			for(int j = 0; j<grid.length;j++) {
				System.out.println(grid[i][j]);
			}
		}
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
