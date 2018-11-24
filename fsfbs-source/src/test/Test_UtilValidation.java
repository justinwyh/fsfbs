package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Fsfbs.UtilValidation;

public class Test_UtilValidation {
	@Test
	public void test_Ada() throws NullPointerException, IOException {
		UtilValidation export = new UtilValidation();
		boolean result = export.existedAC("Ada");
		assertEquals(true, result);
	}
	
	@Test
	public void test_Ken() throws NullPointerException, IOException {
		UtilValidation export = new UtilValidation();
		boolean result = export.existedAC("Ken");
		assertEquals(true, result);
	}
	
	@Test
	public void test_Jacky() throws NullPointerException, IOException {
		UtilValidation export = new UtilValidation();
		boolean result = export.existedAC("Jacky");
		assertEquals(false, result);
	}
}
