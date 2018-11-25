package test;
import Fsfbs.*;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_User {

//	@Before
//	public void setupCurrentTime() {
//		class UtilTime_stub extends UtilTime {
//			public String getCurrentTime() {
//				return "11:00:00";
//			}
//		}
//
//	}

	@Test
	public void test_addBooking() throws ExFullBooking, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E1B2", 1213);
		int result = tester.getTodayBookingNum();
		assertEquals(1, result);
	}

	@Test
	public void test_addBooking4() throws ExFullBooking, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr B", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E1B2", 1213);
		int result = tester.getTodayBookingNum();
		assertEquals(0, result);
	}
	/*
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
		tester.addBooking("E2B2", 1011);
		tester.addBooking("E2B2", 1112);
		tester.addBooking("E2B2", 1213);
		tester.addBooking("E2B2", 1314);
		int result = tester.getTodayBookingNum();
		assertEquals(0, result);
	}
	*/
	/*
	public void test_searchVacancies() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist  {
		// user calls each facility to show their vacancy
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr C", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.searchVacancies("E1", "badminton");

	}
	*/


}
