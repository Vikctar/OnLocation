package com.vikcandroid.onlocation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class BusinessServices extends Fragment {

    // Define an ArrayAdapter that takes Strings
    public ArrayAdapter<String> mBusinessAdapter;

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
    public static BusinessServices newInstance(int sectionNumber) {
        BusinessServices fragment = new BusinessServices();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    */

    public BusinessServices() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        //Once the root view for the fragment has been created, it's time to
        // populate the ListView with some data

        // Create the data for the list view
        String[] businessArray = {
                "Advertising", "Business Communications",
                "Events and Conferences", "General Office Services",
                "Human Resources", "Import and Export Agents",
                "Management Consultants", "Marketing",
                "Offices and Office Space", "Public Relations",
                "Retail Services", "Sales Management", "Staff Training"

        };

        List<String> businessServices = new ArrayList<>(Arrays.asList(businessArray));

        // Now that we have some data, create an ArrayAdapter.
        // The ArrayAdapter will take some data from a source and use it to
        // populate the ListView it's attached to.
        mBusinessAdapter =
                new ArrayAdapter<String>(
                        // The current context (this fragment's parent activity)
                        getActivity(),
                        // ID of the list item to populate
                        R.layout.list_item,
                        // ID of the text view to populate
                        R.id.list_item_textView,
                        // data
                        businessServices
                );

        // Get a reference to the list view and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mBusinessAdapter);

        return rootView;
    }
}
