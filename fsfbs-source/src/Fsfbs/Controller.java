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
		System.out.println("Please enter the key for selecting Sport Centre");
		for (String key: sportCentreList.keySet()) {
		    System.out.println("key : " + key);
		    System.out.println("Name : " + sportCentreList.get(key).getScName());
		    System.out.println("Address : " + sportCentreList.get(key).getScAddress());
		    System.out.println("Tel : "+sportCentreList.get(key).getScTel());
		}
	}
	public static Controller getInstance() {
		if(instance == null)
			instance = new Controller();
		return instance;
	}

	public Facility getFacilities(SportCentre sc, String facilitiesId){
	    return sc.getFacilityByID(facilitiesId);
    }
}
