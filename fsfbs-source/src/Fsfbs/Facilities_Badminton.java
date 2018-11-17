package Fsfbs;

public class Facilities_Badminton extends Facilities {

    public Facilities_Badminton(String facilityId){
        super(facilityId);
    }

    @Override
    public double getPrice() {
        return 59;
    }
}
