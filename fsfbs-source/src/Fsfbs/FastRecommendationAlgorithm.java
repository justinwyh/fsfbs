package Fsfbs;

import java.util.ArrayList;

public class FastRecommendationAlgorithm {
    private static FastRecommendationAlgorithm instance = new FastRecommendationAlgorithm();

    public static FastRecommendationAlgorithm getInstance() {
        return instance;
    }
    public void fastRcommandation(String preferedSC, String preferedFacility){
        Controller controller =  Controller.getInstance();
        int count = 0;
        //priority: SportCentre, District
        ArrayList<SportCentre> scInSameDistrictList = controller.searchSCByDistrict(preferedSC, true);
        ArrayList<SportCentre> scNotInSameDisctrictList = controller.searchSCByDistrict(preferedSC,false);
        for (SportCentre sc: scInSameDistrictList){

        }


    }
}
