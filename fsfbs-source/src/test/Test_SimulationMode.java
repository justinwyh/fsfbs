package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import Fsfbs.*;


public class Test_SimulationMode {

	@Test
	public void test_SimulationMode() throws IOException {
		assertEquals(true,SimulationMode.getSimulationMode());
	}
	
}
