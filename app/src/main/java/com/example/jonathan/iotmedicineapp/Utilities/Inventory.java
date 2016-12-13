package com.example.jonathan.iotmedicineapp.Utilities;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woshibiantai on 29/11/16.
 */

public class Inventory {
    private List<Medicine> inventory = new ArrayList<>();
    private Medicine medicine;
    DatabaseReference inventoryDatabase = FirebaseDatabase.getInstance().getReference();

    public void addMedicine(String name, boolean amount, String website) {
        medicine = new Medicine(name, amount, website);

        inventory.add(medicine);

        inventoryDatabase.child("Inventory").child(name).setValue(medicine);
    }

    public void removeMedicine(String name) {
        for (Medicine medication : inventory) {
            if (medication.getName().equals(name)) {
                inventory.remove(medication);
                inventoryDatabase.child("Inventory").child(name).removeValue();
                break;
            }
        }
    }

    public List<Medicine> getInventory() {
        return inventory;
    }



    public String getAmount(String name) {
        for (Medicine medication : inventory) {
            if (medication.getName().equals(name)) {
                return Boolean.toString(medication.getAmount());
            }
        }
        return "Please refill soon!";
    }

}
