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

public class Properties extends AppCompatActivity {
    ListView listView;

    String[] propertiesArray = {
            "Auctions", "Estate Agents",
            "Property Consultants", "Property Maintenance",
            "Removals & Relocation", "Ministry of Lands",
            "Ministry of Housing",
            "Security & Safety", "Home Improvement",
            "Letting Agents", "Interior Design",
            "Renovation", "Cleaning",
            "Online Property Listing", "Overseas Property",
            "Commercial Property", "Property Development"
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(propertiesArray));

    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);

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
