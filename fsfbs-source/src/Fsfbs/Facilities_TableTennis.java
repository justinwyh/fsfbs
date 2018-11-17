package Fsfbs;

public class Facilities_TableTennis extends Facilities {

    public Facilities_TableTennis(String courtId){
        super(courtId);
    }

    @Override
    public double getPrice() {
        return 29;
    }
}
