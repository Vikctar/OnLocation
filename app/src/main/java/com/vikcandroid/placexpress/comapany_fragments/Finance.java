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


public class Finance extends Fragment {
    // Define an ArrayAdapter that will take Strings
    private ArrayAdapter<String> mFinanceAdapter;

    // Public constructor
    public Finance(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        // Once the rootView has been created, it's time to
        // populate the listView  with some data

        // Create the data for the listview
        String[] financeArray = {
                "Accounting & Fiscal", "Banking", "Business Finance",
                "Insurance", "Legal", "Personal Finance",
                "Associations", "Stock Brokers", "Solicitors & Lawyers",
                "Tax Agents", "Loans", "General Insurance", "Wills & Trusts",
                "Credit Union", "Business Management", "ATM", "Forex Bureaus",
                "Investment Companies", "Financial Activity",
        };

        List<String> financier = new ArrayList<>(Arrays.asList(financeArray));

        // Now we have some data, create an array Adapter
        // which will take some dta from source and use it to
        // populate the ListView it is attached to
        mFinanceAdapter =
                new ArrayAdapter<String>(
                        //The current context (this fragment's parent activity)
                        getActivity(),
                        // The ID of the list iem to populate
                        R.layout.list_item,
                        // The ID of the text view to populate
                        R.id.list_item_textView,
                        //data
                        financier
                );

        // Get a reference to tje listView and attach this adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.listView_company);
        listView.setAdapter(mFinanceAdapter);

        // Make the listView items clickable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String finance = mFinanceAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, finance);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
