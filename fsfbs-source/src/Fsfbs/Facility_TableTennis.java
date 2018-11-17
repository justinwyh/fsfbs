package Fsfbs;

public class Facility_TableTennis extends Facility {

    public Facility_TableTennis(String courtId){
        super(courtId);
    }

    @Override
    public double getPrice() {
        return 29;
    }
}
