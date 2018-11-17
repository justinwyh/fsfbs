package Fsfbs;

import java.io.File;
import java.io.IOException;

public class UtilValidation {
	private static UtilValidation validationInstance= new UtilValidation();
    public static UtilValidation getValidationInstance() {return validationInstance;}
	

  //validate account ----------------------------------------------------------------------------------------
    public boolean existedAC(String account) throws IOException, NullPointerException {
    	 File file = new File(UtilsLoadconfig.getConfig("membershipFilePath"));
         File[] files = file.listFiles();
         for(File f: files){
        	 String filename = f.getName().substring(0,f.getName().length()-4);
        	 if(filename.equals(account))
        		 return true;
         }
    	return false;
    	}


    public boolean validateSportCentreById(String inputSCId){
     Controller controller =  Controller.getInstance();
     SportCentre sc = controller.getSportCentrebyID(inputSCId);
     if (sc == null){
         return false;
     }
     return true;
    }

    public boolean validateUserByName(String inputUserName){
        Controller controller =  Controller.getInstance();
        User user = controller.getUserbyID(inputUserName);
        if (user == null){
            return false;
        }
        return true;
    }
    
}
