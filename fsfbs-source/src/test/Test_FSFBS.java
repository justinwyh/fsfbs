package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import Fsfbs.SimulationMode;
import Fsfbs.SportCentre;
import Fsfbs.User;
import Membership.*;
import Util.UtilTime;
import Util.UtilValidation;
import Util.UtilsLoadconfig;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Test.*;
import org.junit.runner.RunWith;

import Exception.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Test_FSFBS {

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

		//UtilValidation
		@Test
		public void test_timevalidation_1(){
			UtilValidation valid = UtilValidation.getValidationInstance();
			assertEquals(1314,valid.validateTimeFormat("1314"));
		}
		@Test
		public void test_timevalidation_2(){
			UtilValidation valid = UtilValidation.getValidationInstance();
			assertEquals(0,valid.validateTimeFormat("13145"));
		}
		@Test
		public void test_timevalidation_3(){
			UtilValidation valid = UtilValidation.getValidationInstance();
			assertEquals(0,valid.validateTimeFormat("ABB"));
		}
		@Test
		public void test_SimulationMode() throws IOException {
			assertEquals(true,SimulationMode.getSimulationMode());
		}

		//SportCentre
		@Test
		public void test_SportFacilities1() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
			Controller controller = Controller.getInstance();
			controller.importData();
			SportCentre temp = controller.searchSportCentre("E1");
			assertEquals("Java Road Municipal Services Building, 99 Java Road, North Point, Hong Kong",temp.getScAddress());
		}

		@Test
		public void test_SportFacilities2() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
			Controller controller = Controller.getInstance();
			SportCentre temp = controller.searchSportCentre("E1");
			assertEquals("E1",temp.getScId());
		}

		@Test
		public void test_SportFacilities3() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
			Controller controller = Controller.getInstance();
			SportCentre temp = controller.searchSportCentre("E1");
			assertEquals("Java Road Sports Centre",temp.getScName());
		}

		@Test
		public void test_SportFacilities4() throws ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig, ExFacilityIdNotExist, ExSportCentreNotExist {
			Controller controller = Controller.getInstance();
			SportCentre temp = controller.searchSportCentre("E1");
			assertEquals("25169415",temp.getScTel());
		}

		//S1B vacancy
		@Test
		public void test_SportFacilities51() throws ExFacilityIdNotExist, ExFacilityNameNotExist, ExSportCentreNotExist,ExTimeRangeNotCorrect, ExAllowToBookOneHourOnly, ExTimeSlotNotInOpeningHour, ExInputTimeEarlierThanCurrentTime, IOException, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig {
			UtilTime utilTime = UtilTime.getTimeInstance();
			Controller controller = Controller.getInstance();
			SportCentre temp = controller.searchSportCentre("E1");
			ArrayList<String> list = temp.searchVacanciesInSC(1718,"badminton",utilTime);
			for(String txt:list)
				System.out.println(txt);
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
			}
			catch(Exception e) {
				assertEquals("Your inputted facility id does not exist!",e.getMessage());
			}
		}

	    //S1B vacancy
	    @Test
	    public void test_SportFacilities5() throws ExFacilityIdNotExist, ExFacilityNameNotExist, ExSportCentreNotExist, ExTimeRangeNotCorrect, ExAllowToBookOneHourOnly, ExTimeSlotNotInOpeningHour, ExInputTimeEarlierThanCurrentTime, IOException, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig {
	        UtilTime utilTime = UtilTime.getTimeInstance();
	        Controller controller = Controller.getInstance();
	        SportCentre temp = controller.searchSportCentre("E1");
	        ArrayList<String> list = temp.searchVacanciesInSC(1718, "badminton", utilTime);
	        for (String txt : list)
	            System.out.println(txt);
	    }

	    @Test
	    public void test_searchVacancy() {
	        setOutput();
	        User tester = new User("Mr C", "password", "Membership_Adult", "E1", "Facility_Badminton");
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

	    //Facility Test Stub
	    @Test
	    public void test_Facility1() {
	        Facility_Badminton fb = new Facility_Badminton("E1B3");
	        assertEquals(59, fb.getPrice());
	    }

	    @Test
	    public void test_Facility2() {
	        Facility_Badminton fb = new Facility_Badminton("E1B3");
	        assertEquals("badminton court", fb.getFacilityType());
	    }

	    @Test
	    public void test_Facility3() {
	        Facility_ActivityRoom ar = new Facility_ActivityRoom("E1A3");
	        assertEquals(99, ar.getPrice());
	    }

	    @Test
	    public void test_Facility4() {
	        Facility_ActivityRoom ar = new Facility_ActivityRoom("E1A3");
	        assertEquals("activity room", ar.getFacilityType());
	    }

	    @Test
	    public void test_Facility5() {
	        Facility_TableTennis ar = new Facility_TableTennis("E1T3");
	        assertEquals(29, ar.getPrice());
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
	    	assertEquals(expectedResult,result);
	    }

	    @Test
	    public void test_facility_getTimeTableMap() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1213, "20180101A1B11213");
	    	Map<Integer,String> result = testFacility.getTimetableMap();
	    	Map<Integer,String> expectedResult = new HashMap<Integer,String>();
	    	expectedResult.put(1112,"20180101A1B11112");
	    	expectedResult.put(1213,"20180101A1B11213");
	    	assertEquals(expectedResult,result);
	    }

	    @Test
	    public void test_facility_removeFromTimeTable() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	String result = testFacility.removeFromTimeTable(1112);
	    	assertEquals("20180101A1B11112",result);
	    }

	    @Test
	    public void test_facility_canBook_1() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1213, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = testFacility.canBook(1112,utiltime);
				assertEquals(false,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToBookOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			} catch (ExInputTimeEarlierThanCurrentTime e) {
				assertEquals("Your input time has passed. Please enter a time slot later than the current time.",e.getMessage());
			} catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Test
	    public void test_facility_canBook_2() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1213, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = testFacility.canBook(1113,utiltime);
				assertEquals(false,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToBookOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			} catch (ExInputTimeEarlierThanCurrentTime e) {
				assertEquals("Your input time has passed. Please enter a time slot later than the current time.",e.getMessage());
			} catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Test
	    public void test_facility_canBook_3() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1213, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = testFacility.canBook(1312,utiltime);
				assertEquals(false,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToBookOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			} catch (ExInputTimeEarlierThanCurrentTime e) {
				assertEquals("Your input time has passed. Please enter a time slot later than the current time.",e.getMessage());
			} catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Test
	    public void test_facility_canBook_4() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1213, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = testFacility.canBook(910,utiltime);
				assertEquals(false,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToBookOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			} catch (ExInputTimeEarlierThanCurrentTime e) {
				assertEquals("Your input time has passed. Please enter a time slot later than the current time.",e.getMessage());
			} catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Test
	    public void test_facility_canBook_5() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1819, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = testFacility.canBook(1819,utiltime);
				assertEquals(false,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToBookOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			} catch (ExInputTimeEarlierThanCurrentTime e) {
				assertEquals("Your input time has passed. Please enter a time slot later than the current time.",e.getMessage());
			} catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Test
	    public void test_facility_canBook_6() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1213, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = testFacility.canBook(1617,utiltime);
				assertEquals(true,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToBookOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			} catch (ExInputTimeEarlierThanCurrentTime e) {
				assertEquals("Your input time has passed. Please enter a time slot later than the current time.",e.getMessage());
			} catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Test
	    public void test_facility_canDelete_1() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1617, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = testFacility.canDelete(1617,utiltime);
				assertEquals(true,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToDeleteOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			}  catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExBookingHasPassed e) {
				assertEquals("Your booking has passed. Booking cannot be cancelled.",e.getMessage());
			}
	    }

	    @Test
	    public void test_facility_canDelete_2() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1617, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = testFacility.canDelete(1317,utiltime);
				assertEquals(true,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToDeleteOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			}  catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExBookingHasPassed e) {
				assertEquals("Your booking has passed. Booking cannot be cancelled.",e.getMessage());
			}
	    }

	    @Test
	    public void test_facility_canDelete_3() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1617, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = Facility.canDelete(910,utiltime);
				assertEquals(true,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToDeleteOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			}  catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExBookingHasPassed e) {
				assertEquals("Your booking has passed. Booking cannot be cancelled.",e.getMessage());
			}
	    }

	    @Test
	    public void test_facility_canDelete_4() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1617, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = Facility.canDelete(1513,utiltime);
				assertEquals(true,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToDeleteOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			}  catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExBookingHasPassed e) {
				assertEquals("Your booking has passed. Booking cannot be cancelled.",e.getMessage());
			}
	    }

	    @Test
	    public void test_facility_canDelete_5() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1617, "20180101A1B11213");
	    	UtilTime utiltime = UtilTime.getTimeInstance();
	    	try {
				boolean result = Facility.canDelete(1112,utiltime);
				assertEquals(true,result);
			} catch (ExTimeRangeNotCorrect e) {
				assertEquals("The input time range is not correct. Please input again.",e.getMessage());
			} catch (ExAllowToDeleteOneHourOnly e) {
				assertEquals("You are allowed to book 1 hour only for each booking. Please input again.",e.getMessage());
			}  catch (ExTimeSlotNotInOpeningHour e) {
				assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExBookingHasPassed e) {
				assertEquals("Your booking has passed. Booking cannot be cancelled.",e.getMessage());
			}
	    }

	    //Booking Class
	    @Test
		public void test_Booking1() throws IOException {
			UtilTime utilTime = UtilTime_stub.getTimeInstance();
			Booking bk = new Booking("Ada",1314,"E1B1",utilTime);
		}

		@Test
		public void test_Booking2() throws IOException {
		UtilTime utilTime = UtilTime_stub.getTimeInstance();
		Booking bk = new Booking("Ada",1314,"E1B1",utilTime);
		System.out.println(bk.getBookingID());
		assertEquals("20180101E1B111314",bk.getBookingID());
		}

		@Test
		public void test_Booking3() throws IOException {
			UtilTime utilTime = UtilTime_stub.getTimeInstance();
			Booking bk = new Booking("Ada",1314,"E1B1",utilTime);
			System.out.println(bk.getBookingTime());
			assertEquals(1314,bk.getBookingTime());
		}
		@Test
		public void test_Booking4() throws IOException {
			UtilTime utilTime = UtilTime_stub.getTimeInstance();
			Booking bk = new Booking("Ada",1314,"E1B1",utilTime);
			assertEquals("E1B1",bk.getFacilitiesID());
		}

		@Test
		public void test_Booking5() throws IOException {
			UtilTime utilTime = UtilTime_stub.getTimeInstance();
			Booking bk = new Booking("Ada",1314,"E1B1",utilTime);
			assertEquals("Ada",bk.getuserName());
		}

	    @Test
	    public void test_facility_getBookingStatus() {
	    	Facility testFacility = new Facility_Badminton("A1B1");
	    	testFacility.addToTimeTable(1112, "20180101A1B11112");
	    	testFacility.addToTimeTable(1617, "20180101A1B11213");
	    	String result = testFacility.getBookingStatus(1112);
	    	assertEquals("Booked, Booking ID: 20180101A1B11112",result);
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
			assertEquals("Booking ID does not exist! Cannot delete!",e.getMessage());
		}
		@Test
		public void test_Exception5() {
			ExFacilityIdNotExist e = new ExFacilityIdNotExist();
			assertEquals("Your inputted facility id does not exist!",e.getMessage());
		}
		@Test
		public void test_Exception51() {
			ExFacilityIdNotExist e = new ExFacilityIdNotExist("Hello");
			assertEquals("Hello",e.getMessage());
		}
		@Test
		public void test_Exception6() {
			ExFacilityNameNotExist e = new ExFacilityNameNotExist();
			assertEquals("The facility name does not exist. Please type again.\nFacility Type: badminton, tableTennis, activityRoom",e.getMessage());
		}
		@Test
		public void test_Exception7() {
			ExFullBooking e = new ExFullBooking("E1","badminton",1314);
			assertEquals("All " + "E1B1" + " has been fulled in the time slot: " + "1314" + " in " + "E1"+"\n"
			        +"Please choose another time slot or sport centre.",e.getMessage());
		}
		@Test
		public void test_Exception8() {
			ExInputTimeEarlierThanCurrentTime e = new ExInputTimeEarlierThanCurrentTime();
			assertEquals("Your input time has passed. Please enter a time slot later than the current time.",e.getMessage());
		}

		@Test
		public void test_Exception9() {
			ExIOErrorinGetConfig e = new ExIOErrorinGetConfig();
			assertEquals("IO exception found when getConfig(), please check the config file again.",e.getMessage());
		}

		@Test
		public void test_Exception10() {
			ExMaxFailLogin e = new ExMaxFailLogin();
			assertEquals("You have reached the maximum fail login limit.",e.getMessage());
		}
		@Test
		public void test_Exception11() {
			ExMemberShipFilePathNotExist  e = new ExMemberShipFilePathNotExist();
			assertEquals("The membership files does not exist. Please check the path again.",e.getMessage());
		}
		@Test
		public void test_Exception12() {
			ExSCFilesNotExist e = new ExSCFilesNotExist();
			assertEquals("The sport centre files does not exist. Please check the path again.",e.getMessage());
		}
		@Test
		public void test_Exception13() {
			ExSportCentreNotExist  e = new ExSportCentreNotExist();
			assertEquals("The input Sport Centre ID does not exist!",e.getMessage());
		}
		@Test
		public void test_Exception14() {
			ExSportCentreNotExist  e = new ExSportCentreNotExist("Hello");
			assertEquals("Hello",e.getMessage());
		}
		@Test
		public void test_Exception15() {
			ExTimeRangeNotCorrect  e = new ExTimeRangeNotCorrect();
			assertEquals("The input time range is not correct. Please input again.",e.getMessage());
		}

		@Test
		public void test_Exception16() {
			ExTimeSlotNotInOpeningHour  e = new ExTimeSlotNotInOpeningHour();
			assertEquals("\nThe opening hour is from 10am to 12am. Please enter another time slot.",e.getMessage());
		}
		@Test
		public void test_Exception17() {
			ExUserIdNotExist e = new ExUserIdNotExist("Jeff");
			assertEquals("Jeff" + "does not exist!",e.getMessage());
		}
		//UtilTime
		@Test
		public void test_UtilTime1() {
			UtilTime utilTime = new UtilTime();
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
	        LocalDateTime now = LocalDateTime.now();
			assertEquals(dtf.format(now),utilTime.getCurrentTime());
		}

		@Test
		public void test_UtilTime2() {
			UtilTime utilTime = new UtilTime();
			assertEquals(true,utilTime.isTimeLaterThanCurrentTime("24:00:00"));
		}
		@Test
		public void test_UtilTime22() {
			UtilTime utilTime = new UtilTime();
			assertEquals(false,utilTime.isTimeLaterThanCurrentTime("00:00:00"));
		}
		@Test
		public void test_UtilTime3() {
			UtilTime utilTime = new UtilTime();
			assertEquals(-1,utilTime.isTimeRangeExceed(2526));
		}
		@Test
		public void test_UtilTime32() {
			UtilTime utilTime = new UtilTime();
			assertEquals(-1,utilTime.isTimeRangeExceed(2226));
		}
		@Test
		public void test_UtilTime33() {
			UtilTime utilTime = new UtilTime();
			assertEquals(-1,utilTime.isTimeRangeExceed(2220));
		}
		@Test
		public void test_UtilTime4() {
			UtilTime utilTime = new UtilTime();
			assertEquals(-2,utilTime.isTimeRangeExceed(1214));
		}
		@Test
		public void test_UtilTime5() {
			UtilTime utilTime = new UtilTime();
			assertEquals(-3,utilTime.isTimeRangeExceed(910));
		}

		@Test
		public void test_UtilTime6() {
			UtilTime utilTime = new UtilTime();
			assertEquals("10:00 - 11:00",utilTime.getTimeWithFormat(1011));
		}
		//getTimeSlot
		@Test
		public void test_UtilTime7() {
			UtilTime utilTime = new UtilTime();
			assertEquals(1011,utilTime.getTimeSlot("10:00","11:00"));
		}
		@Test
		public void test_UtilTime8() {
			UtilTime utilTime = new UtilTime();
			assertEquals(2324,utilTime.getTimeSlot("23:00","00:00"));
		}

		@Test
		public void test_UtilTime9() {
			UtilTime utilTime = new UtilTime();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:00:00");
			assertEquals(dtf.format(LocalDateTime.now().plusHours(1)),utilTime.getNextAvailableTimeSlot(1));
		}


    //*******************PLEASE DO NOT DELETE BELOW CODE AND ADD TEST CASE UNDER IT*******************//
    PrintStream oldPrintStream;
    ByteArrayOutputStream bos;

    private void setOutput() {
        oldPrintStream = System.out;
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
    }

    private String getOutput() { // throws Exception
        System.setOut(oldPrintStream);
        return bos.toString();
    }
}
