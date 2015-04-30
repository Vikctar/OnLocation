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
* Created by vikc on 4/30/15.
*/
public class Shopping extends Fragment {

    // define an array adapter that takes strings
    private ArrayAdapter<String> mShoppingAdapter;

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
    public static Shopping newInstance(int sectionNumber) {
        Shopping fragment = new Shopping();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    */

    // public constructor
    public Shopping() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the root view for the fragment has been created, it's time to
        // populate the listView with some data

        // create the data for the listView
        String[] shoppingArray = {
                "Book Stores", "Clothing", "Electronics",
                "Gifts/Accessories", "Home and Garden",
                "Kids Store", "Phone/Tablets",
                "Toys", "Jewellery"
        };

        List<String> shopper = new ArrayList<>(Arrays.asList(shoppingArray));

        // Now that we have some data, create an array adapter
        // which will take some data from source and use it to
        // populate the ListView it's attached to
        mShoppingAdapter =
                new ArrayAdapter<String>(
                          // the current context (this fragment's parent activity)
                        getActivity(),
                        // The ID of the list item to populate
                        R.layout.list_item,
                        // ID of the text view to populate
                        R.id.list_item_textView,
                        //data
                        shopper
                );

        // Get a reference to the list view and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mShoppingAdapter);

        return rootView;
    }
}
