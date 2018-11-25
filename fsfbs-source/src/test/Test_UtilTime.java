package test;

import static org.junit.Assert.*;

import Fsfbs.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Test_UtilTime {

	@Test
	public void UtilTime_hour() {
		class UtilTime_Stub extends UtilTime {
			//Overwrite the class functions
		    public String getCurrentTime() {
				return "12:00:00";
			}
		}
		UtilTime_Stub time = new UtilTime_Stub();
		boolean result = time.isTimeLaterThanCurrentTime("09:00:00");
		assertEquals(false, result);
	}

	@Test
	public void UtilTime_hour2() {
		class UtilTime_Stub extends UtilTime {
			//Overwrite the class functions
		    public String getCurrentTime() {
				return "12:00:00";
			}
		}
		UtilTime_Stub time = new UtilTime_Stub();
		boolean result = time.isTimeLaterThanCurrentTime("14:00:00");
		assertEquals(true, result);
	}
	
}
