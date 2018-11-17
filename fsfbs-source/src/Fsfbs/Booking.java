package Fsfbs;

public class Booking {

	private String userID;
	private String bookingID;
	private int bookingTime;
	private String facilitiesID;

	public Booking (String userID, int bookingTime, String facilitiesID){

	    this.userID = userID;
	    this.bookingID = createBookingID(bookingTime,facilitiesID);
	    this.bookingTime = bookingTime;
	    this.facilitiesID = facilitiesID;
    }

    private String createBookingID(int bookingTime, String facilitiesID){
	    UtilTime utilTime = UtilTime.getTimeInstance();
	    return (utilTime.getCurrentDate() + facilitiesID + bookingTime);
    }


    public String getBookingID() {
        return bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public String getFacilitiesID() {
        return facilitiesID;
    }

    public int getBookingTime() {
        return bookingTime;
    }
}
