package com.example.jonathan.iotmedicineapp.Chart;

import android.os.Bundle;
import android.support.v7.widget.CardView;

import com.example.jonathan.iotmedicineapp.MainActivity;
import com.example.jonathan.iotmedicineapp.R;

/**
 * Created by Jonathan on 2/12/2016.
 */

public class ChartActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chartactivity);
        initDrawer();
        (new LineCardOne((CardView) findViewById(R.id.card1), getApplicationContext())).init();
    }

}
