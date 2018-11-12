package Fsfbs;

import java.util.ArrayList;

public class District {
    private String dname;
    private int districtId;
    private ArrayList<SportCentre> sportCentresList;

    public District(String dname, int districtId){
        this.dname = dname;
        this.districtId = districtId;
        sportCentresList = new ArrayList<>();
    }

    public void setSportCentresList(){

    }

}
