package com.example.jonathan.iotmedicineapp.Inventory;

import android.os.Bundle;

import com.example.jonathan.iotmedicineapp.MainActivity;
import com.example.jonathan.iotmedicineapp.R;

/**
 * Created by Jonathan on 2/12/2016.
 */

public class DrugActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugactivity);

        initDrawer();
    }
}
