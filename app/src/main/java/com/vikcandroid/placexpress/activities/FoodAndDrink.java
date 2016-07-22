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

public class FoodAndDrink extends AppCompatActivity {
    ListView listView;


    // create the data for the listView
    String[] foodArray = {
            "Bakery", "Butcheries",
            "Bistros & Braisseries",
            "Cafes", "Catering Services",
            "Coffee houses",
            "Drinks", "Eat out",
            "Food Manufacturers",
            "Pubs and Clubs", "Restaurants",
            "Food Delivery", "Dairy Products",
            "Catering Equipment", "Food Retailers",
            "Wine & Dine",
            "Supermarket", "Groceries"
    };

    List<String> stringList = new ArrayList<>(Arrays.asList(foodArray));

    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_drink);

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
