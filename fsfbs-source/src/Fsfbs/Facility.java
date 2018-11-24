package Fsfbs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public String getBookingIdbyTime(Integer time) {
    	return timetableMap.get(time);
    }

    public Set<Integer> getkeyset() {
    	return timetableMap.keySet();
    }
    public Map<Integer, String> getTimetableMap() {
		return timetableMap;
	}

	public void setTimetableMap(Map<Integer, String> timetableMap) {
		this.timetableMap = timetableMap;
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
    public void exportTimeschedule() {
    	
    }
}
