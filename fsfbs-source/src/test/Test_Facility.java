package test;

import Facility.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest(Facility.class)
@RunWith(PowerMockRunner.class)
public class Test_Facility {

	
	@Test
	public void test_facility_getFacilityId() {
		Facility testFacility = new Facility_Badminton("A1B1");
		assertEquals("A1B1",testFacility.getFacilityId());
	}
	@Test
	public void test_facility_getBookingIDByTIme() {
		Facility testFacility = new Facility_Badminton("A1B1");
		testFacility.addToTimeTable(1112,"20180101A1B11112");
		assertEquals("20180101A1B11112",testFacility.getBookingIdbyTime(1112));
	}
	
}
