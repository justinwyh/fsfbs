package Fsfbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SportCentre {
    public String getScId() {
		return scId;
	}

	public String getScName() {
		return scName;
	}

	public String getScTel() {
		return scTel;
	}

	public String getScAddress() {
		return scAddress;
	}

	private String scId;
    private String scName;
    private String scTel;
    private String scAddress;
    private Map<String, Facility> facilitiesMap;		//facility id, Facility Obj


    public Map<String, Facility> getFacilitiesMap() {
		return facilitiesMap;
	}
    public Set<String> getKeySet() {
    	return facilitiesMap.keySet();
    }

	public SportCentre(String scId, String scName, String scTel, String scAddress){
        this.scId = scId;
        this.scName = scName;
        this.scTel = scTel;
        this.scAddress = scAddress;
        facilitiesMap = new HashMap<>();
    }

/*    public ArrayList<Facility> searchVacanciesInSC(UtilTime utilTime){
        String time = utilTime.getCurrentTime();
    }*/

    public Facility findFacilityByID(String facilityId) throws ExFacilityIdNotExist{
        Facility facility = facilitiesMap.get(facilityId);
        if (facility == null){
            throw new ExFacilityIdNotExist();
        }
        return facility;
    }

	public void addfacilitytosc(String facilitiesId, Facility facility) {
		facilitiesMap.putIfAbsent(facilitiesId, facility);
	}
}
