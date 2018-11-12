package Fsfbs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class User {
<<<<<<< HEAD
private String userName;
private String userID;
private String userPassword;
private Membership  membership= null;
//private District preferDistrict;
//private SportCenter preferSportCenter;
//private ArrayList<Booking> bookingHistory

public User() {
}

public void setUpAC() throws IOException {
	Scanner in = new Scanner(System.in);	
	//setAc
	System.out.println("Please enter your preferred userID");	
	String ac = in.nextLine();
	while(existedAC(ac)==true)
	{
		System.out.println("Account already exist. Please input again.");
		ac=in.nextLine();
	}
	this.setUserName(ac);
	
	//setPw
	this.setUserPassword(in.next());
}
//getter setter
public String getUserName() {
	return userName;
}

public void setUserName(String userName) throws IOException {
	this.userName = userName;
}

public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}

public Membership getMembership() {
	return membership;
}

public void setMembership(Membership membership) {
	this.membership = membership;
}
public String getUserPassword() {
	return userPassword;
}

public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
}
//validate account
private static boolean existedAC(String account) throws IOException {
	 File file = new File(UtilsLoadconfig.getConfig("membershipFilePath"));
     File[] files = file.listFiles();
     for(File f: files){
    	 String filename = f.getName().substring(0,f.getName().length()-4);
    	 if(filename.equals(account))
    		 return true;
     }
	return false;
}
=======
	
>>>>>>> 1574d3a365722868571b7d40286c387436875848
}
