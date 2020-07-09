package business.simulation;

import java.util.ArrayList;

import business.components.Street;
import business.components.StreetType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Grid {

	// properties x und y , gr��e wird von gui �bernommen, aufteilung...
	// durch gui wenn nicht null in 2d array hier speichern mit namen und position
	// oder Funktion wird in passende methode eingegeben die dann �ber die gui
	// aufgerufen wird
	// methode setGrid wird in gui aufgerufen und alle parameter mitgegeben um hier
	// eine "Kopie" zu erstellen
	// if equals curve -> placeCurve methode aufrufen
	// remove methoden

	private int gridsize;
	private SimpleObjectProperty<Street[][]> grid;

	public Grid(int GRIDSIZE) {

		grid = new SimpleObjectProperty<>();
		grid.set(new Street[GRIDSIZE][GRIDSIZE]);
		this.gridsize = GRIDSIZE;

	}

	//TODO: Fragen, warum kopie
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

	public void removeItem(int posX, int posY) {

		// TODO: bekommt x,y übergeben, um an der passenden Stelle zu entfernen

		Street[][] tempGrid = new Street[gridsize][gridsize];
		for (int y = 0; y <= gridsize - 1; y++) {
			for (int x = 0; x <= gridsize - 1; x++) {
				tempGrid[x][y] = grid.get()[x][y];
			}
		}

		tempGrid[posX][posY] = null;
		grid.set(tempGrid);

	}

	public SimpleObjectProperty<Street[][]> getGrid() {
		return grid;
	}

	public Street getStreet(int x, int y) {
		return grid.get()[x][y];
	}

}
