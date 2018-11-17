package Fsfbs;

import java.util.HashMap;
import java.util.Map;

public class Controller {
	private static Map<String, User> userList = new HashMap<>();
	private static Map<String, SportCentre> sportCentreList = new HashMap<>();
	private static Controller instance=null;
	
	private Controller() {
		//import 
	}

	public User getUserbyID(String userName) {
		return userList.get(userName);
	}

	public SportCentre getSportCentrebyID(String scid) {
		return sportCentreList.get(scid);
	}
	public void printAllFacilities() {
		
	}
	public static Controller getInstance() {
		if(instance == null)
			instance = new Controller();
		return instance;
	}

	public Facilities getFacilities(SportCentre sc, String facilitiesId){
	    return sc.getFacilityByID(facilitiesId);
    }
}
