package com.vikcandroid.onlocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.Toast;


@SuppressWarnings("deprecation")
public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button shouldn't be enabled, since there's no hierarchical parent
        actionBar.setHomeButtonEnabled(false);

        // Specify that tabs will be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        // Set up the ViewPager with the sections adapter and setting up a listener for
        // when a user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // Alternatively, ActionBar.Tab#Select() can be used to do this if there is a reference
                // to the Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected
                actionBar.addTab(
                        actionBar.newTab()
                        .setText(mSectionsPagerAdapter.getPageTitle(i))
                        .setTabListener(this)
                );

        }

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    // First section of the app
                    return new PlaceholderFragment();

                case 1:
                    // The second tab
                    return new CategoriesSectionFragment();

                default:
                    // The third tab
                    return new AboutFragment();

            }



        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

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
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
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
                            R.layout.list_item_nearby,
                            // Id of text view to populate
                            R.id.list_item_nearby_textView,
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
                    Toast.makeText(getActivity(), nearby, Toast.LENGTH_SHORT).show();
                }
            });
            return rootView;
        }
    }

    public static class AboutFragment extends Fragment {

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

}
