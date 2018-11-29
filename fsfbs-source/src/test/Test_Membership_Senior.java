package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Membership.Membership_Senior;
import Util.UtilTime;

	public class Test_Membership_Senior {

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
}