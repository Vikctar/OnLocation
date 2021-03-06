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

public class PersonalServices extends Fragment {

    //Define an ArrayAdapter
    private ArrayAdapter<String> mPersonalServicesAdapter;

    // public constructor
    public PersonalServices() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the rootView for the fragment has been created, it's time to
        // populate the ListView with some data

        // create the dat for the ListView
        String[] personalServicesArray = {
                "Beauty Salons", "Hair Specialists",
                "Spa & Massage",
                "Podiatrists",
                "Tailoring & Repairs",
                "Laundry", "Shoe Repairs",
                "Cleaning & Hygiene"
        };

        List<String> personalServices = new ArrayList<>(Arrays.asList(personalServicesArray));

        // Now that we have some data, create an array adapter
        // which will take some data from source and use it to
        // populate the ListView it's attached to
        mPersonalServicesAdapter =
                new ArrayAdapter<String>(
                        // The current context (this fragment's parent activity)
                        getActivity(),
                        // The ID of the list item to populate
                        R.layout.list_item,
                        // The ID of the text view to populate
                        R.id.list_item_textView,
                        // data
                        personalServices
                );

        // Get a reference to the listView and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mPersonalServicesAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String personal = mPersonalServicesAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, personal);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
