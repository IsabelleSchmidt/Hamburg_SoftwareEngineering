package business.simulation;

import java.util.ArrayList;

import business.components.Street;
import business.components.StreetType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Grid {

	private int gridsize;
	private SimpleObjectProperty<Street[][]> grid;

	public Grid(int GRIDSIZE) {

		grid = new SimpleObjectProperty<>();
		grid.set(new Street[GRIDSIZE][GRIDSIZE]);
		this.gridsize = GRIDSIZE;

	}

	/**
	 * 
	 * @param street - bekommt ein Element vom Typ Street
	 * @param posX   - bekommt die x Koordidate der Platzierung im Grid
	 * @param posY   - bekommt die y Koordidate der Platzierung im Grid
	 */
	public void placeStreet(Street street, int posX, int posY) {
		Street[][] tempGrid = new Street[gridsize][gridsize];
		for (int y = 0; y <= gridsize - 1; y++) {
			for (int x = 0; x <= gridsize - 1; x++) {
				tempGrid[x][y] = grid.get()[x][y];
			}
		}
		tempGrid[posX][posY] = street;
		grid.set(tempGrid);
	}

	/**
	 * 
	 * @param posX - bekommt die x Koordidate der Entferungn aus dem Grid
	 * @param posY - bekommt die y Koordidate der Entferungn aus dem Grid
	 */
	public void removeItem(int posX, int posY) {

		Street[][] tempGrid = new Street[gridsize][gridsize];
		for (int y = 0; y <= gridsize - 1; y++) {
			for (int x = 0; x <= gridsize - 1; x++) {
				tempGrid[x][y] = grid.get()[x][y];
			}
		}

		tempGrid[posX][posY] = null;
		grid.set(tempGrid);

	}

	/**
	 * 
	 * @return gibt das Grid als Property zurück mit dem Grid 
	 */
	public SimpleObjectProperty<Street[][]> getGrid() {
		return grid;
	}

	/**
	 * 
	 * @param x - bekommt die x Koordidate für die gesuchte Street im Grid
	 * @param y - bekommt die y Koordidate für die gesuchte Street im Grid
	 * @return  - gibt das Street Element an der Stelle x,y im Grid
	 */
	public Street getStreet(int x, int y) {
		return grid.get()[x][y];
	}

}
