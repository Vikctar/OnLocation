package com.vikcandroid.placexpress.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vikcandroid.placexpress.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessServices extends AppCompatActivity {

    ListView listView;

    // Create the data for the list view
    String[] businessArray = {
            "Advertising",
            "Business Communications",
            "Events and Conferences",
            "General Office Services", "Office Services",
            "Human Resources", "Import and Export",
            "Management Consultants", "Marketing",
            "Offices and Office Space", "Public Relations",
            "Retail Services", "Sales Management",
            "Training", "Gas & Energy",
            "Business Travel Agencies", "Accounting & Fiscality",
            "Secretarial Services", "Trading",
            "Storage & Warehousing", "Environmental Services",
            "Business Transport", "Money Banking & Insurance",
            "Repairs & Maintenance", "Travel & Tours",
            "Security", "Brokers", "Overseas Business",
            "Distributors", "Printing", "Courier Services",
            "Outsourcing", "Business Parks",
            "Translation Services", "Dry Cleaning", "Small Businesses",
            "Market Research", "General Business", "Consultants",
            "Safety Consultants"


    };

    List<String> businessList = new ArrayList<>(Arrays.asList(businessArray));

    // Declare array adapter of type String
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_services);

        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                // ID of the list item to populate
                R.layout.list_item,
                // ID ot the text view
                R.id.list_item_textView,
                // the data to use
                businessList);

        listView.setAdapter(arrayAdapter);
    }
}
