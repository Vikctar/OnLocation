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

public class Sports extends AppCompatActivity {
    ListView listView;

    // create the dat for the ListView
    String[] sportsArray = {
            "Fitness And Gyms", "Trophies",
            "Stadiums and Gymnasiums", "Skating Parks",
            "Swimming Pools", "Sports Equipments",
            "Sports Complex", "Camping & Outdoors",
            "Cruises", "Sports Clubs", "Travel & Tours",
            "Museums", "Tourist Information",
            "Yoga", "Sights To See",
            "Attractions", "Translation Services"
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(sportsArray));
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

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
