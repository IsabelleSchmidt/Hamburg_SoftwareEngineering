package HamburgTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.components.Direction;
import business.components.Trafficlight;
import business.components.TrafficlightStatus;

public class TrafficlightTest {
	Trafficlight light = new Trafficlight(Direction.UP);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void switchLightTest() {

		assertEquals(light.getTrafficlightStatus(), TrafficlightStatus.RED);
		light.switchLight();
		assertEquals(light.getTrafficlightStatus(), TrafficlightStatus.ORANGEGREEN);
		light.switchLight();
		assertEquals(light.getTrafficlightStatus(), TrafficlightStatus.GREEN);
		light.switchLight();
		assertEquals(light.getTrafficlightStatus(), TrafficlightStatus.ORANGE);
		light.switchLight();
		assertEquals(light.getTrafficlightStatus(), TrafficlightStatus.RED);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void resetTest() {

		assertEquals(light.getTrafficlightStatus(), TrafficlightStatus.RED);
		light.reset();
	}
	
	

}