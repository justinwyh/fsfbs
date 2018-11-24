package test;

import static org.junit.Assert.*;

import Fsfbs.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class test_UtilTime {

	class UtilTime_Stub extends UtilTime {
		//Overwrite the class functions
	    public String getCurrentTime() {
			return "12:00:00";
		}
	}

	@Test
	public void UtilTime() {
		boolean result;
		UtilTime_Stub time = new UtilTime_Stub();
		result = time.isTimeLaterThanCurrentTime("09:00:00");
		assertEquals(false, result);
	}


}
