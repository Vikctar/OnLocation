package com.vikcandroid.onlocation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;

import Network.AlertDialogManager;
import Network.ConnectionDetector;
import Network.GPSTracker;
import Network.GooglePlaces;
import Network.PlacesList;


public class Splash extends Activity {

    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Google Places
    GooglePlaces googlePlaces;

    // Places List
    public static PlacesList nearPlaces;

    // GPS Location
    public static GPSTracker gps;

    // Progress dialog
    ProgressDialog pDialog;


    public static boolean noGo = true;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            // Internet Connection is not present

            alert.showAlertDialog(Splash.this, "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        // creating GPS Class object
        gps = new GPSTracker(this);

        // check if GPS location can get
        if (gps.canGetLocation()) {
            Log.d("Your Location", "latitude:" + gps.getLatitude() + ", longitude: " + gps.getLongitude());
        } else {
            // Can't get user's current location
            alert.showAlertDialog(Splash.this, "GPS Status",
                    "Couldn't get location information. Please enable GPS",
                    false);
            // stop executing code by return
            return;
        }


        noGo = false;

        new Handler().postDelayed(new Runnable() {

            // Showing splash screen with a timer
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // calling background Async task to load Google Places
                new LoadPlaces().execute();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_splash);
    }

    /**
     * Background Async Task to Load Google places
     * */
    class LoadPlaces extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Splash.this);
            pDialog.setMessage(Html.fromHtml("<b>Search</b><br/>Loading Nearby places..."));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.getWindow().setGravity(Gravity.BOTTOM);
            pDialog.show();
        }

        /**
         * getting Places JSON
         * */
        protected String doInBackground(String... args) {
            // creating Places class object
            googlePlaces = new GooglePlaces();

            try {
                // Separeate your place types by PIPE symbol "|"
                // If you want all types places make it as null
                // Check list of types supported by google
                //
                String types = "cafe|restaurant|airport|atm|aquarium|bakery|bank|bar|beauty_salon|book_store|bus_station|" +
                        "cafe|car_wash|car_repair|car_rental|casino|cemetery|church|dentist|courthouse|department_store|doctor" +
                        "|electronics_store|embassy|finance|fire_station|florist|food|furniture_store|gas_station|general_contractor|" +
                        "grocery_or_supermarket|gym|hair_care|hardware_store|health|hindu_temple|home_goods_store|hospital|insurance_agency|" +
                        "jewelry_store|laundry|lawyer|library|liquor_store|local_government_office|lodging|mosque|movie_theater|museum|night_club|" +
                        "pharmacy|physiotherapist|place_of_worship|" +
                        "police|post_office|restaurant|school|shopping_mall|spa|stadium|store|taxi_stand|train_station|travel_agency|university|zoo"; // Listing places only restaurants


//                String keyword = "cafe|restaurant|airport|atm|bakery|bank|bar|beauty_salon|book_store|bus_station|" +
//                        "car_wash|car_repair|car_rental|casino|cemetery|church|dentist|doctor|university";// Listing places with keyword pizza
//                // Radius in meters - increase this value if you don't find any places
//                //double radius = 1000; // 1000 meters
                String rankby = "distance";

                // get nearest places ranked by distance
                nearPlaces = googlePlaces.search(gps.getLatitude(),
                        gps.getLongitude(), types,  rankby);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            pDialog.dismiss();

            // After completing http call
            // will close this activity and launch main activity
            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);

            // close this activity
            finish();

        }
    }


}
