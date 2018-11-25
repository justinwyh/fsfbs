package test;
import Fsfbs.*;
import org.junit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Test_deleteBooking {
	
	@Test
	public void test_deleteBooking() throws ExFullBooking, ExBookingNotExist, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr A", "password", "Membership_Audit", "E1", "Facility_Badminton");
		tester.addBooking("E1B2", 1617);
		int result = tester.getTodayBookingNum();
		assertEquals(1, result);
//		int result = tester.getTodayBookingNum();
//		tester.deleteBooking("20181125E1B21617");
//		assertEquals(0, result-1);
	}
	
//	@Test
//	public void test_deleteBooking2() throws ExFullBooking, ExBookingNotExist, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
//		Controller controller = Controller.getInstance();
//		controller.importData();
//		User tester = new User("Mr A", "password", "Membership_Audit", "E1", "Facility_Badminton");
//		tester.addBooking("E1B2", 2021);
//		tester.addBooking("E1B2", 2122);
//		tester.deleteBooking("20181125E1B22122");
//		int result = (tester.getTodayBookingNum())-1;
//		assertEquals(1, result);
//	}
//	
}
