package Fsfbs;

public class ExTimeRangeNotCurrent extends Exception {
    public ExTimeRangeNotCurrent(){
        super("The inputted time range does not correct. Please input again.");
    }
}
