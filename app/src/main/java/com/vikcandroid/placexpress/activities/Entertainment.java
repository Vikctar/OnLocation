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

public class Entertainment extends AppCompatActivity {

    ListView listView;

    String[] entertainmentArray = {
            "Arts and Crafts",
            "Fashion",
            "Fine Arts",
            "Film, TV &* Video",
            "Gaming",
            "Theater and Musicals",
            "Parties and Events",
            "Performing Arts",
            "Photography & Video",
            "Media Production",
            "Casino", "Movie",
            "Nightclubs", "Lifestyle Management",
            "Pets", "Weddings",
            "Leisure", "Conferences",
            "Books & Magazines",
            "Cinemas", "Music",
            "Clothing & Accessories",
            "Radio",
            "Equipments", "Event Services"
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(entertainmentArray));

    public ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

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
