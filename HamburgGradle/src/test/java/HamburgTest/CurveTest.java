package HamburgTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.components.Curve;
import business.components.Direction;

public class CurveTest {
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void rotateTest() {
		
		Curve curve = new Curve();
//		int rotationCount = 1;
		
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
		
		
//		assertEquals(curve.getDirections(), rotationCount);
//		curve.rotate();
//		assertEquals(curve.getDirections(), rotationCount);
//		curve.rotate();
		
//		assertEquals(Direction.DOWN, );
		
	}
	
	@After
	public void tearDown() {
		
	}

}
