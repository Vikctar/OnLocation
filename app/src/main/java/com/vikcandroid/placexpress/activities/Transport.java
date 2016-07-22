package com.vikcandroid.placexpress.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vikcandroid.placexpress.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transport extends AppCompatActivity {

    ListView listView;
    // create the data for the listView
    String[] travelArray = {
            "Airports", "Bicycles",
            "Flights", "Air Freight", "Parking Services",
            "Taxis", "Matatus", "Marine Services",
            "Travel Agents", "Bus Charters",
            "Petrol Stations", "Train", "Transportation/Logistics",
            "Couriers", "Shipping Agencies", "Sea & Haulage", "Liquid Transport",
            "Tours & Travel", "Vehicle Hire", "Car Rental", "Visa Agencies"
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(travelArray));
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
