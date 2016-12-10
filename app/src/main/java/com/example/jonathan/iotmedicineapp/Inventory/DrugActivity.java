package com.example.jonathan.iotmedicineapp.Inventory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jonathan.iotmedicineapp.MainActivity;
import com.example.jonathan.iotmedicineapp.R;
import com.example.jonathan.iotmedicineapp.Utilities.Inventory;
import com.example.jonathan.iotmedicineapp.Utilities.Medicine;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jonathan on 2/12/2016.
 */

public class DrugActivity extends MainActivity {
    private Inventory medInventory = new Inventory();
    private List<Medicine> inventoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugactivity);

        initDrawer();

        populateMedicineList();
        populateMedicineView();
        registerClickCallback();

    }

    private void registerClickCallback() {
        ListView medList = (ListView) findViewById(R.id.medicineListview);
        medList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Medicine clickedMed = inventoryList.get(i);
                String url = clickedMed.getWebsite();
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse(url));
                startActivity(webIntent);
            }
        });
    }

    private void populateMedicineList() {
        final FirebaseDatabase medicineDatabase = FirebaseDatabase.getInstance();
        DatabaseReference medicineRef = medicineDatabase.getReference("Inventory");

        medicineRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Medicine newMedicine = dataSnapshot.getValue(Medicine.class);
                medInventory.addMedicine(newMedicine.getName(), newMedicine.getAmount(), newMedicine.getWebsite());
                inventoryList.add(newMedicine);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void populateMedicineView() {
        ArrayAdapter<Medicine> medicineArrayAdapter = new medListAdapter();
        ListView medicineList = (ListView) findViewById(R.id.medicineListview);
        medicineList.setAdapter(medicineArrayAdapter);
    }

    private class medListAdapter extends ArrayAdapter<Medicine> {
        public medListAdapter() {
            super(DrugActivity.this, R.layout.medicine_view, inventoryList);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            String status = "";
            View medicineView = convertView;
            if (medicineView == null) {
                medicineView = getLayoutInflater().inflate(R.layout.medicine_view, parent, false);
            }

            Medicine currentMed = inventoryList.get(position);

            TextView makeText = (TextView) medicineView.findViewById(R.id.medicine_name);
            makeText.setText(currentMed.getName());

            TextView makeStatus = (TextView) medicineView.findViewById(R.id.medicine_status);
            if (currentMed.getAmount() == true) status = "Sufficient";
            else status = "Refill soon";
            makeStatus.setText(status);

            return medicineView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drugactivity_menu, menu);
        return true;
    }


}
