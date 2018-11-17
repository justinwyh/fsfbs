package Fsfbs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class User {
private String userName;
private String userPassword;
private Membership  membership= null;
//private SportCenter preferSportCenter;
private ArrayList<Booking> todayBooking = new ArrayList<>();

public User() {

}

//login----------------------------------------------------------------------------------------
public static String Login() throws NullPointerException, IOException {
	//variable initialization
	String username,password;
	Scanner in = new Scanner(System.in); //user input

	//login procedure
	while(true) {
		System.out.println("Please enter user name:");
		username = in.next();
		if(existedAC(username))
			break;
		else
			System.out.println("Invalid username. Please enter again :");

		}
	//get Password
	//read file
			String filepath = UtilsLoadconfig.getConfig("membershipFilePath")+username+".txt";
			File file = new File(filepath);
			Scanner inFile = new Scanner(file);
			String ac = inFile.next();
			String correctpass = inFile.next();

	while(true) {
		//user input
		System.out.println("Your username: "+ac);
		System.out.println("Please enter password:");
		password = in.next();
		if(password.equals(correctpass))
		{
			System.out.println("Login Success.");
			return ac;
		}
		else
			System.out.println("Invalid password. Please enter again.");
		}

}

//Setup Account ----------------------------------------------------------------------------------------
public void setUpAC() throws IOException {
	Scanner in = new Scanner(System.in);
	String[] temp = new String[4];
	int age;
	String password;

	//User account set up
	System.out.println("Please enter your preferred userName");
	String ac = in.nextLine();
	while(existedAC(ac))
	{
		System.out.println("Account already exist. Please input again.");
		ac=in.nextLine();
	}
	this.setUserName(ac);

	//User password set up
	while(true) {
	System.out.println("Please enter your password");
	password = in.next();
	System.out.println("Please enter your password again");
	if(password.equals(in.next()))
	{
		this.setUserPassword(password);
		break;
	}
	}
	
	//User age setup
	while(true) {
	System.out.println("Please enter your age");
	age = in.nextInt();
	if(age > 0)
	{
		if(age <= 18)
			this.setMembership(Membership_Student.getInstance());
			else if(age <= 60)
				this.setMembership(Membership_Adult.getInstance());
					else if(age > 60)
						this.setMembership(Membership_Senior.getInstance());
		break;
	}
	else
		System.out.println("Invalid input entered. Please enter a number.");
	}
	temp[0]=ac;
	temp[1]=password;
	temp[2]=this.membership.getClass().toString();
	UtilsExport.printToFile(UtilsLoadconfig.getConfig("membershipFilePath")+ac+".txt",temp);
	System.out.println("Create User Success. Log In Success!");
}
//getter and setter...-----------------------------------------------------------------------------------
public String getUserName() {
	return userName;
}

private void setUserName(String userName) throws IOException {
	this.userName = userName;
}


public Membership getMembership() {
	return membership;
}

private void setMembership(Membership membership) {
	this.membership = membership;
}
public String getUserPassword() {
	return userPassword;
}

private void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
}
//validate account ----------------------------------------------------------------------------------------
private static boolean existedAC(String account) throws IOException, NullPointerException {
	 File file = new File(UtilsLoadconfig.getConfig("membershipFilePath"));
     File[] files = file.listFiles();
     for(File f: files){
    	 String filename = f.getName().substring(0,f.getName().length()-4);
    	 if(filename.equals(account))
    		 return true;
     }
	return false;
	}


private void addBooking(String inputSCId, String inputFacilitiesId, int t) {


    /*sc.addFacilitiestoSC(f.getFacilityId(), f);
	//check booking user < 3
	boolean todayBkBelow3 = todayBooking.size()<3;
	if(todayBkBelow3) {
		 boolean canBook = f.canBook(t);
		 Booking booking = new Booking(userName, t, f.getFacilityId());
		 f.addToTimeTable(t, booking.getBookingID());
	}*/

}





}
