package test;
import static org.junit.jupiter.api.Assertions.*;

import Fsfbs.UtilsExport;
import org.junit.jupiter.api.Test;

import java.io.*;

class Test_UtilExport {
	//statement coverage
	@Test
	public void testUtilWrite_1() {
	    String[] s = new String[5];
	    for (int i =0; i<5; i++){
            s[i] = "abcd";
        }
        UtilsExport.printToFile("./Data/Membership/abc.txt",s);
	    //logic check if file contains s
        boolean result = true;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./Data/Membership/abc.txt"));
            String line = reader.readLine();
            int i=0;
            while(line != null){
                if(!line.equals(s[i])){
                    result = false;
                    break;
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e){
            result = false;
        } catch (IOException e){
            result = false;
        }
        //return boolean true / f
        assertEquals(true, result);
	}
	


}
