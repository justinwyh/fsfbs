package Fsfbs;

public class Facilities_TableTennis extends Facilities {

    public Facilities_TableTennis(int courtId, int courtNum){
        super(courtId,courtNum);
    }

    @Override
    public double getPrice() {
        return 29;
    }
}
