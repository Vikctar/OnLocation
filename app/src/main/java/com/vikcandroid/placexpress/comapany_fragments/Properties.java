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

public class Properties extends Fragment {

    // Define an ArrayAdapter
    private ArrayAdapter<String> mPropertyAdapter;

    // public constructor
    public Properties() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the rootView for the fragment has been created
        // it's time to populate the listView with some data

        // Create the data for the listView
        String[] propertiesArray = {
                "Auctions", "Estate Agents",
                "Property Consultants", "Property Maintenance",
                "Removals & Relocation", "Ministry of Lands",
                "Ministry of Housing",
                "Security & Safety", "Home Improvement",
                "Letting Agents", "Interior Design",
                "Renovation", "Cleaning",
                "Online Property Listing", "Overseas Property",
                "Commercial Property", "Property Development"
        };

        List<String> property = new ArrayList<>(Arrays.asList(propertiesArray));

        // Now that we have some data, create an ArrayAdapter
        // which will take some data from source and use it to
        // populate the ListView it's attached to
        mPropertyAdapter =
                new ArrayAdapter<String>(
                        // The current context (the fragment's parent activity)
                        getActivity(),
                        // The ID of the list item to populate
                        R.layout.list_item,
                        // The ID of the text view to populate
                        R.id.list_item_textView,
                        // data
                        property
                );

        // Get a reference to the list view and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mPropertyAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String properties = mPropertyAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, properties);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
