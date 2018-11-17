package Fsfbs;

import java.util.HashMap;
import java.util.Map;

public class SportCentre implements SCAdminInterface {
    private String scId;
    private String scName;
    private String scTel;
    private String scAddress;
    private Map<String,Facilities> facilitiesMap;


    public SportCentre(String scId, String scName, String scTel, String scAddress){
        this.scId = scId;
        this.scName = scName;
        this.scTel = scTel;
        this.scAddress = scAddress;
        facilitiesMap = new HashMap<>();
    }

    public void addFacilitiestoSC(String facilitiesId,Facilities facilities) {
        facilitiesMap.putIfAbsent(facilitiesId,facilities);
    }

    public void removeFacilitiesFromSC(String facilitiesId){
        facilitiesMap.remove(facilitiesId);
    }
}