package Fsfbs;

import java.util.HashMap;
import java.util.Map;

public class SportCentre implements SCAdminInterface/*, SCUserInterface*/{
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
    private Map<String, Facility> facilitiesMap;


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

    public Facility getFacilityByID(String facilityId) {
        facilitiesMap.get(facilityId);
    }
}
