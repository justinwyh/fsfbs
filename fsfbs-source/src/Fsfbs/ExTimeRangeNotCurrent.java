package Fsfbs;

public class ExTimeRangeNotCurrent extends Exception {
    public ExTimeRangeNotCurrent(){
        super("The input time range is not correct. Please input again.");
    }
}
