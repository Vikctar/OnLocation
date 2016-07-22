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

public class Accommodation extends AppCompatActivity {

    ListView listView;

    // create the dat for the ListView
    String[] accommodationArray = {
            "Apartments", "Bed & Breakfast",
            "Camping & Caravan", "Cottages",
            "Guest Houses", "Hostels",
            "Hotels & Motels", "Lodgings",
            "Holiday Homes", "Self Catering Accommodation",
            "Specialist Accommodation", "Homes", "Equipment"
    };

    List<String> list = new ArrayList<>(Arrays.asList(accommodationArray));

    // declare an adapter
    public ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acommodation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                // Id of the layout to populate
                R.layout.list_item,
                // id of the text item
                R.id.list_item_textView,
                // data
                list);

        listView.setAdapter(arrayAdapter);
    }
}
