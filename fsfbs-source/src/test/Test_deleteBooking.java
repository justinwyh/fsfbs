package test;
import Fsfbs.*;
import Util.UtilTime;

import org.junit.jupiter.api.Test;

import Exception.ExFacilityIdNotExist;
import Exception.ExIOErrorinGetConfig;
import Exception.ExMemberShipFilePathNotExist;
import Exception.ExSCFilesNotExist;
import Exception.ExFullBooking;

import static org.junit.jupiter.api.Assertions.*;

public class Test_deleteBooking {

	class UtilTime_stub extends UtilTime {
		public String getCurrentTime() {
			return "15:00:00";
		}
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
