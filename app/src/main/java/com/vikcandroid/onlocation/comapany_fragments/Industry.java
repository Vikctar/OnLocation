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


public class Industry extends Fragment {

    //Define an ArrayAdapter
    private ArrayAdapter<String> mIndustryAdapter;

    // public constructor
    public Industry() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the rootView for the fragment has been created, it's time to
        // populate the ListView with some data

        // create the dat for the ListView
        String[] industryArray = {
                "Energy Suppliers", "Cleaning & Hygene", "Farming",
                "General Engineering", "Industrial Automation", "Industrial Equipment",
                "Industrial Premises", "Industrial Services", "Industrial Supplies",
                "Manufacturing", "Product Development", "Warehousing & Storage"
        };

        List<String> industry = new ArrayList<>(Arrays.asList(industryArray));

        // Now that we have some data, create an array adapter
        // which will take some data from source and use it to
        // populate the ListView it's attached to
        mIndustryAdapter =
                new ArrayAdapter<String>(
                        // The current context (this fragment's parent activity)
                        getActivity(),
                        // The ID of the list item to populate
                        R.layout.list_item,
                        // The ID of the text view to populate
                        R.id.list_item_textView,
                        // data
                        industry
                );

        // Get a reference to the listView and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mIndustryAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String inda = mIndustryAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, inda);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
