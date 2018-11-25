package Fsfbs;
import java.io.IOException;
import java.util.Scanner;
public class Main {
 public static void main(String[] args) throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, IOException
 {
     try {
         Controller controller = Controller.getInstance();
         //Step 1: Import Data
         controller.importData();
         
         //welcome message
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
         User user = User.Login();
         user.searchVacancies("E1","badminton");
     }
     catch (Exception e){
         System.out.println(e.getMessage());
     }
     finally {
    	 Controller controller = Controller.getInstance();
         //Last Step: export to txt file and end the program.
    	 if(!SimulationMode.getSimulationMdoe())
    		 controller.exportAllSchedule();
 
     }

	 //Controller.getInstance().importData();
	 //Controller.getInstance().exportAllSchedule();
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
