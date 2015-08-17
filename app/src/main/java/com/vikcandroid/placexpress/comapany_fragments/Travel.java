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

public class Travel extends Fragment {

    // Define an ArrayAdapter
    private ArrayAdapter<String> mTravelAdapter;


    // public constructor
    public Travel() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the rootView has been created, it's time to
        // populate the listView with some data

        // create the data for the listView
        String[] travelArray = {
                "Airports", "Bicycles",
                "Flights", "Air Freight", "Parking Services",
                "Taxis", "Matatus", "Marine Services",
                "Travel Agents", "Bus Charters",
                "Petrol Stations", "Train", "Transportation/Logistics",
                "Couriers", "Shipping Agencies", "Sea & Haulage", "Liquid Transport",
                "Tours & Travel", "Vehicle Hire", "Car Rental", "Visa Agencies"
        };

        List<String> travel = new ArrayList<>(Arrays.asList(travelArray));

        // Now that we have some data, create an ArrayAdapter
        // which will take some data from source and use it to
        // populate the ListView it's attached to
        mTravelAdapter =
                new ArrayAdapter<>(
                      // the current context (the fragment's parent activity)
                        getActivity(),
                        // The ID of the list item to populate
                        R.layout.list_item,
                        // ID of the textView to populate
                        R.id.list_item_textView,
                        // data
                        travel
                );

        // Get a reference to ListView and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mTravelAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String trav = mTravelAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, trav);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
