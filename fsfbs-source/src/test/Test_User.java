package test;
import Fsfbs.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_User {
	


	@Test
	public void test_addBooking() throws ExFullBooking, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E1B1", 2211);
		int result = tester.getTodayBookingNum();
		assertEquals(1, result);
	}

}
