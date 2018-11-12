package Fsfbs;

import java.util.ArrayList;

public abstract class Facilities {
        private String facilityId; //court num can be consist of district, sport centre and facility number
        private int facilityNum;
        private ArrayList<Booking> facilityBookingList;

        public Facilities(String facilityId) {
            this.facilityId = facilityId;
            this.facilityNum = Integer.parseInt(facilityId.substring(3));
            facilityBookingList = new ArrayList<>();
        }

    public String getFacilityId() {
        return facilityId;
    }

    public int getFacilityNum() {
        return facilityNum;
    }

    public void setTodayBookingList(ArrayList<Booking> bookingArrayList){
        facilityBookingList = bookingArrayList;
    }

    public abstract double getPrice();
}
