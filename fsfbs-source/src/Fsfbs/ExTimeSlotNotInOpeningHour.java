package Fsfbs;

public class ExTimeSlotNotInOpeningHour extends Exception {
    public ExTimeSlotNotInOpeningHour(){
        super("The opening hour is from 10am to 12am. Please enter another time slot.");
    }
}
