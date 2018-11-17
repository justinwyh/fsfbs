package Fsfbs;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main {
 public static void main(String[] args) throws IOException{
     //Step 1: Scan all csv including user.csv, sport_facilities.csv, booking.csv
     // for each courts on the current day and all booking history of users automatically.

     //Step 2:User Login
	 User.Login();

     //Step 3: Show the recommendation algorithm and all functions available for users (or admin)

	 User user = new User();
	 user.setUpAC();
 }
}
