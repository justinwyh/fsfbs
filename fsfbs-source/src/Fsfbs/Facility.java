package Fsfbs;

import java.util.HashMap;
import java.util.Map;

public abstract class Facility {
        private String facilityId; //court num can be consist of district, sport centre and facility number
        private int facilityNum;
        private String facilityType;
        private Map<Integer, String> timetableMap; // time, bookingID

        public Facility(String facilityId) {
            this.facilityId = facilityId;
            this.facilityNum = Integer.parseInt(facilityId.substring(3));
            timetableMap = new HashMap<>();
        }

    public String getFacilityId() {
        return facilityId;
    }

    public int getFacilityNum() {
        return facilityNum;
    }

    public void addToTimeTable(int time, String bookingID) {   	
    	timetableMap.putIfAbsent(time, bookingID);
    }
    
    public void removeFromTimeTable(int time) {
    	timetableMap.remove(time);
    }

    public abstract double getPrice();

    public abstract String getFacilityType();
    
    public boolean canBook(int timeslot) {
    	for (Integer key : timetableMap.keySet()) {
            if(key==timeslot)
            	return false;
        }
    	return true;
    }
}
