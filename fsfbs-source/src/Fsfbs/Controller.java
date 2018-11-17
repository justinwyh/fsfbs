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
	
	private Controller() throws IOException {
		//import membership
		this.importAllMember();
		this.importAllSportFacility();
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
	public static Controller getInstance() throws IOException {
		if(instance == null)
			instance = new Controller();
		return instance;
	}

    public SportCentre searchSportCentre (String inputSCId) throws ExSportCentreNotExist{
	    SportCentre sc = getSportCentrebyID(inputSCId);
        if (sc == null){
            throw new ExSportCentreNotExist();
        }
        return sc;
    }
    public Facility searchFacility (String facilityId) throws ExFacilityIdNotExist,ExSportCentreNotExist {
            SportCentre sc;
            Facility facility;
            String scId = facilityId.substring(0, 2);
            sc = searchSportCentre(scId);
            facility = sc.findFacilityByID(facilityId);
            return facility;
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
	       	 while(inFile.hasNext()) {
	       		 String fid = inFile.next();
	       		 System.out.println(fid+"...created and added to "+temp.getScId());
	       		 if(fid.charAt(2)=='B')
	       			 temp.addFacilitytoSC(fid, new Facility_Badminton(fid));
	       		 else if(fid.charAt(2)=='A')
	       			 temp.addFacilitytoSC(fid, new Facility_ActivityRoom(fid));
	       		 else if(fid.charAt(2)=='T')
	       			 temp.addFacilitytoSC(fid, new Facility_TableTennis(fid));
	       	 }
	     	sportCentreList.put(temp.getScId(), temp); 
	     	System.out.println("Added "+temp.getScId());
	        }
	
	}
}
