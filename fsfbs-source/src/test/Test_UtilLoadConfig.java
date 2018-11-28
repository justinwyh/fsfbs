package test;

import Util.UtilsLoadconfig;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Test_UtilLoadConfig {

	@Test
	public void test_membership() throws IOException {
		String result = UtilsLoadconfig.getConfig("membershipFilePath");
		String str = "./Data/Membership/";
		assertEquals(str, result);
	} 
	
	@Test
	public void test_booking() throws IOException {
		String result = UtilsLoadconfig.getConfig("bookingFilePath");
		String str = "./Data/Booking/";
		assertEquals(str, result);
	}
	
	@Test
	public void test_sportsCentre() throws IOException {
		String result = UtilsLoadconfig.getConfig("sportCentreFilePath");
		String str = "./Data/SportFacility/";
		assertEquals(str, result);
	}
	
	@Test
	public void test_time() throws IOException {
		String result = UtilsLoadconfig.getConfig("timeScheduleFilePath");
		String str = "./Data/TimeSchedule/";
		assertEquals(str, result);
	}

}
