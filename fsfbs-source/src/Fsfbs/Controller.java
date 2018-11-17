package Fsfbs;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {
	private static Map<String, User> userList = new HashMap<>();
	private static Map<String, SportCentre> sportCentreList = new HashMap<>();
	private static Controller instance=null;
	
	private Controller() {
		//import membership
		
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

	public Facility getFacility(SportCentre sc, String facilitiesId){
	    return sc.getFacilityByID(facilitiesId);
    }
	
	//import Membership
	public void importAllMember() throws IOException {
		File file = new File(UtilsLoadconfig.getConfig("membershipFilePath"));
		File[] files = file.listFiles(new FilenameFilter() {
	        @Override
	        public boolean accept(File dir, String name) {
	            return !name.equals(".DS_Store");
	        }
	    });
        for(File f: files){
       	 Scanner inFile = new Scanner(f);
         userList.put(f.getName().substring(0,f.getName().length()-4),new User(inFile.next(),inFile.next(),inFile.next(),inFile.next(),inFile.next()));; 
         System.out.println(f.getName()+"...created");
        }
	}
	
	//import SportFacility 
	public void importAllSportFacility() throws IOException {
		File file = new File(UtilsLoadconfig.getConfig("sportCentreFilePath"));
		File[] files = file.listFiles(new FilenameFilter() {
	        @Override
	        public boolean accept(File dir, String name) {
	            return !name.equals(".DS_Store");
	        }
	    });
		 for(File f: files){
	       	 Scanner inFile = new Scanner(f);
	       	 SportCentre temp = new SportCentre(inFile.nextLine(),inFile.nextLine(),inFile.nextLine(),inFile.nextLine());
	       	 temp.addFacilitytoSC(facilitiesId, facility);
	         sportCentreList.put(f.getName().substring(0,f.getName().length()-4),new ); 
	         System.out.println(f.getName()+"...created");
	        }
		 
	}
}
