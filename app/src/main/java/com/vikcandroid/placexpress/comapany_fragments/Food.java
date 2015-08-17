package com.vikcandroid.placexpress.comapany_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vikcandroid.placexpress.Profile;
import com.vikcandroid.placexpress.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vikc on 4/30/15.
 */
public class Food extends Fragment {

    // define an array adapter that takes strings
    private ArrayAdapter<String> mFoodAdapter;

    // public constructor
    public Food() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create the rootView for the fragment
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the rootView has been created, populate
        // the listView with some data

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

        List<String> food = new ArrayList<>(Arrays.asList(foodArray));

        // Create an array adapter which will take our data
        // from source and use it to
        // populate the ListView it's attached to
        mFoodAdapter =
                new ArrayAdapter<String>(
                        // the current context (this fragment's parent activity)
                        getActivity(),
                        // The ID of the list item to populate
                        R.layout.list_item,
                        // ID of the text view to populate
                        R.id.list_item_textView,
                        // data
                        food
                );

        // Get a reference to the list view and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mFoodAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String foodie = mFoodAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, foodie);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
