package Fsfbs;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
 public static void main(String[] args) throws IOException, ExFullBooking{
	 Controller.getInstance();
	 //variable
	 /*
	 String userinput;
	 User user = new User();


     //Step 1: Scan all csv including user.csv, sport_facilities.csv, booking.csv

     // for each courts on the current day and all booking history of users automatically.

	 Scanner in = new Scanner(System.in);

	 System.out.println("+-------------------------------------------------------------------+");
	 System.out.println("|-------------------------------------------------------------------|");
	 System.out.println("||                                                                 ||");
	 System.out.println("||                                                                 ||");
	 System.out.println("||  +      + +----+ +      +-----+ +-----+  +-+  +-+  +----+   ++  ||");
	 System.out.println("||  |      | |      |      |       |     |  | +--+ |  |        ||  ||");
	 System.out.println("||  |  +   | +---+  |      |       |     |  |  ++  |  +---+    ++  ||");
	 System.out.println("||  |  |   | |      |      |       |     |  |      |  |            ||");
	 System.out.println("||  +--+---+ +----+ +----+ +-----+ +-----+  +      +  +----+   ++  ||");
	 System.out.println("||                                                                 ||");
	 System.out.println("||        Welcome to Fast Sports Facility Booking System!          ||");
	 System.out.println("|-------------------------------------------------------------------|");
	 System.out.println("+-------------------------------------------------------------------+");
	 System.out.println("=================Do you have an User Account? (Y/N)==================");
	 userinput = in.next();
	 while(true) {
	 	try {
			if (userinput.equals("Y"))
			//Step 2:User Login
			{
				user = Controller.getInstance().getUserbyID(User.Login());
				break;
			} else if (userinput.equals("N")) {
				user.setUpAC();
				break;
			} else
				System.out.println("Invalid input. Input should be Y or N");
		}
		catch(Exception e){
	 		System.out.println(e.getMessage());
		}
	 }


     //Step 3: Show the recommendation algorithm and all functions available for users (or admin)
      * */

	 User user =Controller.getInstance().getUserbyID("Ada");
	 user.importBooking();
	 user.addBooking("E1A1", 1112);

 }
}
