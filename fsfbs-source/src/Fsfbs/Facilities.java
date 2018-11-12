package Fsfbs;

import java.util.ArrayList;

public abstract class Facilities {
        private int facilityId; //court num can be consist of district, sport centre and facility number
        private int facilityNum;
        private ArrayList<Booking> facilityBookingList;

        public Facilities(int facilityId, int facilityNum) {
            this.facilityId = facilityId;
            this.facilityNum = facilityNum;
            facilityBookingList = new ArrayList<>();
        }

    public int getFacilityId() {
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
