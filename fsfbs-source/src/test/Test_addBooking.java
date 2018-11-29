package test;
import Fsfbs.*;
import Util.UtilTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exception.ExFacilityIdNotExist;
import Exception.ExFullBooking;
import Exception.ExIOErrorinGetConfig;
import Exception.ExMemberShipFilePathNotExist;
import Exception.ExSCFilesNotExist;

class Test_addBooking {

	class UtilTime_stub extends UtilTime {
		public String getCurrentTime() {
			return "15:00:00";
		}
	}

	@Test
	public void test_addBooking() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		UtilTime utilTime_stub = UtilTime_stub.getTimeInstance();
		controller.importData();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E1B2", 1617,utilTime_stub);
		int result = tester.getTodayBookingNum();
		assertEquals(1, result);
	}

	@Test
	public void test_addBooking2() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		UtilTime utilTime_stub = UtilTime_stub.getTimeInstance();
		controller.importData();
		User tester = new User("Mr B", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E1B2", 2211,utilTime_stub);
		int result = tester.getTodayBookingNum();
		assertEquals(0, result);
	}

	@Test
	public void test_addBooking3() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		UtilTime utilTime_stub = UtilTime_stub.getTimeInstance();
		controller.importData();
		User tester = new User("Mr C", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E2B2", 1617,utilTime_stub);
		tester.addBooking("E2B2", 1718,utilTime_stub);
		tester.addBooking("E2B2", 1819,utilTime_stub);
		tester.addBooking("E2B2", 1920,utilTime_stub);
		int result = tester.getTodayBookingNum();
		assertEquals(3, result);
	}

	@Test
	public void test_addBooking_twoPerson() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		UtilTime utilTime_stub = UtilTime_stub.getTimeInstance();
		controller.importData();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.addBooking("E1B2", 1617,utilTime_stub);

		User tester2 = new User("Mr B", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester2.addBooking("E1B2", 1617,utilTime_stub);
		int result = tester2.getTodayBookingNum();
		assertEquals(0, result);
	}

}
