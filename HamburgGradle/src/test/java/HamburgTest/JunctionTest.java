package HamburgTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.components.Curve;
import business.components.Direction;
import business.components.Junction;

public class JunctionTest {

	Junction junction = new Junction();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void rotateJunctionTest() {

		assertEquals(junction.getDirections().get(0), Direction.DOWN);
		assertEquals(junction.getDirections().get(1), Direction.RIGHT);
		assertEquals(junction.getDirections().get(2), Direction.LEFT);
		junction.rotate();
		assertEquals(junction.getDirections().get(0), Direction.DOWN);
		assertEquals(junction.getDirections().get(1), Direction.LEFT);
		assertEquals(junction.getDirections().get(2), Direction.UP);
		junction.rotate();
		assertEquals(junction.getDirections().get(0), Direction.RIGHT);
		assertEquals(junction.getDirections().get(1), Direction.LEFT);
		assertEquals(junction.getDirections().get(2), Direction.UP);
		junction.rotate();
		assertEquals(junction.getDirections().get(0), Direction.DOWN);
		assertEquals(junction.getDirections().get(1), Direction.RIGHT);
		assertEquals(junction.getDirections().get(2), Direction.UP);

	}

}