package Fsfbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {
	private static Map<String, User> userList;
	private static Map<String, SportCentre> sportCentreList;
	private static Controller instance=null;

	private Controller() {
	    userList = new HashMap<>();
	    sportCentreList = new HashMap<>();
	}

	public void importData() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
        //import membership
        this.importAllMember();
        this.importAllSportFacility();
        this.importAllSchedule();
    }

    public static Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
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

    public User searchUserById (String userId) throws ExUserIdNotExist{
	    User user = getUserbyID(userId);
	    if(user == null){
	        throw new ExUserIdNotExist(userId);
        }
        return user;
    }

    public void addUser(User newuser){
	    userList.putIfAbsent(newuser.getUserName(),newuser);
    }

    public void addSC(SportCentre newSC){
        sportCentreList.putIfAbsent(newSC.getScId(),newSC);
    }

    public ArrayList<Facility> searchFacilitiesByType(String scId, String sfType) throws ExSportCentreNotExist, ExFacilityNameNotExist, ExFacilityIdNotExist{
        ArrayList<Facility> facilitiesList = new ArrayList<>();
	    SportCentre sc = searchSportCentre(scId);
	    System.out.println("\nSport Centre: " + sc.getScName()+ "\n");
	    String type = null;
        switch (sfType) {
            case "badminton":
                type = "B";
                break;
            case "tableTennis":
                type = "T";
                break;
            case "activityRoom":
                type = "A";
                break;
            default:
                throw new ExFacilityNameNotExist();
        }
        for (String key : sc.getKeySet()){
            if(key.contains(scId + type)){
                facilitiesList.add(sc.findFacilityByID(key));
            }
        }
        return facilitiesList;
    }

	//import Membership
	public void importAllMember() throws ExMemberShipFilePathNotExist,ExIOErrorinGetConfig {
	    try {
            File file = new File(UtilsLoadconfig.getConfig("membershipFilePath"));
            File[] files = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return !name.equals(".DS_Store");
                }
            });
            for (File f : files) {
                Scanner inFile = new Scanner(f);
                userList.put(f.getName().substring(0, f.getName().length() - 4), new User(inFile.next(), inFile.next(), inFile.next(), inFile.next(), inFile.next()));
                ;
                if(SimulationMode.getSimulationMdoe())
                System.out.println(f.getName() + "...created");
            }
        }
        catch(FileNotFoundException fe){
            throw new ExMemberShipFilePathNotExist();
        }
        catch(IOException ioe){
            throw new ExIOErrorinGetConfig();
        }
	}

	//import SportFacility
	public void importAllSportFacility() throws ExSCFilesNotExist,ExIOErrorinGetConfig {
	    try {
            File file = new File(UtilsLoadconfig.getConfig("sportCentreFilePath"));
            File[] files = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return !name.equals(".DS_Store");
                }
            });
            for (File f : files) {
                Scanner inFile = new Scanner(f);
                SportCentre temp = new SportCentre(inFile.nextLine(), inFile.nextLine(), inFile.nextLine(), inFile.nextLine());
                while (inFile.hasNext()) {
                    String fid = inFile.next();
                    if(SimulationMode.getSimulationMdoe())
                    System.out.println(fid + "...created and added to " + temp.getScId());
                    if (fid.charAt(2) == 'B')
                        temp.addFacilitytoSC(fid, new Facility_Badminton(fid));
                    else if (fid.charAt(2) == 'A')
                        temp.addFacilitytoSC(fid, new Facility_ActivityRoom(fid));
                    else if (fid.charAt(2) == 'T')
                        temp.addFacilitytoSC(fid, new Facility_TableTennis(fid));
                }
                sportCentreList.put(temp.getScId(), temp);
                if(SimulationMode.getSimulationMdoe())
                System.out.println("Added " + temp.getScId());
            }
        }
        catch (FileNotFoundException fe){
	        throw new ExSCFilesNotExist();
        }
	    catch (IOException ioe){
	        throw new ExIOErrorinGetConfig();
        }
	}
	//export all time scheudle
	public void exportAllSchedule() throws ExIOErrorinGetConfig{
		String tsfp;
		try {
			tsfp = UtilsLoadconfig.getConfig("timeScheduleFilePath");
			for(String sckey: sportCentreList.keySet()) {			//loop each Sport Centre in Sport Centre list
			SportCentre tempsc=sportCentreList.get(sckey);
			UtilsExport.printToFile(tsfp+tempsc.getScId()+".txt","");

			for(String fackey:tempsc.getKeySet()) {							//loop each Facilities in Facility list
					Facility tempfac = tempsc.getFacilitiesMap().get(fackey);
					if(!tempfac.getTimetableMap().isEmpty())
					{
					UtilsExport.appendToFile(tsfp+tempsc.getScId()+".txt", tempfac.getFacilityId());

					for(Integer timekey: tempfac.getkeyset()) {								//loop each timeslot
						UtilsExport.appendToFile(tsfp+tempsc.getScId()+".txt",timekey.toString());
						UtilsExport.appendToFile(tsfp+tempsc.getScId()+".txt",tempfac.getBookingIdbyTime(timekey));
					}
			}
				}
			}
		}
			catch (IOException e) {
					throw new ExIOErrorinGetConfig();
				}
	}

	public void importAllSchedule() throws ExIOErrorinGetConfig, ExFacilityIdNotExist {
		try {
		File file = new File(UtilsLoadconfig.getConfig("timeScheduleFilePath"));
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.equals(".DS_Store");
            }
        });
        for (File f : files) {
            Scanner inFile = new Scanner(f);
            SportCentre sc = Controller.getInstance().getSportCentrebyID(f.getName().substring(0,2));
            if(SimulationMode.getSimulationMdoe())
            System.out.println("Inside "+f.getName().substring(0,2));
            String fcid = inFile.next();
            if(SimulationMode.getSimulationMdoe())
            System.out.println("Adding "+fcid);
            Facility fc = sc.findFacilityByID(fcid);
            System.out.println(fcid);
            while(inFile.hasNext()) {
            int time = inFile.nextInt();
            String bkid = inFile.next();
            if(SimulationMode.getSimulationMdoe())
            System.out.println("time = "+time+", bkid: "+bkid);
            fc.addToTimeTable(time,bkid);
        }
        }
		}
		catch (IOException e) {
			throw new ExIOErrorinGetConfig();
		}

	}
}
