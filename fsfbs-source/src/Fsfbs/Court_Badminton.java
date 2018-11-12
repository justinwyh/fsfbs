package Fsfbs;

public class Court_Badminton extends Facilities {

    public Court_Badminton(int facilityId, int facilityNum){
        super(facilityId,facilityNum);
    }

    @Override
    public double getPrice() {
        return 59;
    }
}
