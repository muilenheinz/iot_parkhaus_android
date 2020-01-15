package com.example.iotparkhaus.customDataStructs;

import java.util.HashMap;

public class parkingGarageOccupation {
    private static parkingGarageOccupation instance = null;

    //Integer is the spot number, bool is the availability
    private HashMap<Integer, Boolean> occupation = new HashMap<>();


    private parkingGarageOccupation() {
        //implement as singelton
    }

    public static parkingGarageOccupation getInstance() {
        if (instance == null) {
            instance = new parkingGarageOccupation();
        }
        return instance;
    }
    public HashMap<Integer, Boolean> getOccupation() {
        return occupation;
    }

    public void addSpotOccupation(Integer _spotNumber, Boolean _available) {
        this.occupation.put(_spotNumber, _available);
    }
}
