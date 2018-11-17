package Fsfbs;

public class UtilValidation {
    private static UtilValidation validationInstance= new UtilValidation();
    public static UtilValidation getValidationInstance() {return validationInstance;}

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