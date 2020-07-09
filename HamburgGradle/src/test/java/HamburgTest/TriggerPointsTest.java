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
import business.components.Street;

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

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void chooseRandomDirectionTest() {
	
		Random random = new Random();
		Street street;
		Direction direction;
		
		//assertEquals(random.nextInt(directions.size()), direction );
	}

}
