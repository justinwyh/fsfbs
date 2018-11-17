package Fsfbs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class User {
private String userName;
private String userID;
private String userPassword;
private Membership  membership= null;
//private SportCenter preferSportCenter;
//private ArrayList<Booking> bookingHistory

public User() {
}

public static void Login() throws NullPointerException, IOException {
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
			break;
		}
		else
			System.out.println("Invalid password. Please enter again.");
		}	
	
}

public void setUpAC() throws IOException {
	Scanner in = new Scanner(System.in);	
	
	//User account set up
	System.out.println("Please enter your preferred userID");	
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
	String password = in.next();
	System.out.println("Please enter your password again");
	if(password.equals(in.next()))
	{
		this.setUserPassword(in.next());
		break;
	}
	}
}


//getter setter
public String getUserName() {
	return userName;
}

private void setUserName(String userName) throws IOException {
	this.userName = userName;
}

public String getUserID() {
	return userID;
}

private void setUserID(String userID) {
	this.userID = userID;
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
//validate account
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
}
