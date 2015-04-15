package com.vikcandroid.onlocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NearbyFragment extends Fragment {

    // define an ArrayAdapter that takes Strings
    public ArrayAdapter<String> nearbyAdapter;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NearbyFragment newInstance(int sectionNumber) {
        NearbyFragment fragment = new NearbyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public NearbyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_tabs, container, false);

        // Once the root view for the Fragment has been created, it's time to
        // populate the ListView with some dummy data.

        // Create some dummy data for the list view
        String[] nearbyArray = {
                "Place  1  -  10 metres",
                "Place  2  -  20 metres",
                "Place  3  -  30 metres",
                "Place  4  -  40 metres",
                "Place  5  -  50 metres",
                "Place  6  -  60 metres",
                "Place  7  -  70 metres"
        };

        List<String> nearbyPlaces = new ArrayList<>(Arrays.asList(nearbyArray));

        // Now that we have some dummy nearby data, create an ArrayAdapter.
        // The ArrayAdapter will take data from a source (like the dummy nearby data)
        // and use it to populate the ListView it's attached to.
        nearbyAdapter =
                new ArrayAdapter<>(
                        // The current context (this fragment's parent activity)
                        getActivity(),
                        // ID of the list item to populate
                        R.layout.list_item,
                        // Id of text view to populate
                        R.id.list_item_textView,
                        // Nearby data
                        nearbyPlaces
                );

        // Get a reference to the list view, and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_nearby);
        listView.setAdapter(nearbyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nearby = nearbyAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, nearby);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
