package com.vikcandroid.placexpress.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vikcandroid.placexpress.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Automotive extends AppCompatActivity {

    ListView listView;

    // create the dat for the ListView
    String[] autoArray = {
            "Car Dealerships", "Car Parts and Accessories",
            "Car Repair", "New & Used Cars", "Car Rentals",
            "Motor Mechanics", "Tyres", "Auto Electricians",
            "Auto Air Cons", "Motorbikes", "Diving Schools",
            "Car Wash", "Vehicle Services", "Emergency & Breakdown",
            "Clearing & Forwarding", "Fuel Dealers", "Vehicle Maintenance",
            "Car Customization", "Parking Services", "Storage & Warehousing"
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(autoArray));

    // ArrayAdapter
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automotive);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                //Id of the layout to populate
                R.layout.list_item,
                // id of the text item
                R.id.list_item_textView,
                // data
                stringList);

        // set the adapter on the list view
        listView.setAdapter(arrayAdapter);
    }
}
