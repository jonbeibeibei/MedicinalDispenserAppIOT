package com.example.jonathan.iotmedicineapp;

import android.os.Bundle;

/**
 * Created by Jonathan on 2/12/2016.
 */

public class Settings extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        initDrawer();
    }
}
