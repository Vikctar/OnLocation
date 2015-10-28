package com.vikcandroid.placexpress;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.Toast;

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


        /**
         * Link leads to privacy policy page at
         * http://placexpress.com/privacypolicy.php
         */
        trPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacy = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://placexpress.com/privacypolicy.php"));
                startActivity(privacy);
            }
        });

        /**
         * Link leads to terms-ad-condition page at
         * http://placexpress.com/terms-and-conditions.php
         */
        trTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacy = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://placexpress.com/terms-and-conditions.php"));
                startActivity(privacy);
            }
        });

        /**
         * Open a compose page in gmail mobile app
         */
        trContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent contactIntent = new Intent(Intent.ACTION_SEND);
                    contactIntent.putExtra("android.intent.extra.EMAIL", new String[] {"support@placexpress.com"});
                    contactIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    contactIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact Support");
                    contactIntent.putExtra(Intent.EXTRA_TEXT, "\n placexpress Mobile App.");
                    contactIntent.setType("text/plain");
                    startActivity(contactIntent);

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Gmail Application Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        trAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(getActivity(), AddBusiness.class);
                startActivity(addIntent);
            }
        });

        return rootView;

    }
}
