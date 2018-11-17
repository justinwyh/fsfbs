package Fsfbs;

import java.util.HashMap;
import java.util.Map;

public class Controller {
	private static Map<String, User> userList = new HashMap<>();
	private static Map<String, Facilities> facilitiesList = new HashMap<>();
	private static Controller instance=null;
	
	private Controller() {
		//import 
	}
	public User getUserbyID(String userid) {
		return userList.get(userid);
	}

	public Facilities getFacilitybyID(String facid) {
		return facilitiesList.get(facid);
	}
	
	public static Controller getInstance() {
		if(instance == null)
			instance = new Controller();
		return instance;
	}
}
