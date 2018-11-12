package Fsfbs;

public class TableTennisCourt extends Facilities {

    public TableTennisCourt(int courtId, int courtNum){
        super(courtId,courtNum);
    }

    @Override
    public double getPrice() {
        return 29;
    }
}
