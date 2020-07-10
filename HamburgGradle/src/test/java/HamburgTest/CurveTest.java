package HamburgTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.components.Curve;
import business.components.Direction;

public class CurveTest {
	Curve curve = new Curve();
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void rotateTest() {
		assertEquals(curve.getDirections().get(0), Direction.DOWN);
		assertEquals(curve.getDirections().get(1), Direction.RIGHT);
		curve.rotate();
		assertEquals(curve.getDirections().get(0), Direction.LEFT);
		assertEquals(curve.getDirections().get(1), Direction.DOWN);
		curve.rotate();
		assertEquals(curve.getDirections().get(0), Direction.UP);
		assertEquals(curve.getDirections().get(1), Direction.LEFT);
		curve.rotate();
		assertEquals(curve.getDirections().get(0), Direction.UP);
		assertEquals(curve.getDirections().get(1), Direction.RIGHT);
		
	}
	
	@After
	public void tearDown() {
		
	}

}