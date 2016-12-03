package com.example.jonathan.iotmedicineapp.Utilities;

/**
 * Created by woshibiantai on 29/11/16.
 */

public class Status {
    private String description;
    private int missedDoses;

    Status() {
        description = "Looking good!";
        missedDoses = 0;
    }

    String getDescription() {
        if (missedDoses == 0) {
            description = "Looking good! ";
        } else {
            description = missedDoses + "missed doses.";
        }
        return description;
    }




}
