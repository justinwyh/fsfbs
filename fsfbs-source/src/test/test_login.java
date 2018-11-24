package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import Fsfbs.*;

import org.junit.jupiter.api.Test;

class test_login {

	class UtilTime_Stub extends UtilTime {
		//Overwrite the class functions
	    public String getCurrentTime(){
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
	        LocalDateTime now = LocalDateTime.now();
	        return (dtf.format(now));
	    }
	    
	}

	@Test
	public void UtilTime() {
		boolean result;
		UtilTime_Stub time = new UtilTime_Stub();
		result = time.isTimeLaterThanCurrentTime("10:00:00");
		assertEquals(true, result);
	}

}
