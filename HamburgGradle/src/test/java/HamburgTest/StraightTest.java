package HamburgTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.components.Direction;
import business.components.Straight;

public class StraightTest {
	Straight straight = new Straight();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void rotateStraightTest() {

		assertEquals(straight.getDirections().get(0), Direction.DOWN);
		assertEquals(straight.getDirections().get(1), Direction.UP);
		straight.rotate();
		assertEquals(straight.getDirections().get(0), Direction.RIGHT);
		assertEquals(straight.getDirections().get(1), Direction.LEFT);
		straight.rotate();
		assertEquals(straight.getDirections().get(0), Direction.DOWN);
		assertEquals(straight.getDirections().get(1), Direction.UP);
		straight.rotate();
		assertEquals(straight.getDirections().get(0), Direction.RIGHT);
		assertEquals(straight.getDirections().get(1), Direction.LEFT);

	}
}
