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

public class Shopping extends AppCompatActivity {
    ListView listView;

    // create the data for the listView
    String[] shoppingArray = {
            "Book Stores", "Clothing", "Shoes",
            "Cigars & Tobacco", "Electronics",
            "Gifts/Accessories", "Home and Garden",
            "Kids Store",
            "Watches & Jewellery", "Food Stuff",
            "Malls", "Beauty Shops",
            "Furniture", "Fashion Boutiques",
            "Textile", "Food",
            "Duty Free Shops", "Retail/Department stores",
            "Music",
            "Phones & Tablets", "Sports & Outdoors",
            "Toys & Games", "Shopping Centers",
            "Optical Shops", "Gadgets"
    };

    List<String> shopper = new ArrayList<>(Arrays.asList(shoppingArray));
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                //Id of the layout to populate
                R.layout.list_item,
                // id of the text item
                R.id.list_item_textView,
                // data
                shopper);

        // set the adapter on the list view
        listView.setAdapter(arrayAdapter);
    }
}
