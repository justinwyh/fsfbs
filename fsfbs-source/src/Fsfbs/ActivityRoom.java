package Fsfbs;

public class ActivityRoom extends Facilities {

    public ActivityRoom(int facilityId, int facilityNum){
        super(facilityId,facilityNum);
    }

    @Override
    public double getPrice() {
        return 99;
    }
}
