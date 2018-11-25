package test;

import static org.junit.Assert.*;

import Fsfbs.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Test_UtilTime {

	@Test
	public void UtilTime_hour() throws IOException {
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
	public void UtilTime_hour2() throws IOException {
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

	@Test
	public void UtilTime_min() throws IOException {
		class UtilTime_Stub extends UtilTime {
			//Overwrite the class functions
		    public String getCurrentTime() {
				return "15:30:00";
			}
		}
		UtilTime_Stub time = new UtilTime_Stub();
		boolean result = time.isTimeLaterThanCurrentTime("15:29:00");
		assertEquals(false, result);
	}

	@Test
	public void UtilTime_min2() throws IOException {
		class UtilTime_Stub extends UtilTime {
			//Overwrite the class functions
		    public String getCurrentTime() {
				return "15:30:00";
			}
		}
		UtilTime_Stub time = new UtilTime_Stub();
		boolean result = time.isTimeLaterThanCurrentTime("15:31:00");
		assertEquals(true, result);
	}

	@Test
	public void UtilTime_second() throws IOException {
		class UtilTime_Stub extends UtilTime {
			//Overwrite the class functions
		    public String getCurrentTime() {
				return "20:00:30";
			}
		}
		UtilTime_Stub time = new UtilTime_Stub();
		boolean result = time.isTimeLaterThanCurrentTime("20:00:29");
		assertEquals(false, result);
	}

	@Test
	public void UtilTime_second2() throws IOException {
		class UtilTime_Stub extends UtilTime {
			//Overwrite the class functions
		    public String getCurrentTime() {
				return "20:00:30";
			}
		}
		UtilTime_Stub time = new UtilTime_Stub();
		boolean result = time.isTimeLaterThanCurrentTime("20:00:31");
		assertEquals(true, result);
	}
}
