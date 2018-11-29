package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Membership.Membership_Adult;
import Util.UtilTime;

	public class Test_Membership_Adult {

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
}
