package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Util.UtilValidation;

public class Test_UtilValidation {
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
	public void test_timevalidation_1(){
		UtilValidation valid = UtilValidation.getValidationInstance();
		assertEquals(1314,valid.validateTimeFormat("1314"));
	}
	@Test
	public void test_timevalidation_2(){
		UtilValidation valid = UtilValidation.getValidationInstance();
		assertEquals(0,valid.validateTimeFormat("13145"));
	}
}
