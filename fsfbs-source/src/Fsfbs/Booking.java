package Fsfbs;

public class Booking {
	
	private String userID;
	private String bookingID;
	private int bookingTime;
	private String facilitiesID;
	

	public Booking (String userID, int bookingTime, String facilitiesID){

	    this.userID = userID;
	    this.bookingID = createBookingID(bookingID,facilitiesID);
	    this.bookingTime = bookingTime;
	    this.facilitiesID = facilitiesID;
    }

    private String createBookingID(String bookingID, String facilitiesID){
	    return (bookingID + facilitiesID);
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
