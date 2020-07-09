package HamburgTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.components.Crossing;
import business.components.Curve;
import business.components.Junction;
import business.components.Straight;
import business.components.Street;
import business.simulation.Grid;

public class GridTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void placeStreetTest() {
		Grid grid = new Grid(5);
		Street straight = new Straight();
		Street curve = new Curve();
		Street crossing = new Crossing();
		Street junction = new Junction();
		
		grid.placeStreet(straight, 3, 3);
		grid.placeStreet(curve, 2, 4);
		grid.placeStreet(crossing, 0, 1);
		grid.placeStreet(junction, 2, 3);
		
		assertEquals(grid.getStreet(3, 3).toString(),"Straight");
		assertEquals(grid.getStreet(2, 4).toString(),"Curve");
		assertEquals(grid.getStreet(0, 1).toString(),"Crossing");
		assertEquals(grid.getStreet(2, 3).toString(),"Junction");
		
		
	}
	@Test
	public void removeStreetTest() {
		Grid grid = new Grid(5);
		grid.removeItem(2, 4);
		
		assertEquals(grid.getStreet(2, 4), null);
		
		
		
		
	}
	

}
