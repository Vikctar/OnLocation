package com.vikcandroid.onlocation;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import Network.AlertDialogManager;
import Network.ConnectionDetector;
import Network.GPSTracker;
import Network.GooglePlaces;
import Network.Place;
import Network.PlacesList;

/**
 * A placeholder fragment containing a simple view.
 */
public class NearbyFragment extends Fragment {

    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Google Places
    GooglePlaces googlePlaces;

    // Places List
    PlacesList nearPlaces;

    // GPS Location
    GPSTracker gps;

    // Progress dialog
    ProgressDialog pDialog;

    ListView lv;

    // ListItems data
    ArrayList<HashMap<String, String>> placesListItems = new ArrayList<HashMap<String, String>>();


    // KEY Strings
    public static String KEY_REFERENCE = "reference"; // id of the place
    public static String KEY_NAME = "name"; // name of the place
    public static String KEY_ADDRESS = "address"; // address of the place
    public static String KEY_CATEGORY = "category"; // category of the place
    public static String KEY_VICINITY = "vicinity"; // Place area name

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

//        // Create some dummy data for the list view
//        String[] nearbyArray = {
//                "Place  1  -  10 metres",
//                "Place  2  -  20 metres",
//                "Place  3  -  30 metres",
//                "Place  4  -  40 metres",
//                "Place  5  -  50 metres",
//                "Place  6  -  60 metres",
//                "Place  7  -  70 metres"
//        };
//
//        List<String> nearbyPlaces = new ArrayList<>(Arrays.asList(nearbyArray));

        // Now that we have some dummy nearby data, create an ArrayAdapter.
        // The ArrayAdapter will take data from a source (like the dummy nearby data)
        // and use it to populate the ListView it's attached to.
//        nearbyAdapter =
//                new ArrayAdapter<>(
//                        // The current context (this fragment's parent activity)
//                        getActivity(),
//                        // ID of the list item to populate
//                        R.layout.list_item,
//                        // Id of text view to populate
//                        R.id.list_item_textView,
//                        // Nearby data
//                        new ArrayList<String>()
//                );

        // Get a reference to the list view, and attach this adapter to it
//        ListView listView = (ListView) rootView.findViewById(R.id.listView_nearby);
//        listView.setAdapter(nearbyAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String nearby = nearbyAdapter.getItem(position);
//                Intent intent = new Intent(getActivity(), Profile.class).putExtra(Intent.EXTRA_TEXT, nearby);
//                startActivity(intent);
//            }
//        });
        lv = (ListView) rootView.findViewById(R.id.listView_nearby);

        nearPlaces = Splash.nearPlaces;
        gps = Splash.gps;

        // calling background Async task to load Google Places
        // After getting places from Google all the data is shown in listview
        new LoadPlaces().execute();
        return rootView;
    }



    /**
     * Background Async Task to Load Google places
     */
    class LoadPlaces extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * getting Places JSON
         */
        protected String doInBackground(String... args) {
            // creating Places class object
            googlePlaces = new GooglePlaces();
            //nearPlaces = SplashScreenActivity.nearPlaces;
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * and show the data in UI
         * Always use runOnUiThread(new Runnable()) to update UI from background
         * thread, otherwise you will get error
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            //pDialog.dismiss();
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed Places into LISTVIEW
                     * */
                    // Get json response status
                    //System.out.println("nearplaces status " + nearPlaces.status);
                    String status = nearPlaces.status;

                    // Check for all possible status
                    if (status.equals("OK")) {
                        // Successfully got places details
                        if (nearPlaces.results != null) {
                            // loop through each place
                            for (Place p : nearPlaces.results) {
                                HashMap<String, String> map = new HashMap<String, String>();

                                // Place reference won't display in listview - it will be hidden
                                // Place reference is used to get "place full details"
                                map.put(KEY_REFERENCE, p.reference);

                                // Place name
                                map.put(KEY_NAME, p.name);

                                // place vicinity
                                //map.put(KEY_VICINITY, p.vicinity);


                                // adding HashMap to ArrayList
                                placesListItems.add(map);
                            }
                            // list adapter
                            ListAdapter adapter = new SimpleAdapter(getActivity(), placesListItems,
                                    R.layout.nearby_list_item,
                                    new String[]{KEY_REFERENCE, KEY_NAME}, new int[]{
                                    R.id.reference, R.id.name});

                            // Adding data into listview
                            lv.setAdapter(adapter);
                        }
                    } else if (status.equals("ZERO_RESULTS")) {
                        // Zero results found
                        alert.showAlertDialog(getActivity(), "Places Error",
                                "Sorry no places found.",
                                false);
                    } else if (status.equals("UNKNOWN_ERROR")) {
                        alert.showAlertDialog(getActivity(), "Places Error",
                                "Sorry unkn .own error occured.",
                                false);
                    } else if (status.equals("OVER_QUERY_LIMIT")) {
                        alert.showAlertDialog(getActivity(), "Places Error",
                                "Sorry query limit to google places is reached",
                                false);
                    } else if (status.equals("REQUEST_DENIED")) {
                        alert.showAlertDialog(getActivity(), "Places Error",
                                "Sorry error occured. Request is denied",
                                false);
                    } else if (status.equals("INVALID_REQUEST")) {
                        alert.showAlertDialog(getActivity(), "Places Error",
                                "Sorry error occured. Invalid Request",
                                false);
                    } else {
                        alert.showAlertDialog(getActivity(), "Places Error",
                                "Sorry error occured.",
                                false);
                    }
                }
            });

        }

    }

}