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

public class Health extends AppCompatActivity {
    ListView listView;

    // Create the data for the listView
    String[] healthArray = {
            "Hospitals", "Chemists",
            "Emergencies", "Herbal Clinics",
            "Home Health Care",
            "Dentists", "Medical Equipment",
            "Pharmaceutical", "Medical Specialists",
            "Fitness", "Doctors"
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(healthArray));

    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

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
