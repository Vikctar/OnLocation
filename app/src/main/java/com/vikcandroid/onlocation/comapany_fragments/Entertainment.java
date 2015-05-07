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

/**
* Created by vikc on 4/30/15.
*/
public class Entertainment extends Fragment {

    // Define an array adapter that takes strings
    private ArrayAdapter<String> mEntertainmentAdapter;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    /*
    public static Entertainment newInstance(int sectionNumber) {
        Entertainment fragment = new Entertainment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    */

    // constructor
    public Entertainment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the root view for the fragment has been created, it's time to
        // populate the ListView with some data

        // Create the data for the list view
        String[] entertainmentArray = {
                "Arts and Crafts", "Fashion", "Fine Arts",
                "Gaming", "Theater and Musicals", "Parties and Events",
                "Performing Arts", "Bars", "Photography", "Media Production",
                "Casino", "Movie", "Museums", "Nightclubs"
        };

        List<String> entertainment = new ArrayList<>(Arrays.asList(entertainmentArray));

        // Now that we have some data, create an array adapter.
        // which will take some data from  a source and use it to
        // populate the ListView it's attached to
        mEntertainmentAdapter =
                new ArrayAdapter<>(
                        // The current context (this fragment's parent activity)
                        getActivity(),
                        // ID of the list item to populate
                        R.layout.list_item,
                        // ID of the text view to populate
                        R.id.list_item_textView,
                        //data
                        entertainment
                );

        // Get a reference to the list view and attach the adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mEntertainmentAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String enter = mEntertainmentAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, enter);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
