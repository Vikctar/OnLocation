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

public class Construction extends AppCompatActivity {

    ListView listView;

    // create the dat for the ListView
    String[] constructionArray = {
            "Construction Consultants", "Building Maintenance",
            "Construction Material", "Construction Equipment",
            "Construction Workers", "Chemicals", "Building Materials",
            "Wrought Iron", "Locksmiths", "Contractors",
            "Construction Services", "Aluminium", "Construction Training",
            "Architectural Services", "Stainless Steel", "Water Supply",
            "Glass", "Gardeners",
            "Decorators", "Pest Control", "Cleaning & Hygiene"
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(constructionArray));

    // ArrayAdapter
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);

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
