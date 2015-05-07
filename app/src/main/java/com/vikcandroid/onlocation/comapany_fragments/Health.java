package com.vikcandroid.onlocation.comapany_fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vikcandroid.onlocation.Profile;
import com.vikcandroid.onlocation.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Health extends Fragment {
    // Define an ArrayAdapter
    private ArrayAdapter<String> mHealthAdapter;

    // public constructor
    public Health() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        //Once the rootView has been created, it's time to
        // populate the listView with some data

        // Create the data for the listView
        String[] healthArray = {
                "Hospitals", "Chemists", "Herbal Clinics",
                "Dentists", "Pharmaceutical", "Optometrists"
        };

        List<String> health = new ArrayList<>(Arrays.asList(healthArray));

        // Create the ArrayAdapter which will take some data from source and use it to
        // populate the ListView it is attached to
        mHealthAdapter =
                new ArrayAdapter<>(
                        // the current context (fragments parent activity)
                        getActivity(),
                        // The ID of the list item to populate
                        R.layout.list_item,
                        // The ID of the text view to populate
                        R.id.list_item_textView,
                        // data
                        health
                );

        // Get a reference to the list view and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mHealthAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String healthy = mHealthAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, healthy);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
