package HamburgTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.components.Direction;
import business.components.Vehicle;
import business.simulation.Grid;
import javafx.beans.property.SimpleIntegerProperty;

public class VehicleTest {
	int x = 5;
	int y = 5;
	Grid grid = new Grid(5);
	Vehicle vehicle = new Vehicle(x, y, grid);

	SimpleIntegerProperty xPos = new SimpleIntegerProperty(95);
	SimpleIntegerProperty yPos = new SimpleIntegerProperty(72);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void rotateVehicleTest() {

		
		assertEquals(vehicle.getDirection(), Direction.RIGHT);
		xPos.set((95-95%100)+95);
		assertEquals(vehicle.getXPosition(),xPos.get());
		yPos.set((72-72%100)+72);
		assertEquals(vehicle.getYPosition(),yPos.get());
		vehicle.rotate();
		
		assertEquals(vehicle.getDirection(), Direction.DOWN);
		xPos.set((95-95%100)+33);
		assertEquals(vehicle.getXPosition(),xPos.get());
		yPos.set((72-72%100)+90);
		assertEquals(vehicle.getYPosition(),yPos.get());
		vehicle.rotate();
		
		assertEquals(vehicle.getDirection(), Direction.LEFT);
		xPos.set((95-95%100)+10);
		assertEquals(vehicle.getXPosition(),xPos.get());
		yPos.set((72-72%100)+33);
		assertEquals(vehicle.getYPosition(),yPos.get());
		vehicle.rotate();
		
		assertEquals(vehicle.getDirection(), Direction.UP);
		xPos.set((95-95%100)+67);
		assertEquals(vehicle.getXPosition(),xPos.get());
		yPos.set((72-72%100)+10);
		assertEquals(vehicle.getYPosition(),yPos.get());

	}

	@Test
	public void driveVehicleTest() {
		
		assertEquals(vehicle.getDirection(), Direction.RIGHT);
		xPos.set(xPos.get()+1);
		assertEquals(vehicle.getXPosition()+1, xPos.get());
		vehicle.drive();

		
	}

	@Test
	public void tellOpositeVehicleTest() {
		

		vehicle.tellOposite(Direction.RIGHT);
		vehicle.setDirection(Direction.LEFT);
		assertEquals(Direction.LEFT, vehicle.getDirection());
		
		vehicle.tellOposite(Direction.LEFT);
		vehicle.setDirection(Direction.RIGHT);
		assertEquals(Direction.RIGHT, vehicle.getDirection());
		
		vehicle.tellOposite(Direction.UP);
		vehicle.setDirection(Direction.DOWN);
		assertEquals(Direction.DOWN, vehicle.getDirection());
		
		vehicle.tellOposite(Direction.DOWN);
		vehicle.setDirection(Direction.UP);
		assertEquals(Direction.UP, vehicle.getDirection());
		
	}

}
