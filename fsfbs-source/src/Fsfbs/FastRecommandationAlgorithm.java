package Fsfbs;

public class FastRecommandationAlgorithm {
    private static FastRecommandationAlgorithm instance = new FastRecommandationAlgorithm();

    public static FastRecommandationAlgorithm getInstance() {
        return instance;
    }
    public void fastRcommandation(String preferedSC, String preferedFacility){
        Controller controller =  Controller.getInstance();
        //priority: SportCentre,
    }
}
