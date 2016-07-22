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

public class Computers extends AppCompatActivity {

    ListView listView;

    // create the dat for the ListView
    String[] computersArray = {
            "Computer Retailers",
            "Computer Repairs",
            "Computer Games",
            "Communication",
            "Computer Rentals",
            "Computer Training",
            "Data Recovery and Backup",
            "Domain Name Registration",
            "Networking",
            "Internet Service Providers",
            "Online Content",
            "Web Designs",
            "Software Applications",
            "Web Hosting Services",
            "Internet Security",
            "Web Services",
            "Security", "Web Services",
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(computersArray));
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computers);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                // layout to populate
                R.layout.list_item,
                // id of text item
                R.id.list_item_textView,
                // data
                stringList);

        // set the adapter to a listView
        listView.setAdapter(arrayAdapter);
    }
}
