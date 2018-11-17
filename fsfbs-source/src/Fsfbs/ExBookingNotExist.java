package Fsfbs;

public class ExBookingNotExist extends Exception {
    public ExBookingNotExist () {
        super("Booking ID does not exist! Cannot delete!");
    }
    public ExBookingNotExist(String Message){
        super(Message);
    }
}
