package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Membership.*;
import Util.UtilTime;

	public class Test_Membership_Student {

		@Test	
		public void test_membership_senior1() { 							//test get discount
		Membership_Student membership = Membership_Student.getInstance();    
		assertEquals(0.5,membership.getDiscount());
	}
		
		@Test	
		public void test_membership_senior2() { 							//test get discount
		Membership_Student membership = Membership_Student.getInstance();    
		assertEquals("Membership_Student",membership.getMembershipName());
	}
}