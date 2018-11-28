package test;

import static org.junit.Assert.*;

import Fsfbs.*;
import Util.UtilTime;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Test_UtilTime {

	@Test
	public void UtilTime_hour() throws IOException {
		UtilTime time = new UtilTime();
		boolean result = time.isTimeLaterThanCurrentTime("14:00:00");
		assertEquals(false, result);
	}

	@Test
	public void UtilTime_hour2() throws IOException {
		UtilTime time = new UtilTime();
		boolean result = time.isTimeLaterThanCurrentTime("16:00:00");
		assertEquals(true, result);
	}


}
