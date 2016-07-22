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

public class Industry extends AppCompatActivity {
    ListView listView;

    // create the dat for the ListView
    String[] industryArray = {
            "Energy Suppliers",
            "Cleaning & Hygiene",
            "Farming",
            "General Engineering", "Engineers",
            "Industrial Automation",
            "Industrial Equipment",
            "Industrial Premises",
            "Industrial Services",
            "Industrial Supplies",
            "Manufacturing",
            "Product Development",
            "Warehousing & Storage",
            "Catering", "Food Industry",
            "Paper Products", "Oil & Gas",
            "Automotive", "Civil Engineering",
            "Pump Manufacturers", "Electrical Service",
            "Chemicals", "Science",
            "Copper Products", "Steel Products",
            "Aluminium Works", "Water Services",
            "Media & Radio"
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(industryArray));

    public ArrayAdapter<String> industryArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);

        industryArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.list_item,
                R.id.list_item_textView,
                stringList);

        listView.setAdapter(industryArrayAdapter);

    }
}
