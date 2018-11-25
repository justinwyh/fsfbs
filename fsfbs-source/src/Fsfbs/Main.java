package Fsfbs;
import java.io.IOException;
import java.util.Scanner;
public class Main {
 public static void main(String[] args) throws IOException, ExIOErrorinGetConfig
 {
     try {
         Controller controller = Controller.getInstance();
         //Step 1: Import Data
         controller.importData();
         Scanner in = new Scanner(System.in);
         
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
         while(true)
         {
             System.out.println("+-------------------------------------------------------------------+");
             System.out.println("|---------------------------User Guide------------------------------|");
             System.out.println("|                   Add booking: please enter 'add'                 |");
             System.out.println("|                Delete booking: please enter 'delete'              |");
             System.out.println("|           Print all sport centre: please enter 'print'            |");
             System.out.println("|             Search vacancies: please enter 'vacancy'             |");
             System.out.println("|             Print my booking: please enter 'mybooking'            |");
             System.out.println("|                     exit: please enter 'exit'                     |");
             System.out.println("+-------------------------------------------------------------------+");
             String input = in.next();
             
        	 if(input.equals("vacancy")) {     		 
        		 controller.printAllFacilities();
        		 System.out.println("Please enter the key of Sport Centre");
        		 String sportfacility= in.next();
        		 while(controller.searchSportCentre(sportfacility)==null) {
        			 System.out.println("Wrong key entered. Please try again");
        		 }
        		 System.out.println("Please enter the type of court you want to book");
        		 System.out.println("e.g. baminton, tableTennis, acticityRoom");
        		 String court=null;
        		 court = in.next();
        		 while(!(court.equals("badminton")||court.equals("tableTennis")||court.equals("activityRoom"))) {
        			 System.out.println("Wrong court information entered.");
        		 	 System.out.println("Please enter: badminton, tableTennis, acticityRoom");
        		 	 court=in.next();
        		 }
        		 user.searchVacancies(sportfacility, court);
        	 }
        	 
        	 else if(input.equals("add")) {
        		 System.out.println("Please enter the facility code(e.g. E1B1, E1B2)");
        		 String sportfacility= in.next();
        		 System.out.println("Please enter time range");
        		 int time= in.nextInt();
        		 user.addBooking(sportfacility, time);      		      		 
        	 }
        	 
        	 else if(input.equals("print"))
        		 controller.printAllFacilities();
        	 else if(input.equals("mybooking"))
        		 user.printTodayBookingHistory();
        	 else if(input.equals("delete")){
        		 System.out.println("Please enter the booking ID you want to delete");
        		 user.printTodayBookingHistory();
        		 user.deleteBooking(in.next());
        	 }
        	 else if(input.equals("exit"))
        		 break;
        	
         }
         
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
