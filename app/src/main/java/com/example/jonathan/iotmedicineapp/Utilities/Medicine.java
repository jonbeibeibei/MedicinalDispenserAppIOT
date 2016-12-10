package com.example.jonathan.iotmedicineapp.Utilities;

/**
 * Created by woshibiantai on 10/12/16.
 */

public class Medicine {
    private String name = "";
    private boolean amount;
    private String website = "";

    Medicine() {};

    public Medicine(String name, boolean amount, String website) {
        this.name = name;
        this.amount = amount;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAmount() {
        return amount;
    }

    public void setAmount(boolean amount) {
        this.amount = amount;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
