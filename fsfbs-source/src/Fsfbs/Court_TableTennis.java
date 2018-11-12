package Fsfbs;

public class Court_TableTennis extends Facilities {

    public Court_TableTennis(int courtId, int courtNum){
        super(courtId,courtNum);
    }

    @Override
    public double getPrice() {
        return 29;
    }
}
