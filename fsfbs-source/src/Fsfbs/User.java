package Fsfbs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class User {
private String userName;
private String userPassword;
private Membership  membership= null;
private String preferSportCentre = null;
private String preferFacilities = null;
private ArrayList<Booking> todayBooking = new ArrayList<>();
public User() {

}

public User(String userName,String userPassword, String mem, String preferSportCentre, String preferFacilities) {
	this.userName=userName;
	this.userPassword=userPassword;
	
	if(mem.equals("Membership_Adult"))
			this.membership=Membership_Adult.getInstance();
	else if(mem.equals("Membership_Student"))
			this.membership=Membership_Student.getInstance();
	else if(mem.equals("Membership_Senior"))
		this.membership=Membership_Senior.getInstance();
	
	this.preferSportCentre=preferSportCentre;
	this.preferFacilities=preferFacilities;
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
		if(UtilValidation.getValidationInstance().existedAC(username))
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
	String[] temp = new String[5];
	int age;
	String password;
	String sc;
	String type;

	//User account set up
	System.out.println("Please enter your preferred userName");
	String ac = in.nextLine();
	while(UtilValidation.getValidationInstance().existedAC(ac))
	{
		System.out.println("Account already exist. Please input again.");
		ac=in.nextLine();
	}
	this.setUserName(ac);


	//User password
	password = this.validatePassword();

	//set membership
	this.setMembership(this.getMembershipbyAge());
	
	//set SportCentre
	Controller.getInstance().printAllFacilities();
		while(true) {
		System.out.println("Please enter your prefer Sport Centre");
		sc=in.next();
		if(UtilValidation.getValidationInstance().validateSportCentreById(sc))
		{
			this.setPreferSportCentre(sc);
			break;
		}
	}
	
	//set Facilities
	while(true) {
		System.out.println("Please enter your prefer Facilities");
		System.out.println("B: Badminton, A: ActivityRoom, T:TableTennis");
		type = in.next();
		if(type.equals("B"))
		{
			this.setPreferFacilities("Facilities_Badmintion");
			break;
		}
		else if(type.equals("A"))
		{
			this.setPreferFacilities("Facilities_ActivityRoom");
			break;
		}
		else if(type.equals("T"))
		{
			this.setPreferFacilities("Facilities_TableTennis");
			break;
		}
		break;
	}
	
	//User Profile setup
	temp[0]=ac;
	temp[1]=password;
	temp[2]=this.membership.getClass().getSimpleName();
	temp[3]=sc;
	temp[4]=type;
	
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
public String getPreferSportCentre() {
	return preferSportCentre;
}

public void setPreferSportCentre(String preferSportCentre) {
	this.preferSportCentre = preferSportCentre;
}

public String getPreferFacilities() {
	return preferFacilities;
}

public void setPreferFacilities(String preferFacilities) {
	this.preferFacilities = preferFacilities;
}

//-----------------validate User Pw-------------------
private String validatePassword() {
	Scanner in = new Scanner(System.in);
	String password;
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
		else System.out.println("Your password does not match with what you previously entered. Please enter again!");
		}
		return password;
}
//-----------------validate User Age-------------------

private Membership getMembershipbyAge() {
	Scanner in = new Scanner(System.in);
	int age;
	while(true) {
	System.out.println("Please enter your age");
	age = in.nextInt();
	if(age > 0)
	{
		if(age <= 18)
			return (Membership_Student.getInstance());
			else if(age <= 60)
				return (Membership_Adult.getInstance());
					else if(age > 60)
						return (Membership_Senior.getInstance());
		break;
	}
	else
		System.out.println("Invalid input entered. Please enter a number.");
	}
	return membership;
}


private void addBooking(String inputSCId, String inputFacilitiesId, int time) throws ExSportCentreNotExist, ExFacilityIdNotExist, ExFullBooking{
    //Step 1: Validate Input
    SportCentre sc;
    Facility facility;
     sc = getSportCentreById(inputSCId);
    if (sc == null){
        throw new ExSportCentreNotExist();
    }
    else{
        facility = getFacility(sc,inputFacilitiesId);
        if (facility == null){
            throw new ExFacilityIdNotExist();
        }
    }
    //Step 2: check if user exceed the maximum booking limit
    boolean todayBkBelow3 = todayBooking.size()<3;
    //Step 3:
    if(todayBkBelow3) {
        boolean canBook = facility.canBook(time);
        if (canBook) {
            Booking booking = new Booking(userName, time, facility.getFacilityId());
            facility.addToTimeTable(time, booking.getBookingID());
        }
        else {
            throw new ExFullBooking(sc.getScName(),facility.getFacilityType(),time);
        }
    }

    /*sc.addFacilitiestoSC(f.getFacilityId(), f);
	//check booking user < 3
	boolean todayBkBelow3 = todayBooking.size()<3;
	if(todayBkBelow3) {
		 boolean canBook = f.canBook(t);
		 Booking booking = new Booking(userName, t, f.getFacilityId());
		 f.addToTimeTable(t, booking.getBookingID());
	}*/

}

private SportCentre getSportCentreById(String inputSCId){
    Controller controller =  Controller.getInstance();
    return controller.getSportCentrebyID(inputSCId);
}

private Facility getFacility(SportCentre sc, String inputFacilitiesId){
    Controller controller =  Controller.getInstance();
    return controller.getFacility(sc,inputFacilitiesId);
}

}
