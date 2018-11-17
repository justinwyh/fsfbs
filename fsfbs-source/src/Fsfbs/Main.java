package Fsfbs;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main {
 public static void main(String[] args) throws IOException{
	 //variable
	 String userinput;
	 User user = new User();
	 
	 
     //Step 1: Scan all csv including user.csv, sport_facilities.csv, booking.csv
	 
     // for each courts on the current day and all booking history of users automatically.
	 
	 Scanner in = new Scanner(System.in);
	 System.out.println("<=============Welcome to our Fast Sports Facility Booking System!=============>");
	 System.out.print("Do you have an User Account? (Y/N)");
	 userinput = in.next();
	 while(true) {
		if(userinput.equals("Y"))
			//Step 2:User Login		
		{
			user = Controller.getInstance().getUserbyID(User.Login());
			break;
		}
		 else if(userinput.equals("N"))
		 {
			user.setUpAC();
			break;
		 }
		 else 
			 System.out.println("Invalid input. Input should be Y or N");
	 }
     

     //Step 3: Show the recommendation algorithm and all functions available for users (or admin)
 }
}
