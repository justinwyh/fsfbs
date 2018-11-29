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
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Test.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import Exception.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilTime.class)
public class Test_FSFBS {

    //membership adult
    @Test
    public void test_membership_adult1() {                            //test get discount
        Membership_Adult membership = Membership_Adult.getInstance();
        assertEquals(1, membership.getDiscount());
    }

    @Test
    public void test_membership_adult2() {                            //test get discount
        Membership_Adult membership = Membership_Adult.getInstance();
        assertEquals("Membership_Adult", membership.getMembershipName());
    }

    //membership senior
    @Test
    public void test_membership_senior1() {                            //test get discount
        Membership_Senior membership = Membership_Senior.getInstance();
        assertEquals(0.5, membership.getDiscount());
    }

    @Test
    public void test_membership_senior2() {                            //test get discount
        Membership_Senior membership = Membership_Senior.getInstance();
        assertEquals("Membership_Senior", membership.getMembershipName());
    }

    //membership student
    @Test
    public void test_membership_student() {                            //test get discount
        Membership_Student membership = Membership_Student.getInstance();
        assertEquals(0.5, membership.getDiscount());
    }

    @Test
    public void test_membership_student2() {                            //test get discount
        Membership_Student membership = Membership_Student.getInstance();
        assertEquals("Membership_Student", membership.getMembershipName());
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

    //S1B vacancy
    @Test
    public void test_SportFacilities5() throws ExFacilityIdNotExist, ExFacilityNameNotExist, ExSportCentreNotExist, ExTimeRangeNotCurrent, ExAllowToBookOneHourOnly, ExTimeSlotNotInOpeningHour, ExInputTimeEarlierThanCurrentTime, IOException, ExMemberShipFilePathNotExist, ExSCFilesNotExist, ExIOErrorinGetConfig {
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
    public void test_facility_canBook() {
    	try {
			UtilTime mockUtilTime = Whitebox.invokeConstructor(UtilTime.class);
			PowerMockito.mockStatic(UtilTime.class);
			Mockito.when(UtilTime.getTimeInstance()).thenReturn(mockUtilTime);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
