package com.example.jonathan.iotmedicineapp.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woshibiantai on 28/11/16.
 */

public class Parents {
    private String name;
    private int phoneNumber;
    private int profilePic;
    private Status status;

    Parents(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        status = new Status();
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(int number) {
        phoneNumber = number;
    }

    int getProfilePic() {
        return profilePic;
    }

    void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    String getStatus() {
        return status.getDescription();
    }

    private List<String> instructions = new ArrayList<>(3);
    private List<List<String>> prescription = new ArrayList<>();

    void addMedicine(String medicine, String timeToTake, String amount) {
        instructions.add(medicine);
        instructions.add(timeToTake);
        instructions.add(amount);

        prescription.add(instructions);

        instructions.clear();   // to ensure new instructions don't overlap
    }

    void removeMedicine(String medicine) {
        for (List<String> instruction : prescription) {
            if (instruction.get(0).equals(medicine)) {
                prescription.remove(instruction);
            }
        }
    }

    String getPrescription() {
        return prescription.toString();
    }

    void resetPrescription() {
        prescription.clear();
    }


}
