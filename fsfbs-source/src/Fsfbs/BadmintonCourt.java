package Fsfbs;

public class BadmintonCourt extends Facilities {

    public BadmintonCourt(int facilityId, int facilityNum){
        super(facilityId,facilityNum);
    }

    @Override
    public double getPrice() {
        return 59;
    }
}
