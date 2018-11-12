package Fsfbs;

public class Court_ActivityRoom extends Facilities {

    public Court_ActivityRoom(int facilityId, int facilityNum){
        super(facilityId,facilityNum);
    }

    @Override
    public double getPrice() {
        return 99;
    }
}
