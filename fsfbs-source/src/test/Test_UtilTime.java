package test;

import static org.junit.Assert.*;

import Util.UtilTime;

import org.junit.jupiter.api.Test;

class Test_UtilTime {

	@Test
	public void UtilTime_hour() {
		UtilTime time = new UtilTime();
		boolean result = time.isTimeLaterThanCurrentTime("14:00:00");
		assertEquals(false, result);
	}

	@Test
	public void UtilTime_hour2() {
		UtilTime time = new UtilTime();
		boolean result = time.isTimeLaterThanCurrentTime("16:00:00");
		assertEquals(true, result);
	}


}
