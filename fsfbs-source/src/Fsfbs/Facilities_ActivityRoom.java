package Fsfbs;

public class Facilities_ActivityRoom extends Facilities {

    public Facilities_ActivityRoom(int facilityId, int facilityNum){
        super(facilityId,facilityNum);
    }

    @Override
    public double getPrice() {
        return 99;
    }
}
