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

public class Automotive extends Fragment {
    //Define an ArrayAdapter
    private ArrayAdapter<String> mAutoAdapter;

    // public constructor
    public Automotive() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the rootView for the fragment has been created, it's time to
        // populate the ListView with some data

        // create the dat for the ListView
        String[] autoArray = {
                "Car Dealerships", "Car Parts and Accessories",
                "Car Repair", "New & Used Cars", "Car Rentals",
                "Motor Mechanics", "Tyres", "Auto Electricians",
                "Auto Air Cons", "Motorbikes", "Diving Schools",
                "Car Wash", "Vehicle Services", "Emergency & Breakdown",
                "Clearing & Forwarding", "Fuel Dealers", "Vehicle Maintenance",
                "Car Customization", "Parking Services", "Storage & Warehousing"
        };

        List<String> auto = new ArrayList<>(Arrays.asList(autoArray));

        // Now that we have some data, create an array adapter
        // which will take some data from source and use it to
        // populate the ListView it's attached to
        mAutoAdapter =
                new ArrayAdapter<>(
                        // The current context (this fragment's parent activity)
                        getActivity(),
                        // The ID of the list item to populate
                        R.layout.list_item,
                        // The ID of the text view to populate
                        R.id.list_item_textView,
                        // data
                        auto
                );

        // Get a reference to the listView and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mAutoAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String automate = mAutoAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, automate);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
