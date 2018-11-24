package Fsfbs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SportCentre implements SCAdminInterface, SCUserInterface{
    public String getScId() {
		return scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public String getScTel() {
		return scTel;
	}

	public void setScTel(String scTel) {
		this.scTel = scTel;
	}

	public String getScAddress() {
		return scAddress;
	}

	public void setScAddress(String scAddress) {
		this.scAddress = scAddress;
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

    public void addFacilitytoSC(String facilitiesId, Facility facility) {
        facilitiesMap.putIfAbsent(facilitiesId, facility);
    }

    public void removeFacilityFromSC(String facilityId){
        facilitiesMap.remove(facilityId);
    }


    public Facility findFacilityByID(String facilityId) throws ExFacilityIdNotExist{
        Facility facility = facilitiesMap.get(facilityId);
        if (facility == null){
            throw new ExFacilityIdNotExist();
        }
        return facility;
    }

}
