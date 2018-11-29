package Exception;

public class ExTimeRangeNotCorrect extends Exception {
    public ExTimeRangeNotCorrect(){
        super("The input time range is not correct. Please input again.");
    }
}
