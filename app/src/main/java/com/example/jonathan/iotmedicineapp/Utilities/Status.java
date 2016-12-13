package com.example.jonathan.iotmedicineapp.Utilities;

/**
 * Created by woshibiantai on 29/11/16.
 */

public class Status {
    private String description;
    private int missedDoses;
    private boolean afternoon;
    private boolean morning;
    private boolean night;
    private double chartDoses = 0;


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



    public boolean getAfternoon() {
        return afternoon;
    }

    public boolean getMorning() {
        return morning;
    }

    public boolean getNight() {
        return night;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public void setNight(boolean night) {
        this.night = night;
    }

    public double averageMissed() {
        if (afternoon){
            chartDoses += 1;
        }
        if (morning){
            chartDoses += 1;
        }
        if (night){
            chartDoses += 1;
        }
        return Math.floor((chartDoses/3)*100);
    }


}


