package Fsfbs;

public class Facilities_ActivityRoom extends Facilities {

    public Facilities_ActivityRoom(String facilityId){
        super(facilityId);
    }

    @Override
    public double getPrice() {
        return 99;
    }
}
