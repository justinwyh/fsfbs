package test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import Facility.*;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exception.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Test_FSFBS {
	
		//membership adult
		@Test	
		public void test_membership_adult1() { 							//test get discount
			Membership_Adult membership = Membership_Adult.getInstance();
			assertEquals(1,membership.getDiscount());
		}
	
		@Test	
		public void test_membership_adult2() { 							//test get discount
			Membership_Adult membership = Membership_Adult.getInstance();    
			assertEquals("Membership_Adult",membership.getMembershipName());
}
	
		//membership senior
		@Test	
		public void test_membership_senior1() { 							//test get discount
		Membership_Senior membership = Membership_Senior.getInstance();    
		assertEquals(0.5,membership.getDiscount());
	}
		
		@Test	
		public void test_membership_senior2() { 							//test get discount
		Membership_Senior membership = Membership_Senior.getInstance();    
		assertEquals("Membership_Senior",membership.getMembershipName());
	}
		
		//membership student
		@Test	
		public void test_membership_student() { 							//test get discount
		Membership_Student membership = Membership_Student.getInstance(); 
		assertEquals(0.5,membership.getDiscount());
	}
		
		@Test	
		public void test_membership_student2() { 							//test get discount
		Membership_Student membership = Membership_Student.getInstance();    
		assertEquals("Membership_Student",membership.getMembershipName());
	}
		//UtilExport
		@Test
		public void test_membership() throws IOException {
			String result = UtilsLoadconfig.getConfig("membershipFilePath");
			String str = "./Data/Membership/";
			assertEquals(str, result);
		} 
		
		@Test
		public void test_booking() throws IOException {
			String result = UtilsLoadconfig.getConfig("bookingFilePath");
			String str = "./Data/Booking/";
			assertEquals(str, result);
		}
		
		@Test
		public void test_sportsCentre() throws IOException {
			String result = UtilsLoadconfig.getConfig("sportCentreFilePath");
			String str = "./Data/SportFacility/";
			assertEquals(str, result);
		}
		
		@Test
		public void test_time() throws IOException {
			String result = UtilsLoadconfig.getConfig("timeScheduleFilePath");
			String str = "./Data/TimeSchedule/";
			assertEquals(str, result);
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
		public void test_SportFacilities5() throws ExFacilityIdNotExist, ExFacilityNameNotExist, ExSportCentreNotExist, ExTimeRangeNotCurrent, ExAllowToBookOneHourOnly, ExTimeSlotNotInOpeningHour, ExInputTimeEarlierThanCurrentTime, IOException, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig {
			UtilTime utilTime = UtilTime.getTimeInstance();
			Controller controller = Controller.getInstance();
			SportCentre temp = controller.searchSportCentre("E1");
			ArrayList<String> list = temp.searchVacanciesInSC(1718,"badminton",utilTime);
			for(String txt:list)
				System.out.println(txt);
		}
		
		@Test
		public void test_searchVacancy()  {
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
		
		//*******************PLEASE DO NOT DELETE BELOW CODE AND ADD TEST CASE UNDER IT*******************//
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
