package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Fsfbs.Controller;
import Fsfbs.ExFacilityIdNotExist;
import Fsfbs.ExIOErrorinGetConfig;
import Fsfbs.ExMemberShipFilePathNotExist;
import Fsfbs.ExSCFilesNotExist;
import Fsfbs.User;

class Test_searchVacancy {

	@Test
	public void test_searchVacancies() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist  {
		// user calls each facility to show their vacancy
		Controller controller = Controller.getInstance();
		controller.importData();
		User tester = new User("Mr C", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.searchVacancies("E1", "badminton");
		
	}
}
