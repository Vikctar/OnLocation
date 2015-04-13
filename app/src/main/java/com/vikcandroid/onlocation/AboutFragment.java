package com.vikcandroid.onlocation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

/**
* Created by vikc on 4/14/15.
*/
public class AboutFragment extends Fragment {

    TableRow trAdd;
    TableRow trContact;
    TableRow trPrivacy;
    TableRow trTerms;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.about_us, container, false);

        trAdd = (TableRow) rootView.findViewById(R.id.tr_add_to_db);
        trContact = (TableRow) rootView.findViewById(R.id.tr_contact);
        trPrivacy = (TableRow) rootView.findViewById(R.id.tr_privacy);
        trTerms = (TableRow) rootView.findViewById(R.id.tr_terms);

        trPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;

    }
}
