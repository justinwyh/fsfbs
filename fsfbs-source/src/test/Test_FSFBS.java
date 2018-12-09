package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Facility.*;
import Fsfbs.Booking;
import Fsfbs.Controller;
import Fsfbs.FastRecommendationAlgorithm;
import Fsfbs.SimulationMode;
import Fsfbs.SportCentre;
import Fsfbs.User;
import Membership.*;
import Util.UtilTime;
import Util.UtilValidation;
import Util.UtilsLoadconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import Exception.*;


public class Test_FSFBS {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @AfterClass
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }

    //UtilValidation

    @BeforeEach
    public void setUp() throws ExFacilityIdNotExist, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig {
        Controller controller = Controller.getInstance();
        controller.importData();
    }

    @Test
    public void test_Ada() throws NullPointerException, IOException {
        UtilValidation export = UtilValidation.getValidationInstance();
        boolean result = export.existedAC("Ada");
        assertEquals(true, result);
    }

    @Test
    public void test_Ken() throws NullPointerException, IOException {
        UtilValidation export = UtilValidation.getValidationInstance();
        boolean result = export.existedAC("Ken");
        assertEquals(true, result);
    }

    @Test
    public void test_Jacky() throws NullPointerException, IOException {
        UtilValidation export = UtilValidation.getValidationInstance();
        boolean result = export.existedAC("Jacky");
        assertEquals(false, result);
    }

    @Test
    public void test_timevalidation_1() {
        UtilValidation valid = UtilValidation.getValidationInstance();
        assertEquals(1314, valid.validateTimeFormat("1314"));
    }

    @Test
    public void test_timevalidation_2() {
        UtilValidation valid = UtilValidation.getValidationInstance();
        assertEquals(0, valid.validateTimeFormat("13145"));
    }

    @Test
    public void test_timevalidation_3() {
        UtilValidation valid = UtilValidation.getValidationInstance();
        assertEquals(0, valid.validateTimeFormat("ABB"));
    }

    @Test
    public void test_SimulationMode() throws IOException {
        assertEquals(true, SimulationMode.getSimulationMode());
    }

    //SportCentre

    @Test
    public void test_SportFacilities1() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
        Controller controller = Controller.getInstance();
        controller.importData();
        SportCentre temp = controller.searchSportCentre("E1");
        assertEquals("Java Road Municipal Services Building, 99 Java Road, North Point, Hong Kong", temp.getScAddress());
    }

    @Test
    public void test_SportFacilities2() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
        Controller controller = Controller.getInstance();
        SportCentre temp = controller.searchSportCentre("E1");
        assertEquals("E1", temp.getScId());
    }

    @Test
    public void test_SportFacilities3() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
        Controller controller = Controller.getInstance();
        SportCentre temp = controller.searchSportCentre("E1");
        assertEquals("Java Road Sports Centre", temp.getScName());
    }

    @Test
    public void test_SportFacilities4() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
        Controller controller = Controller.getInstance();
        SportCentre temp = controller.searchSportCentre("E1");
        assertEquals("25169415", temp.getScTel());
    }

    @Test
    public void test_SportFacilities6() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
        Controller controller = Controller.getInstance();
        SportCentre temp = controller.searchSportCentre("E1");

    }

    @Test
    public void test_SportFacilities7() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
        Controller controller = Controller.getInstance();
        SportCentre temp = controller.searchSportCentre("E1");
        try {
            temp.findFacilityByID("F1B2");
        } catch (Exception e) {
            assertEquals("Your inputted facility id does not exist!", e.getMessage());
        }
    }

    @Test
    public void test_getScAddress() {
        SportCentre testsc = new SportCentre("W1", "West Sport Centre", "20001111", "Western District, Hong Kong");
        String result = testsc.getScAddress();
        assertEquals("Western District, Hong Kong", result);
    }

    @Test
    public void test_getkeyset() {
        SportCentre testsc = new SportCentre("W1", "West Sport Centre", "20001111", "Western District, Hong Kong");
        Facility testfacility = new Facility_Badminton("W1B1");
        testsc.addfacilitytosc("W1B1", testfacility);
        Map<String, Facility> expected = new HashMap();
        expected.put("W1B1", testfacility);
        assertTrue(expected.equals(testsc.getFacilitiesMap()));
    }

    @Test
    public void test_SportFacilities5() throws ExFacilityIdNotExist, ExFacilityNameNotExist, ExSportCentreNotExist, ExTimeRangeNotCorrect, ExAllowToBookOneHourOnly, ExTimeSlotNotInOpeningHour, ExInputTimeEarlierThanCurrentTime, IOException, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig {
        UtilTime utilTime = UtilTime.getTimeInstance();
        Controller controller = Controller.getInstance();
        SportCentre temp = controller.searchSportCentre("E1");
        ArrayList<String> list = temp.searchVacanciesInSC(1718, "badminton", utilTime);
        for (String txt : list)
            System.out.println(txt);
    }


    //Facility

    @Test
    public void test_Facility1() {
    	double delta = 0.0000001d;
        Facility fb = new Facility_Badminton("E1B3");
        assertEquals(59,fb.getPrice(),delta);
    }

    @Test
    public void test_Facility2() {
        Facility fb = new Facility_Badminton("E1B3");
        assertEquals("badminton court", fb.getFacilityType());
    }

    @Test
    public void test_Facility3() {
    	double delta = 0.0000001d;
        Facility ar = new Facility_ActivityRoom("E1A3");
        assertEquals(99,ar.getPrice(),delta);
    }

    @Test
    public void test_Facility4() {
        Facility_ActivityRoom ar = new Facility_ActivityRoom("E1A3");
        assertEquals("activity room", ar.getFacilityType());
    }

    @Test
    public void test_Facility5() {
    	double delta = 0.0000001d;
        Facility_TableTennis ar = new Facility_TableTennis("E1T3");
        assertEquals(29,ar.getPrice(),delta);
    }

    @Test
    public void test_Facility6() {
        Facility_TableTennis fb = new Facility_TableTennis("E1T3");
        assertEquals("table tennis court", fb.getFacilityType());
    }

    @Test
    public void test_facility_getFacilityId() {
        Facility testFacility = new Facility_Badminton("A1B1");
        assertEquals("A1B1", testFacility.getFacilityId());
    }

    @Test
    public void test_facility_getBookingIDByTIme() {
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        assertEquals("20180101A1B11112", testFacility.getBookingIdbyTime(1112));
    }

    @Test
    public void test_facility_getkeyset() {
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1213, "20180101A1B11213");
        Set<Integer> result = testFacility.getkeyset();
        Set<Integer> expectedResult = new HashSet<Integer>();
        expectedResult.add(1112);
        expectedResult.add(1213);
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_facility_getTimeTableMap() {
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1213, "20180101A1B11213");
        Map<Integer, String> result = testFacility.getTimetableMap();
        Map<Integer, String> expectedResult = new HashMap<Integer, String>();
        expectedResult.put(1112, "20180101A1B11112");
        expectedResult.put(1213, "20180101A1B11213");
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_facility_removeFromTimeTable() {
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        String result = testFacility.removeFromTimeTable(1112);
        assertEquals("20180101A1B11112", result);
    }

    @Test
    public void test_facility_canBook_1() throws ExTimeRangeNotCorrect,ExAllowToBookOneHourOnly,ExTimeSlotNotInOpeningHour,IOException, ExInputTimeEarlierThanCurrentTime {
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1213, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        try {
            boolean result = testFacility.canBook(1112, utiltime);
        } catch (ExInputTimeEarlierThanCurrentTime e) {
            assertEquals("Your input time has passed. Please enter a time slot later than the current time.", e.getMessage());
        }
    }

    @Test
    public void test_facility_canBook_2()throws ExTimeRangeNotCorrect,ExTimeSlotNotInOpeningHour,IOException, ExInputTimeEarlierThanCurrentTime, ExInputTimeEarlierThanCurrentTime {
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1213, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        try {
            boolean result = testFacility.canBook(1113, utiltime);
        } catch (ExAllowToBookOneHourOnly e) {
            assertEquals("You are allowed to book 1 hour only for each booking. Please input again.", e.getMessage());
        }
    }

    @Test
    public void test_facility_canBook_3() throws ExTimeSlotNotInOpeningHour,IOException, ExInputTimeEarlierThanCurrentTime, ExInputTimeEarlierThanCurrentTime, ExAllowToBookOneHourOnly {
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1213, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        try {
            boolean result = testFacility.canBook(1312, utiltime);
        } catch (ExTimeRangeNotCorrect e) {
            assertEquals("The input time range is not correct. Please input again.", e.getMessage());
        }
    }

    @Test
    public void test_facility_canBook_4() throws ExTimeRangeNotCorrect,IOException, ExInputTimeEarlierThanCurrentTime, ExInputTimeEarlierThanCurrentTime,ExAllowToBookOneHourOnly {
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1213, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        try {
            boolean result = testFacility.canBook(910, utiltime);
        } catch (ExTimeSlotNotInOpeningHour e) {
            assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.", e.getMessage());
        }
    }

    @Test
    public void test_facility_canBook_5() throws ExTimeRangeNotCorrect,ExTimeSlotNotInOpeningHour,IOException, ExInputTimeEarlierThanCurrentTime, ExInputTimeEarlierThanCurrentTime, ExAllowToBookOneHourOnly{
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1819, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        boolean result = testFacility.canBook(1819, utiltime);
        assertEquals(false, result);
    }

    @Test
    public void test_facility_canBook_6() throws ExTimeRangeNotCorrect,ExTimeSlotNotInOpeningHour,IOException, ExInputTimeEarlierThanCurrentTime, ExInputTimeEarlierThanCurrentTime, ExAllowToBookOneHourOnly{
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1213, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
            boolean result = testFacility.canBook(1617, utiltime);
            assertEquals(true, result);
    }

    @Test
    public void test_facility_canDelete_1() throws ExTimeRangeNotCorrect, ExAllowToDeleteOneHourOnly, ExTimeSlotNotInOpeningHour, IOException, ExBookingHasPassed{
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1617, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        boolean result = testFacility.canDelete(1617, utiltime);
        assertEquals(true, result);
    }

    @Test
    public void test_facility_canDelete_2() throws ExTimeRangeNotCorrect, ExAllowToDeleteOneHourOnly, ExTimeSlotNotInOpeningHour, IOException, ExBookingHasPassed{
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1617, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        try {
            boolean result = testFacility.canDelete(1317, utiltime);

        }  catch (ExAllowToDeleteOneHourOnly e) {
            assertEquals("You are allowed to book 1 hour only for each booking. Please input again.", e.getMessage());
        }
    }

    @Test
    public void test_facility_canDelete_3() throws ExTimeRangeNotCorrect, ExAllowToDeleteOneHourOnly, ExTimeSlotNotInOpeningHour, IOException, ExBookingHasPassed{
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1617, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        try {
            boolean result = Facility.canDelete(910, utiltime);
        } catch (ExTimeSlotNotInOpeningHour e) {
            assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.", e.getMessage());
        }
    }

    @Test
    public void test_facility_canDelete_4() throws ExTimeRangeNotCorrect, ExAllowToDeleteOneHourOnly, ExTimeSlotNotInOpeningHour, IOException, ExBookingHasPassed{
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1617, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        try {
            boolean result = Facility.canDelete(1513, utiltime);
        } catch (ExTimeRangeNotCorrect e) {
            assertEquals("The input time range is not correct. Please input again.", e.getMessage());
        }
    }

    @Test
    public void test_facility_canDelete_5() throws ExTimeRangeNotCorrect, ExAllowToDeleteOneHourOnly, ExTimeSlotNotInOpeningHour, IOException, ExBookingHasPassed{
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1617, "20180101A1B11213");
        UtilTime utiltime = UtilTime.getTimeInstance();
        try {
            boolean result = Facility.canDelete(1112, utiltime);
        } catch (ExBookingHasPassed e) {
            assertEquals("Your booking has passed. Booking cannot be cancelled.", e.getMessage());
        }
    }

    //Booking
    @Test
    public void test_Booking1() throws IOException {
        UtilTime utilTime = UtilTime.getTimeInstance();
        Booking bk = new Booking("Ada", 1314, "E1B1", utilTime);
    }

    @Test
    public void test_Booking2() throws IOException {
        UtilTime utilTime = UtilTime.getTimeInstance();
        Booking bk = new Booking("Ada", 1314, "E1B1", utilTime);
        System.out.println(bk.getBookingID());
        assertEquals("20180505E1B11314", bk.getBookingID());
    }

    @Test
    public void test_Booking3() throws IOException {
        UtilTime utilTime = UtilTime.getTimeInstance();
        Booking bk = new Booking("Ada", 1314, "E1B1", utilTime);
        System.out.println(bk.getBookingTime());
        assertEquals(1314, bk.getBookingTime());
    }

    @Test
    public void test_Booking4() throws IOException {
        UtilTime utilTime = UtilTime.getTimeInstance();
        Booking bk = new Booking("Ada", 1314, "E1B1", utilTime);
        assertEquals("E1B1", bk.getFacilitiesID());
    }

    @Test
    public void test_Booking5() throws IOException {
        UtilTime utilTime = UtilTime.getTimeInstance();
        Booking bk = new Booking("Ada", 1314, "E1B1", utilTime);
        assertEquals("Ada", bk.getuserName());
    }

    @Test
    public void test_facility_getBookingStatus() {
        Facility testFacility = new Facility_Badminton("A1B1");
        testFacility.addToTimeTable(1112, "20180101A1B11112");
        testFacility.addToTimeTable(1617, "20180101A1B11213");
        String result = testFacility.getBookingStatus(1112);
        assertEquals("Booked, Booking ID: 20180101A1B11112", result);
    }

    //Fast Recommendation

	@Test
	public void test_getFastRecommendation1() {
        setOutput();
		User tester = new User("Mr C", "password", "Membership_Adult", "E1",  "badminton");
		tester.getFastRecommandation(UtilTime.getTimeInstance());
		String expected = "\n+--------------------Fast Recommendation System---------------------+\n" + 
				"Current Time: 15:00:00\n"+
				"List of available courts in Java Road Sports Centre on 16:00:00: 2\n" + 
				"1: E1B1\n" + 
				"2: E1B2\n" + 
				"List of available courts in Island East Sports Centre on 16:00:00: 2\n" + 
				"1: E2B1\n" + 
				"2: E2B2\n";
		assertEquals(expected,getOutput());
	}

	@Test
	public void test_getFastRecommendation2() {
        setOutput();
		User tester = new User("Mr C", "password", "Membership_Adult", "E1",  "badminton");
		User tester2 = new User("Mr B", "password", "Membership_Adult", "E1",  "badminton");
		User tester3 = new User("Mr A", "password", "Membership_Adult", "E1",  "badminton");
		tester2.addBooking("E1B1", 1617, UtilTime.getTimeInstance());
		tester3.addBooking("E1B2", 1617, UtilTime.getTimeInstance());
		tester.getFastRecommandation(UtilTime.getTimeInstance());
		String expected = "\n" +
                "-----------------Booking Confirmation--------------------\n" +
                "Booking ID: 20180505E1B11617\n" +
                "Sport Centre: Java Road Sports Centre\n" +
                "Facility Type: badminton court\n" +
                "Facility ID: E1B1\n" +
                "Price: 59.0\n" +
                "--------------------------End----------------------------\n" +
                "\n" +
                "\n" +
                "-----------------Booking Confirmation--------------------\n" +
                "Booking ID: 20180505E1B21617\n" +
                "Sport Centre: Java Road Sports Centre\n" +
                "Facility Type: badminton court\n" +
                "Facility ID: E1B2\n" +
                "Price: 59.0\n" +
                "--------------------------End----------------------------\n" +
                "\n" +
                "\n" +
               "+--------------------Fast Recommendation System---------------------+\n" + 
               "Current Time: 15:00:00\n" + 
               "List of available courts in Island East Sports Centre on 16:00:00: 2\n" + 
               "1: E2B1\n" + 
               "2: E2B2\n" + 
               "List of available courts in Java Road Sports Centre on 16:00:00: 0\n" + 
               "\n" + 
               "Sport Centres in other districts:\n" + 
               "List of available courts in Long Ping Sports Centre on 16:00:00: 2\n" + 
               "1: N2B2\n" + 
               "2: N2B1\n" + 
               "List of available courts in Stanley Sports Centre on 16:00:00: 2\n" + 
               "1: S2B1\n" + 
               "2: S2B2\n";
		assertEquals(expected,getOutput());
	}

	//User

    @Test
    public void test_getMembershipByAge1() {
    	setInput("10");
    	Membership result = User.getMembershipbyAge();
    	assertEquals(Membership_Student.getInstance(),result);
    }

    @Test
    public void test_getMembershipByAge2() {
    	setInput("70");
    	Membership result = User.getMembershipbyAge();
    	assertEquals(Membership_Senior.getInstance(),result);
    }

    @Test
    public void test_getMembershipByAge3() {
    	setInput("20");
    	Membership result = User.getMembershipbyAge();
    	assertEquals(Membership_Adult.getInstance(),result);
    }

    @Test
    public void test_getMembershipByAge4() {
    	setInput("-10\n10");
    	Membership result = User.getMembershipbyAge();
    	assertEquals(Membership_Student.getInstance(),result);
    }


    @Test
    public void test_printbookinghistory1() {
    	setOutput();
    	User tester = new User("Mr C", "password", "Membership_Adult", "E1",  "badminton");
    	String output = "----------------------------Booking History--------------------------" + "\n\n" +
    	"               There is no bookings currently."+ "\n\n"
    	+ "---------------------------------End---------------------------------\n";
    	tester.printTodayBookingHistory(UtilTime.getTimeInstance());
    	assertEquals(output,getOutput());
    }
    
    @Test
    public void test_printbookinghistory2() {
    	setOutput();
    	User tester = new User("Mr C", "password", "Membership_Adult", "E1",  "badminton");
    	tester.addBooking("E1B1", 1718, UtilTime.getTimeInstance());
    	tester.addBooking("E1B1", 1819, UtilTime.getTimeInstance());
    	String output = "\n" +
                "-----------------Booking Confirmation--------------------\n" +
                "Booking ID: 20180505E1B11718\n" +
                "Sport Centre: Java Road Sports Centre\n" +
                "Facility Type: badminton court\n" +
                "Facility ID: E1B1\n" +
                "Price: 59.0\n" +
                "--------------------------End----------------------------\n" +
                "\n" +
                "\n" +
                "-----------------Booking Confirmation--------------------\n" +
                "Booking ID: 20180505E1B11819\n" +
                "Sport Centre: Java Road Sports Centre\n" +
                "Facility Type: badminton court\n" +
                "Facility ID: E1B1\n" +
                "Price: 59.0\n" +
                "--------------------------End----------------------------\n" +
                "\n" +
                "----------------------------Booking History--------------------------\n" +
                "  Booking ID: 20180505E1B11718 Court ID: E1B1 Time: 17:00 - 18:00\n" +
                "  Booking ID: 20180505E1B11819 Court ID: E1B1 Time: 18:00 - 19:00\n" +
                "---------------------------------End---------------------------------\n";
    	tester.printTodayBookingHistory(UtilTime.getTimeInstance());
    	assertEquals(output,getOutput());
    }


    @Test
    public void test_searchVacancies() {
    	    User tester = new User("Mr C", "password", "Membership_Adult", "E1",  "badminton");
        	boolean result = tester.searchVacancies("A1", "badminton");
        	assertEquals(false,result);
    }

    @Test
    public void test_searchBookingById1() throws ExBookingNotExist, IOException, ExFullBooking {
    	User tester = new User("Mr C", "password", "Membership_Adult", "E1",  "badminton");
    	tester.addBooking("E1B1", 1718, UtilTime.getTimeInstance());
    	Booking result = tester.searchBookingById("20180505E1B11718");
    	Booking expected = new Booking("Mr C",1718,"E1B1",UtilTime.getTimeInstance());
    	assertEquals(expected.getBookingID(),result.getBookingID());
        assertEquals(expected.getBookingTime(),result.getBookingTime());
        assertEquals(expected.getFacilitiesID(),result.getFacilitiesID());
        assertEquals(expected.getuserName(),result.getuserName());
    }

    @Test
    public void test_searchBookingById2() {
    	try {
    	User tester = new User("Mr C", "password", "Membership_Adult", "E1",  "badminton");
    	tester.addBooking("E1B1", 1718, UtilTime.getTimeInstance());
    	tester.searchBookingById("20180705E1B11718");
    	}
    	catch(Exception e) {
    		assertEquals("Booking ID does not exist! Cannot delete!", e.getMessage());
    	}
    }

    @Test
	public void test_addBooking(){
        setOutput();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1",  "badminton");
		tester.addBooking("E1B2", 1415,UtilTime.getTimeInstance());
		assertEquals("Your input time has passed. Please enter a time slot later than the current time.\n", getOutput());
	}

	@Test
	public void test_addBooking2(){
        setOutput();
		UtilTime utilTime_stub = UtilTime.getTimeInstance();
		User tester = new User("Mr B", "password", "Membership_Adult", "E1",  "badminton");
		tester.addBooking("E1B2", 2211,utilTime_stub);
		assertEquals("The input time range is not correct. Please input again.\n", getOutput());
	}

	@Test
	public void test_addBooking3(){
        setOutput();
            UtilTime utilTime_stub = UtilTime.getTimeInstance();
            User tester = new User("Mr C", "password", "Membership_Adult", "E1",  "badminton");
            tester.addBooking("E2B2", 1617, utilTime_stub);
            tester.addBooking("E2B2", 1718, utilTime_stub);
            tester.addBooking("E2B2", 1819, utilTime_stub);
            tester.addBooking("E2B2", 1920, utilTime_stub);
            String result = "\n-----------------Booking Confirmation--------------------\n" +
                    "Booking ID: 20180505E2B21617\n" +
                    "Sport Centre: Island East Sports Centre\n" +
                    "Facility Type: badminton court\n" +
                    "Facility ID: E2B2\n" +
                    "Price: 59.0\n" +
                    "--------------------------End----------------------------\n" +
                    "\n" +
                    "\n" +
                    "-----------------Booking Confirmation--------------------\n" +
                    "Booking ID: 20180505E2B21718\n" +
                    "Sport Centre: Island East Sports Centre\n" +
                    "Facility Type: badminton court\n" +
                    "Facility ID: E2B2\n" +
                    "Price: 59.0\n" +
                    "--------------------------End----------------------------\n" +
                    "\n" +
                    "\n" +
                    "-----------------Booking Confirmation--------------------\n" +
                    "Booking ID: 20180505E2B21819\n" +
                    "Sport Centre: Island East Sports Centre\n" +
                    "Facility Type: badminton court\n" +
                    "Facility ID: E2B2\n" +
                    "Price: 59.0\n" +
                    "--------------------------End----------------------------\n" +
                    "\n" +
                    "All badminton court has been fulled in the time slot: 1920 in Island East Sports Centre\n" +
                    "Please choose another time slot or sport centre.\n";
            assertEquals(result,getOutput());
	}

	@Test
	public void test_addBooking_twoPerson() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist {
		Controller controller = Controller.getInstance();
		UtilTime utilTime_stub = UtilTime.getTimeInstance();
		controller.importData();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1",  "badminton");
		tester.addBooking("E1B2", 1718,utilTime_stub);

		User tester2 = new User("Mr B", "password", "Membership_Adult", "E1",  "badminton");
		tester2.addBooking("E1B2", 1718,utilTime_stub);
		int result = tester2.getTodayBookingNum();
		assertEquals(0, result);
	}
	


	@Test
	public void test_deleteBooking1() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExFullBooking {
		setOutput();
		UtilTime utilTime_stub = UtilTime.getTimeInstance();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1",  "badminton");
		tester.addBooking("E1B2", 1617,utilTime_stub);
		tester.deleteBooking("20181105E1B21617",utilTime_stub);
		String result = "\n" +
                "-----------------Booking Confirmation--------------------\n" +
                "Booking ID: 20180505E1B21617\n" +
                "Sport Centre: Java Road Sports Centre\n" +
                "Facility Type: badminton court\n" +
                "Facility ID: E1B2\n" +
                "Price: 59.0\n" +
                "--------------------------End----------------------------\n" +
                "\n" +
                "Booking ID does not exist! Cannot delete!\n";
		assertEquals(result, getOutput());
	}

	@Test
	public void test_deleteBooking2() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExFullBooking {
		setOutput();
		UtilTime utilTime_stub = UtilTime.getTimeInstance();
		User tester = new User("Mr A", "password", "Membership_Adult", "E1",  "badminton");
		tester.addBooking("E1B2", 1617,utilTime_stub);
		tester.deleteBooking("20180505E1B21617",utilTime_stub);
		String result = "\n" +
                "-----------------Booking Confirmation--------------------\n" +
                "Booking ID: 20180505E1B21617\n" +
                "Sport Centre: Java Road Sports Centre\n" +
                "Facility Type: badminton court\n" +
                "Facility ID: E1B2\n" +
                "Price: 59.0\n" +
                "--------------------------End----------------------------\n" +
                "\n" +
                "\n" +
                "-------------------Delete Confirmation-------------------\n" +
                "User ID: Mr A\n" +
                "Booking ID: 20180505E1B21617\n" +
                "Facility Type: badminton court\n" +
                "Facility ID: E1B2\n" +
                "Booking with id: 20180505E1B21617 has been deleted.\n" +
                "--------------------------End----------------------------\n" +
                "\n";
		assertEquals(result, getOutput());
	}


    @Test
    public void test_searchVacancy() throws ExFacilityIdNotExist, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig {
        setOutput();
        User tester = new User("Mr C", "password", "Membership_Adult", "E1",  "badminton");
        tester.searchVacancies("E1", "badminton");
        String output = "\n" + "Sport Centre: Java Road Sports Centre\n" +
                "\n" +
                "Facility Code: E1B1\n" +
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
                "2324 : Available\n" +
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


    //Exception
    @Test
    public void test_Exception1() {
        ExAllowToBookOneHourOnly e = new ExAllowToBookOneHourOnly();
        assertEquals("You are allowed to book 1 hour only for each booking. Please input again.", e.getMessage());
    }

    @Test
    public void test_Exception2() {
        ExAllowToDeleteOneHourOnly e = new ExAllowToDeleteOneHourOnly();
        assertEquals("You are allowed to book 1 hour only for each booking. Please input again.", e.getMessage());
    }

    @Test
    public void test_Exception3() {
        ExBookingHasPassed e = new ExBookingHasPassed();
        assertEquals("Your booking has passed. Booking cannot be cancelled.", e.getMessage());
    }


    @Test
    public void test_Exception4() {
        ExBookingNotExist e = new ExBookingNotExist();
        assertEquals("Booking ID does not exist! Cannot delete!", e.getMessage());
    }

    @Test
    public void test_Exception5() {
        ExFacilityIdNotExist e = new ExFacilityIdNotExist();
        assertEquals("Your inputted facility id does not exist!", e.getMessage());
    }

    @Test
    public void test_Exception51() {
        ExFacilityIdNotExist e = new ExFacilityIdNotExist("Hello");
        assertEquals("Hello", e.getMessage());
    }

    @Test
    public void test_Exception6() {
        ExFacilityNameNotExist e = new ExFacilityNameNotExist();
        assertEquals("The facility name does not exist. Please type again.\nFacility Type: badminton, tableTennis, activityRoom", e.getMessage());
    }

    @Test
    public void test_Exception7() {
        ExFullBooking e = new ExFullBooking("E1", "badminton", 1314);
        assertEquals("All " + "badminton" + " has been fulled in the time slot: " + "1314" + " in " + "E1" + "\n"
                + "Please choose another time slot or sport centre.", e.getMessage());}

    @Test
    public void test_Exception8() {
        ExInputTimeEarlierThanCurrentTime e = new ExInputTimeEarlierThanCurrentTime();
        assertEquals("Your input time has passed. Please enter a time slot later than the current time.", e.getMessage());
    }

    @Test
    public void test_Exception9() {
        ExIOErrorinGetConfig e = new ExIOErrorinGetConfig();
        assertEquals("IO exception found when getConfig(), please check the config file again.", e.getMessage());
    }

    @Test
    public void test_Exception10() {
        ExMaxFailLogin e = new ExMaxFailLogin();
        assertEquals("You have reached the maximum fail login limit.", e.getMessage());
    }

    @Test
    public void test_Exception11() {
        ExMemberShipFilePathNotExist e = new ExMemberShipFilePathNotExist();
        assertEquals("The membership files does not exist. Please check the path again.", e.getMessage());
    }

    @Test
    public void test_Exception12() {
        ExSCFilesNotExist e = new ExSCFilesNotExist();
        assertEquals("The sport centre files does not exist. Please check the path again.", e.getMessage());
    }

    @Test
    public void test_Exception13() {
        ExSportCentreNotExist e = new ExSportCentreNotExist();
        assertEquals("The input Sport Centre ID does not exist!", e.getMessage());
    }

    @Test
    public void test_Exception14() {
        ExSportCentreNotExist e = new ExSportCentreNotExist("Hello");
        assertEquals("Hello", e.getMessage());
    }

    @Test
    public void test_Exception15() {
        ExTimeRangeNotCorrect e = new ExTimeRangeNotCorrect();
        assertEquals("The input time range is not correct. Please input again.", e.getMessage());
    }

    @Test
    public void test_Exception16() {
        ExTimeSlotNotInOpeningHour e = new ExTimeSlotNotInOpeningHour();
        assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.", e.getMessage());
    }

    @Test
    public void test_Exception17() {
        ExUserIdNotExist e = new ExUserIdNotExist("Jeff");
        assertEquals("Jeff" + "does not exist!", e.getMessage());
    }

    //UtilTime
    @Test
    public void test_UtilTime1() {
        UtilTime utilTime = new UtilTime();
        assertEquals(true, utilTime.isTimeLaterThanCurrentTime("24:00:00"));
    }

    @Test
    public void test_UtilTime2() {
        UtilTime utilTime = new UtilTime();
        assertEquals(false, utilTime.isTimeLaterThanCurrentTime("00:00:00"));
    }

    @Test
    public void test_UtilTime3() {
        UtilTime utilTime = new UtilTime();
        assertEquals(-1, utilTime.isTimeRangeExceed(2526));
    }

    @Test
    public void test_UtilTime32() {
        UtilTime utilTime = new UtilTime();
        assertEquals(-1, utilTime.isTimeRangeExceed(2226));
    }

    @Test
    public void test_UtilTime33() {
        UtilTime utilTime = new UtilTime();
        assertEquals(-1, utilTime.isTimeRangeExceed(2220));
    }

    @Test
    public void test_UtilTime4() {
        UtilTime utilTime = new UtilTime();
        assertEquals(-2, utilTime.isTimeRangeExceed(1214));
    }

    @Test
    public void test_UtilTime5() {
        UtilTime utilTime = new UtilTime();
        assertEquals(-3, utilTime.isTimeRangeExceed(910));
    }

    @Test
    public void test_UtilTime6() {
        UtilTime utilTime = new UtilTime();
        assertEquals("10:00 - 11:00", utilTime.getTimeWithFormat(1011));
    }

    //getTimeSlot
    @Test
    public void test_UtilTime7() {
        UtilTime utilTime = new UtilTime();
        assertEquals(1011, utilTime.getTimeSlot("10:00", "11:00"));
    }

    @Test
    public void test_UtilTime8() {
        UtilTime utilTime = new UtilTime();
        assertEquals(2324, utilTime.getTimeSlot("23:00", "00:00"));
    }

    //Controller

		@Test
		public void test_controller() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, IOException {
			Controller controller = Controller.getInstance();
			controller.importData();
			controller.printAllFacilities();
			controller.exportAllMembeer();
			controller.exportAllSchedule();
		}

		//exist
		@Test
		public void test_searchUserById() {
			Controller controller = Controller.getInstance();
			User user = controller.getUserbyID("Ada");
			assertEquals(new User("Ada","","","","").getUserName(),user.getUserName());
		}

		//not exist
		@Test
		public void test_searchUserById_False() {
			try {
			Controller controller = Controller.getInstance();
			controller.searchSCByDistrict("E1", true);
			controller.searchUserById("John");
			}
			catch(Exception e) {
				assertEquals(new ExUserIdNotExist("John").toString(),e.toString());
			}
		}

		@Test
		public void test_SearchSportCentre() throws ExSportCentreNotExist, ExFacilityNameNotExist, ExFacilityIdNotExist {
			Controller controller = Controller.getInstance();
			SportCentre sc = controller.searchSportCentre("E1");
			controller.searchFacilitiesByType(sc, "badminton");
			controller.searchFacilitiesByType(sc, "tableTennis");
			controller.searchFacilitiesByType(sc, "activityRoom");
			assertEquals("E1",sc.getScId());
		}

		@Test
		public void test_searchSCByDistrict() {
			Controller controller = Controller.getInstance();

		}

		@Test
		public void test_searchSCByDistrict_1() {
			Controller controller = Controller.getInstance();

		}

		@Test
		public void test_searchUserbyid() throws ExUserIdNotExist {
			Controller controller = Controller.getInstance();
			User user = controller.searchUserById("Ada");
			Set<SportCentre> set = controller.searchSCByDistrict("V1", false);
			assertEquals(user.getUserName(),new User("Ada","","","","").getUserName());
		}
		//ExSportCentreNotExist();

		@Test
		public void test_searchSportFacility() throws ExSportCentreNotExist {
			Controller controller = Controller.getInstance();
			try{
			SportCentre sc = controller.searchSportCentre("V1");}
			catch(Exception e) {
				assertEquals(new ExSportCentreNotExist().toString(), e.toString());
			}
		}

		@Test
		public void test_Login() throws ExMemberShipFilePathNotExist, ExIOErrorinGetConfig, ExMaxFailLogin {
			setInput("Helena"+System.getProperty("line.separator")+"Ken"+System.getProperty("line.separator")+"1234"+System.getProperty("line.separator")+"123"+System.getProperty("line.separator"));
			setOutput();
			User user = User.Login();
			assertEquals("Ken",user.getUserName());
		}
		@Test
		public void test_Login1() throws ExMemberShipFilePathNotExist, ExIOErrorinGetConfig, ExMaxFailLogin {
			try {
			setInput("Helena"+System.getProperty("line.separator")+"Ken"+System.getProperty("line.separator")+"1234"+System.getProperty("line.separator")+"1234"+System.getProperty("line.separator")+"1234"+System.getProperty("line.separator"));
			setOutput();
			User user = User.Login();

			}
			catch(Exception e) {
				assertEquals(new ExMaxFailLogin().toString(),e.toString());
				};
		}
		@Test
		public void test_SetUpAC1() throws ExMemberShipFilePathNotExist, ExIOErrorinGetConfig, ExMaxFailLogin {
			setInput(
					"Ken"+System.getProperty("line.separator")+
					"Polo"+System.getProperty("line.separator")+
					"123"+System.getProperty("line.separator")+
					"123"+System.getProperty("line.separator")+
					"123"+System.getProperty("line.separator")+
					"123"+System.getProperty("line.separator")+
					"ABC"+System.getProperty("line.separator")+
					"5"+System.getProperty("line.separator")+
					"E6"+System.getProperty("line.separator")+
					"E1"+System.getProperty("line.separator")+
					"H"+System.getProperty("line.separator")+
					"B"+System.getProperty("line.separator"));
			User.setUpAC();
			}

    //*******************PLEASE DO NOT DELETE BELOW CODE AND ADD TEST CASE UNDER IT*******************//
    PrintStream oldPrintStream;
    ByteArrayOutputStream bos;

    private void setOutput() {
        oldPrintStream = System.out;
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
    }

    private void setInput(String input) {
    	 testIn = new ByteArrayInputStream(input.getBytes());
         System.setIn(testIn);
    }

    private String getOutput() { // throws Exception
        System.setOut(oldPrintStream);
        return bos.toString();
    }
}
