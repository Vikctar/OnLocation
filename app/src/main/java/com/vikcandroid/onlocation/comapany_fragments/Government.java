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

public class Government extends Fragment {

    // Define an ArrayAdpter
    private ArrayAdapter<String> mGovtAdapter;

    // public constructor
    public Government() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the rootView has ben created, it's time to populate
        // the ListView with some data

        // create the data for the listView
        String[] govtArray = {
                "Embassies & Consulates", "Government Ministries",
                "NGOs", "Government Services", "Voluntary Organizations",
                "Groups & Charities"
        };

        List<String> govt = new ArrayList<>(Arrays.asList(govtArray));

        // Now that we have some data, create an array adapter
        // which will take some dat from source and use it to populate
        // the ListView it is attache to.
        mGovtAdapter =
                new ArrayAdapter<>(
                        // The current context (the fragment's parent activity)
                        getActivity(),
                        // The ID of the list item to populate
                        R.layout.list_item,
                        // The ID of the text view to populate
                        R.id.list_item_textView,
                        // data
                        govt
                );

        // Get a reference to the list view and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mGovtAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gova = mGovtAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, gova);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
