package Fsfbs;

import java.util.HashMap;
import java.util.Map;

public abstract class Facilities {
        private String facilityId; //court num can be consist of district, sport centre and facility number
        private int facilityNum;
        private Map<Integer, String> timetableMap; // time, bookingID

        public Facilities(String facilityId) {
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
    
    public boolean canBook(int t) {
    	for (Integer key : timetableMap.keySet()) {
            if(key==t)
            	return false;
        }
    	return true;
    }
}
