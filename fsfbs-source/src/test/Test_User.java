package test;
import Fsfbs.*;
import Util.UtilTime;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Exception.ExFacilityIdNotExist;
import Exception.ExFullBooking;
import Exception.ExIOErrorinGetConfig;
import Exception.ExMaxFailLogin;
import Exception.ExMemberShipFilePathNotExist;
import Exception.ExSCFilesNotExist;

class Test_User {
	private ByteArrayInputStream testIn;
	
	@Test
	public void test_main() throws ExMemberShipFilePathNotExist, ExIOErrorinGetConfig, ExMaxFailLogin {
		setInput("Helena"+System.getProperty("line.separator")+"Ken"+System.getProperty("line.separator")+"123"+System.getProperty("line.separator"));
		setOutput();		
		User user = User.Login();
		assertEquals("Ken",user.getUserName());
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

