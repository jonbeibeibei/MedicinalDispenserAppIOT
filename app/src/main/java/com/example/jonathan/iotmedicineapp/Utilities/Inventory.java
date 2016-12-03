package com.example.jonathan.iotmedicineapp.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woshibiantai on 29/11/16.
 */

public class Inventory {
    private List<List<String>> inventory = new ArrayList<>();
    private List<String> medicine = new ArrayList<String>(3);

    private void addMedicine(String name, int amount, String website) {
        medicine.add(name);
        medicine.add(Integer.toString(amount));
        medicine.add(website);

        inventory.add(medicine);

        medicine.clear();
    }

    private void removeMedicine(String name) {
        for (List<String> medication : inventory) {
            if (medication.get(0).equals(name)) {
                inventory.remove(medication);
            }
        }
    }

    private String getInventory() {
        return inventory.toString();
    }



    private String getAmount(String name) {
        for (List<String> medication : inventory) {
            if (medication.get(0).equals(name)) {
                return medication.get(1);
            }
        }
        return "Please refill soon!";
    }

}
