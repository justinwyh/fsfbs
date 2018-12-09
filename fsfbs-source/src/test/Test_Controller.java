package test;
import org.junit.Test;
import Fsfbs.Booking;
import Fsfbs.Controller;
import Fsfbs.User;
import Util.UtilTime;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test.*;
import org.junit.runner.RunWith;

import Exception.ExFacilityIdNotExist;
import Exception.ExIOErrorinGetConfig;
import Exception.ExMemberShipFilePathNotExist;
import Exception.ExSCFilesNotExist;
import Exception.ExUserIdNotExist;

public class Test_Controller {

	@Test
	public void test_controller() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		controller.importData();
	}
	
	//exist
	@Test
	public void test_searchUserById() {
		Controller controller = Controller.getInstance();
		User user = controller.getUserbyID("Ada");
		assertEquals(user.getUserName(),new User("Ada","","","","").getUserName());
	}
	
	//not exist 
	@Test
	public void test_searchUserById_False() {
		try {
		Controller controller = Controller.getInstance();
		controller.searchUserById("John");
		}
		catch(Exception e) {
			assertEquals(e.toString(),new ExUserIdNotExist("John").toString());		
		}
	}
	
}
