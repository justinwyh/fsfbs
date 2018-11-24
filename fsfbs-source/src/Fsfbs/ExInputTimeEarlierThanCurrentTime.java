package Fsfbs;

public class ExInputTimeEarlierThanCurrentTime extends Exception {
    public ExInputTimeEarlierThanCurrentTime(){
        super("Operation cannot be done. \nYour input time has passed. Please enter a time slot later than the current time.");
    }

}
