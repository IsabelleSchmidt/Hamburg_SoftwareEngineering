package HamburgTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.components.Direction;
import business.components.Vehicle;
import business.simulation.Grid;

public class VehicleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void rotateVehicleTest() {
		int x=5; 
		int y=5; 
		Grid grid = new Grid(5);
		Vehicle vehicle = new Vehicle(x,y,grid);
		
		assertEquals(vehicle.getDirection(), Direction.RIGHT);
		vehicle.rotate();
		assertEquals(vehicle.getDirection(), Direction.DOWN);
		vehicle.rotate();
		assertEquals(vehicle.getDirection(), Direction.LEFT);
		vehicle.rotate();
		assertEquals(vehicle.getDirection(), Direction.UP);
	
		
		
		
		
	}
	@Test
	public void driveVehicleTest() {
		int x=5; 
		int y=5; 
		Grid grid = new Grid(25);
		Vehicle vehicle = new Vehicle(x,y,grid);
		
		assertEquals(vehicle.getDirection(), Direction.RIGHT);
		vehicle.drive();
		
	}
	@Test
	public void tellOpositeVehicleTest() {
		int x=5; 
		int y=5; 
		Grid grid = new Grid(25);
		Vehicle vehicle = new Vehicle(x,y, grid);
		vehicle.tellOposite(Direction.UP);
	}

}
