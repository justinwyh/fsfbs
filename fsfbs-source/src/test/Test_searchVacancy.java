package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Fsfbs.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class Test_searchVacancy {

	@BeforeEach
	public void setUp() throws Exception {
		Controller controller = Controller.getInstance();
		controller.importData();
	}

	@Test
	public void test_searchVacancy()  {
		setOutput();
		User tester = new User("Mr C", "password", "Membership_Adult", "E1", "Facility_Badminton");
		tester.searchVacancies("E1", "badminton");
		String output = "\n" + "Sport Centre: Java Road Sports Centre\n" + 
				"\n" + 
				"Facility Code: E1B1\n" + 
				"1011 : Booked, Booking ID: 0001\n" + 
				"1112 : Booked, Booking ID: 0001\n" + 
				"1213 : Booked, Booking ID: 0001\n" + 
				"1314 : Available\n" + 
				"1415 : Booked, Booking ID: 0001\n" + 
				"1516 : Booked, Booking ID: 0001\n" + 
				"1617 : Booked, Booking ID: 0001\n" + 
				"1718 : Booked, Booking ID: 0002\n" + 
				"1819 : Booked, Booking ID: 0001\n" + 
				"1920 : Booked, Booking ID: 0002\n" + 
				"2021 : Booked, Booking ID: 0002\n" + 
				"2122 : Booked, Booking ID: 0002\n" + 
				"2223 : Booked, Booking ID: 0003\n" + 
				"2324 : Booked, Booking ID: 0004\n" + 
				"\n" + 
				"Facility Code: E1B2\n" + 
				"1011 : Available\n" + 
				"1112 : Available\n" + 
				"1213 : Available\n" + 
				"1314 : Available\n" + 
				"1415 : Available\n" + 
				"1516 : Available\n" + 
				"1617 : Available\n" + 
				"1718 : Available\n" + 
				"1819 : Available\n" + 
				"1920 : Available\n" + 
				"2021 : Available\n" + 
				"2122 : Available\n" + 
				"2223 : Available\n" + 
				"2324 : Available\n\n";
		assertEquals(output, getOutput());
	}
	
	/* ---------------- No editing below ----------------*/
	
	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;

	private void setOutput()  {
		oldPrintStream = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
	}

	private String getOutput() { // throws Exception
		System.setOut(oldPrintStream);
		return bos.toString();
	}
}
