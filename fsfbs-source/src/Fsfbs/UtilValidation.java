package Fsfbs;

import java.io.File;
import java.io.IOException;

public class UtilValidation {
	
    public static boolean validateSportCenter() {
		
    	return true;   	
    }
  //validate account ----------------------------------------------------------------------------------------
    public static boolean existedAC(String account) throws IOException, NullPointerException {
    	 File file = new File(UtilsLoadconfig.getConfig("membershipFilePath"));
         File[] files = file.listFiles();
         for(File f: files){
        	 String filename = f.getName().substring(0,f.getName().length()-4);
        	 if(filename.equals(account))
        		 return true;
         }
    	return false;
    	}
    
    //validate if sportcentre existed
    public static boolean existedSportCenre(String sportcentre) {
    }
}
