package test;
import Fsfbs.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_addBooking {

	@Test
	public void test_addBooking() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExFullBooking  {
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E1B2", 1617);
		int result = tester.getTodayBookingNum();
		assertEquals(1, result);
	}

	@Test
	public void test_addBooking2() throws ExFullBooking, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr B", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E1B2", 2211);
		int result = tester.getTodayBookingNum();
		assertEquals(0, result);
	}

	@Test
	public void test_addBooking3() throws ExFullBooking, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr C", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E2B2", 1617);
		tester.addBooking("E2B2", 1718);
		tester.addBooking("E2B2", 1819);
		tester.addBooking("E2B2", 1920);
		int result = tester.getTodayBookingNum();
		assertEquals(3, result);
	}

	@Test
	public void test_addBooking_twoPerson() throws ExFullBooking, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E1B2", 1617);

		User tester2 = new User("Mr B", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester2.addBooking("E1B2", 1617);
		int result = tester2.getTodayBookingNum();
		assertEquals(0, result);
	}

}
