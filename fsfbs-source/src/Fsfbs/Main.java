package Fsfbs;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
 public static void main(String[] args)
 {
     try {
         Controller controller = Controller.getInstance();
         //Step 1: Import Data
         controller.importData();
         System.out.println("+-------------------------------------------------------------------+");
         System.out.println("||  +      + +----+ +      +-----+ +-----+  +-+  +-+  +----+   ++  ||");
         System.out.println("||  |      | |      |      |       |     |  | +--+ |  |        ||  ||");
         System.out.println("||  |  +   | +---+  |      |       |     |  |  ++  |  +---+    ++  ||");
         System.out.println("||  |  |   | |      |      |       |     |  |      |  |            ||");
         System.out.println("||  +--+---+ +----+ +----+ +-----+ +-----+  +      +  +----+   ++  ||");
         System.out.println("||        Welcome to Fast Sports Facility Booking System!          ||");
         System.out.println("+-------------------------------------------------------------------+");
         System.out.println("=================Do you have an User Account? (Y/N)==================");

         //Step 2: Choose either login or create user
        loginOrCreateUser();
        User.Login();
        // User user = Controller.getInstance().getUserbyID("Ada");
        // user.importBooking();
        // user.addBooking("E1A1", 1112);
     }
     catch (Exception e){
         System.out.println(e.getMessage());
     }
     finally {
         //Last Step: export to txt file and end the program.
     }
 }

 public static void loginOrCreateUser() {
     String userinput;
     while (true) {
         Scanner in = new Scanner(System.in);
         userinput = in.next();
         //Step 2: Log In or Create User
         if (userinput.equals("Y")) {
             return;
         } else if (userinput.equals("N")) {
             User.setUpAC(); //setupAccount
             return;
         } else {
             System.out.println("Invalid input. Input should be Y or N. Please input again.");
         }
     }
 }
}
