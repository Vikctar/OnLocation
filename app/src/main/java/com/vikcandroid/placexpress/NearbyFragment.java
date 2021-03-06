package com.vikcandroid.placexpress;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

    int baseId;

    // ListItems data
    ArrayList<HashMap<String, String>> placesListItems = new ArrayList<HashMap<String, String>>();


    // KEY Strings
    public static String KEY_REFERENCE = "reference"; // id of the place
    public static String KEY_NAME = "name"; // name of the place
    public static String KEY_ADDRESS = "address"; // address of the place
    public static String KEY_CATEGORY = "category"; // category of the place
    public static String KEY_VICINITY = "vicinity"; // Place area name
    public static String KEY_CATA = "other"; // Place area name

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
        final View rootView = inflater.inflate(R.layout.fragment_main_tabs, container, false);

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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String reference = ((TextView) rootView.findViewById(R.id.reference)).getText().toString();
                Intent intent = new Intent(getActivity(), Profile.class).putExtra(KEY_REFERENCE, reference);
                startActivity(intent);
            }
        });

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
//            pDialog = new ProgressDialog(getActivity());
//            pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Loading Nearby places..."));
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(false);
//            pDialog.getWindow().setGravity(Gravity.BOTTOM);
//            pDialog.show();
        }

        /**
         * getting Places JSON
         */
        protected String doInBackground(String... args) {
            // creating Places class object
            googlePlaces = new GooglePlaces();
            //nearPlaces = SplashScreenActivity.nearPlaces;
//
//            try {
//                // Separate your place types by PIPE symbol "|"
//                // If you want all types places make it as null
//                // Check list of types supported by google
//                //
//                String types = "cafe|restaurant|airport|atm|aquarium|bakery|bank|bar|beauty_salon|book_store|bus_station|" +
//                        "cafe|car_wash|car_repair|car_rental|casino|cemetery|church|dentist|courthouse|department_store|doctor" +
//                        "|electronics_store|embassy|finance|fire_station|florist|food|furniture_store|gas_station|general_contractor|" +
//                        "grocery_or_supermarket|gym|hair_care|hardware_store|health|hindu_temple|home_goods_store|hospital|insurance_agency|" +
//                        "jewelry_store|laundry|lawyer|library|liquor_store|local_government_office|lodging|mosque|movie_theater|museum|night_club|" +
//                        "pharmacy|physiotherapist|place_of_worship|" +
//                        "police|post_office|restaurant|school|shopping_mall|spa|stadium|store|taxi_stand|train_station|travel_agency|university|zoo"; // Listing places only restaurants
//
//
////                String keyword = "cafe|restaurant|airport|atm|bakery|bank|bar|beauty_salon|book_store|bus_station|" +
////                        "car_wash|car_repair|car_rental|casino|cemetery|church|dentist|doctor|university";// Listing places with keyword pizza
////                // Radius in meters - increase this value if you don't find any places
////                //double radius = 1000; // 1000 meters
//                String rankby = "distance";
//
//                // get nearest places ranked by distance
//                nearPlaces = googlePlaces.search(gps.getLatitude(),
//                        gps.getLongitude(), types,  rankby);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
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
//            pDialog.dismiss();
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed Places into LISTVIEW
                     * */
                    // Get json response status
                    //System.out.println("nearplaces status " + nearPlaces.status);
                    String status = nearPlaces.status;
                    String s1 = "";

                    // Check for all possible status
                    if (status.equals("OK")) {
                        // Successfully got places details
                        if (nearPlaces.results != null) {
                            // loop through each place
                            for (Place p : nearPlaces.results) {
                                HashMap<String, String> map = new HashMap<String, String>();

//                                if(!p.equals("store") && !p.equals("grocery_or_supermarket")) {
//                                    if(!p.equals("restaurant") && !p.equals("food"))
//                                    {
//                                        if(!p.equals("hospital") && !p.equals("health") && !p.equals("dentist") && !p.equals("doctor"))
//                                        {
//                                            if(!p.equals("bank") && !p.equals("atm") && !p.equals("finance"))
//                                            {
//                                                if(p.equals("lodging"))
//                                                {
//                                                    s1 = Integer.toString(R.drawable.icon_accomodation_2);
//                                                    KEY_CATA = "Accommodation";
//                                                    continue;
//                                                }
//                                                if(!p.equals("airport") && !p.equals("bus_station") && !p.equals("train_station") && !p.equals("travel_agency"))
//                                                {
//                                                    if(!p.equals("book_store") && !p.equals("bakery") && !p.equals("bakery") && !p.equals("hardware_store"))
//                                                    {
//                                                        if(!p.equals("hindu_temple") && !p.equals("mosque") && !p.equals("place_of_worship"))
//                                                        {
//                                                            if(!p.equals("library") && !p.equals("school"))
//                                                            {
//                                                                if(!p.equals("electronics_store") && !p.equals("electrician"))
//                                                                {
//                                                                    if(p.equals("beauty_salon"))
//                                                                    {
//                                                                        s1 = Integer.toString(R.drawable.ic_personal_2);
//                                                                        KEY_CATA = "Personal Services";
//                                                                    }
//                                                                } else
//                                                                {
//                                                                    s1 = Integer.toString(R.drawable.ic_industry_2);
//                                                                    KEY_CATA = "Industry";
//                                                                }
//                                                            } else
//                                                            {
//                                                                s1 = Integer.toString(R.drawable.ic_public_2);
//                                                                KEY_CATA = "Public Services";
//                                                            }
//                                                        } else
//                                                        {
//                                                            s1 = Integer.toString(R.drawable.ic_public_2);
//                                                            KEY_CATA = "Public Services";
//                                                        }
//                                                    } else
//                                                    {
//                                                        s1 = Integer.toString(R.drawable.ic_shopping_2);
//                                                        KEY_CATA = "Shopping";
//                                                    }
//                                                } else
//                                                {
//                                                    s1 = Integer.toString(R.drawable.ic_transport_2);
//                                                    KEY_CATA = "Travel";
//                                                }
//                                            } else
//                                            {
//                                                s1 = Integer.toString(R.drawable.ic_finance_2);
//                                                KEY_CATA = "Finance";
//                                            }
//                                        } else
//                                        {
//                                            s1 = Integer.toString(R.drawable.ic_healthcare_2);
//                                            KEY_CATA = "Health";
//                                        }
//                                    } else
//                                    {
//                                        s1 = Integer.toString(R.drawable.ic_food_2);
//                                        KEY_CATA = "Food and Drink";
//                                    }
//                                } else
//                                {
//                                    s1 = Integer.toString(R.drawable.ic_shopping_2);
//                                    KEY_CATA = "Shopping";
//                                }

                                // Place reference won't display in listview - it will be hidden
                                // Place reference is used to get "place full details"
                                map.put(KEY_REFERENCE, p.reference);

                                // Place name
                                map.put(KEY_NAME, p.name);
//                                map.put(KEY_CATEGORY, s1);
//
//                                map.put("KEY_C", KEY_CATA);

                                String s2 = p.name;
                                map.put(KEY_NAME, s2.replace("'", ""));
                                if(baseId == 0)
                                    map.put(KEY_ADDRESS, p.vicinity);
                                else
                                if(baseId == 1)
                                    map.put(KEY_ADDRESS, p.formatted_address);

                                // place vicinity
                                //map.put(KEY_VICINITY, p.vicinity);


                                // adding HashMap to ArrayList
                                placesListItems.add(map);
                            }
                            // list adapter
                            ListAdapter adapter = new SimpleAdapter(getActivity(), placesListItems,
                                    R.layout.nearby_list_item,
                                    new String[]{KEY_REFERENCE, KEY_NAME, KEY_ADDRESS}, new int[]{
                                    R.id.reference, R.id.name, R.id.desc});

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