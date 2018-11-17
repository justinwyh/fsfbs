package Fsfbs;

public class Facilities_Badminton extends Facilities {

    public Facilities_Badminton(int facilityId, int facilityNum){
        super(facilityId,facilityNum);
    }

    @Override
    public double getPrice() {
        return 59;
    }
}
