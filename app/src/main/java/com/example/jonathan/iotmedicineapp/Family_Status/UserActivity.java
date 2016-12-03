package com.example.jonathan.iotmedicineapp.Family_Status;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.jonathan.iotmedicineapp.Chart.ChartActivity;
import com.example.jonathan.iotmedicineapp.MainActivity;
import com.example.jonathan.iotmedicineapp.R;

/**
 * Created by Jonathan on 2/12/2016.
 */

public class UserActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useractivity);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_USE_LOGO);

        initDrawer();
    }

    public void callMom(View view) {
        String posted_by = "12345678";

        String uri = "tel:" + posted_by.trim() ;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }


    public void callDad(View view) {
        String posted_by = "87654321";

        String uri = "tel:" + posted_by.trim() ;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void learnMore(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }

}
