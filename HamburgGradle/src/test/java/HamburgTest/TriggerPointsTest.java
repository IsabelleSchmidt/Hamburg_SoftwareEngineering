package HamburgTest;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.components.Direction;
import business.components.Straight;
import business.components.Street;
import business.components.TriggerPoints;
import business.components.Vehicle;
import business.simulation.Grid;

public class TriggerPointsTest {
	
	Point triggerWest = new Point(10, 67);
	Point triggerNorth = new Point(33, 10);
	Point triggerEast = new Point(90, 33);
	Point triggerSouth = new Point(67, 90);

	Point downRight = new Point(67, 67);
	Point upRight = new Point(67, 33);
	Point downLeft = new Point(33, 67);
	Point upLeft = new Point(33, 33);
	
	List<Direction> directions = new ArrayList<>();
	TriggerPoints triggerPoints = new TriggerPoints();
	Grid grid = new Grid(5);
	Vehicle car = new Vehicle(3, 3, grid);

	int x = 10;
	int y = 67;
	Point currentPoint = new Point(x % 100, y % 100);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void isTriggeredTest() {
	
		triggerPoints.isTriggered(car, x, y);
		
		assertTrue(currentPoint.equals(triggerWest));
	}
	
	@Test
	public void canTurnToTest() {
		int x = 33;
		int y = 67;
		Point carPoint = new Point(x % 100, y % 100);
		
		assertEquals(Direction.RIGHT, car.getDirection());
		assertEquals(downLeft, carPoint);
		car.setDirection(Direction.DOWN);
		
	
	}

	
	@Test
	public void tellOpositeTest() {
		assertEquals(Direction.LEFT, triggerPoints.tellOposite(Direction.RIGHT));
		assertEquals(Direction.DOWN, triggerPoints.tellOposite(Direction.UP));
		assertEquals(Direction.UP, triggerPoints.tellOposite(Direction.DOWN));
		assertEquals(Direction.RIGHT, triggerPoints.tellOposite(Direction.LEFT));
	}
}
