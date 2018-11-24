package Fsfbs;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
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
    
    public boolean canBook(int timeslot) throws ExTimeRangeNotCurrent, ExAllowToBookOneHourOnly,ExInputTimeEarlierThanCurrentTime, ExTimeSlotNotInOpeningHour {
        UtilTime utilTime = UtilTime.getTimeInstance();
        int timeRangeResult = utilTime.isTimeRangeExceed(timeslot);
            switch (timeRangeResult) {
                case -1:
                    throw new ExTimeRangeNotCurrent();
                case -2:
                    throw new ExAllowToBookOneHourOnly();
                case 0:
                    break;
            }

        String startTime = Integer.toString(timeslot).substring(0,2) + ":00:00";
        if (utilTime.isTimeLaterThanCurrentTime(startTime)) {
            for (Integer key : timetableMap.keySet()) {
                if (key == timeslot)
                    return false;
            }
            return true;
        }
        else{
            throw new ExInputTimeEarlierThanCurrentTime();
        }
    }

    public boolean canDelete(int timeslot) throws ExTimeRangeNotCurrent, ExAllowToDeleteOneHourOnly,ExInputTimeEarlierThanCurrentTime, ExTimeSlotNotInOpeningHour {
        UtilTime utilTime = UtilTime.getTimeInstance();
        int timeRangeResult = utilTime.isTimeRangeExceed(timeslot);
        switch (timeRangeResult) {
            case -1:
                throw new ExTimeRangeNotCurrent();
            case -2:
                throw new ExAllowToDeleteOneHourOnly();
            case -3:
                throw new ExTimeSlotNotInOpeningHour();

        } 

        String startTime = Integer.toString(timeslot).substring(0,2) + ":00:00";
        if (utilTime.isTimeLaterThanCurrentTime(startTime)) {
            for (Integer key : timetableMap.keySet()) {
                if (key == timeslot)
                    return false;
            }
            return true;
        }
        else{
            throw new ExInputTimeEarlierThanCurrentTime();
        }
    }

    public String getBookingStatus(int timeslot){
        String bookingID = getBookingIdbyTime(timeslot);
        if (bookingID == null){
            return "Available";
        }
        else {
            return "Booked Booking ID: " + bookingID;
        }
    }


    public void showVaccancies(){
        TreeMap<Integer,String> vaccanciesMap = new TreeMap<>();
        int [] timeRange = {1011,1112,1213,1314,1516,1718,1819,1920,2021,2122,2223,2324};
        for (int i = 0; i < 14; i++){
                vaccanciesMap.putIfAbsent(timeRange[i],getBookingStatus(timeRange[i]));
        }
    }
}
