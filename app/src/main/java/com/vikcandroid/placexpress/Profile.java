package com.vikcandroid.placexpress;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;


public class Profile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ProfileFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    @SuppressWarnings("deprecation")
    public static class ProfileFragment extends Fragment {
//        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
        boolean connection = false;

        int arraylength;
        // Log tag
        private static final String LOG_TAG = Profile.class.getSimpleName();

        // Share Tag
        private static final String PROFILE_SHARE_HASHTAG = " #placexpress";

        private String mNearbyStr;
        ListView business_list;
        String id;

        public ProfileFragment() {
            // Required for the share icon to appear in the action bar
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            business_list=(ListView) rootView.findViewById(R.id.bsn);

            // The profile activity called via intent. Inspect the intent for nearby places data
            Intent intent = getActivity().getIntent();
            if (intent != null && intent.hasExtra(intent.EXTRA_TEXT)) {
               mNearbyStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                //((TextView) rootView.findViewById(R.id.profile_text)).setText(mNearbyStr);
            }

            id=getActivity().getIntent().getStringExtra("ID");
//            nameValuePairs.add(new BasicNameValuePair("sub_cat", id));
            new GetHistory().execute();
            return rootView;
        }


        public class GetHistory extends
                AsyncTask<Void, Void, List<String>> {

            InputStream ins;
            String res = "";
            String s;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<String> doInBackground(Void... arg0) {
//Accessing the php in the local host directory
//The link should have the ip address of your machine if using a phone to test
//if ita emulator use 10.0.2.2/
//placexpress is the folder name
                try {
//                    HttpClient httpclient = new DefaultHttpClient();
//                    HttpPost httppost = new HttpPost("http://192.168.100.147/placexpress/get_business.php");
//                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//                    HttpResponse response = httpclient.execute(httppost);
//                    HttpEntity entity = response.getEntity();
//                    ins = entity.getContent();

                } catch (Exception e) {
                    connection = true;
                }

                return null;

            }

            @Override
            protected void onPostExecute(List<String> Items) {
                super.onPostExecute(Items);
                // pDialog.dismiss();
                if (connection) {
                    //Dialog to pop up if there is no connection or good data connection
                    AlertDialog.Builder ErorDialog = new AlertDialog.Builder(
                            getActivity());
                    ErorDialog.setTitle("Connection Failure");
                    ErorDialog.setMessage("Error while connecting to the  server");
                    ErorDialog.setCancelable(true);
                    ErorDialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface Edialog, int id) {
                                    Edialog.cancel();
                                }
                            });
                    AlertDialog alert = ErorDialog.create();
                    alert.show();
                }
                // converting response to string
                try {
                    //Data conversion from the server
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(ins, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");

                    }
                    ins.close();
                    res = sb.toString();

                } catch (Exception e) {
                }// parsing json data

                try {
//Creating a JSON array object to get the coming data in JSON format
                    JSONArray array = new JSONArray(res);
                    //Getting the size of the data in the table>>> No of rows in the table
                    arraylength = array.length();
//Initializing an the array strings
                    final String[] bsn_name = new String[arraylength];
                    final String[] bsn_phone = new String[arraylength];
                    final String[] bsn_location = new String[arraylength];
                    final String[] bsn_email = new String[arraylength];

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject json = array.getJSONObject(i);

                        // obtaining the name of the bsn from the Db
                        //business_name>>>>>>THe column name in the tables -----Should match
                        bsn_name[i] = json.getString("business_name");
                        // obtaining the phone number of the bsn from the Db
                        //business_phone>>>>>>THe column name in the tables
                        bsn_phone[i] = json.getString("business_phone");
                        //Same as above
                        bsn_location[i] = json.getString("business_location");
                        //same as above
                        bsn_email[i] = json.getString("business_email");

                    }


                    // setting the list adapter

                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,bsn_name);
                    //this will only set the name of the bsn to the list. you can add the others on the list by getting them from above ar

                    //array strings. For this you use a custom listview
                    business_list.setAdapter(adapter);
                    business_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int pos, long id) {
                            //Setting the list listener

                        }
                    });

                } catch (Exception e) {
                    //Catch any exceptions that may occur when loading from the database
                }
                s = null;
                res = null;

            }

        }




        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            // Inflate the menu; this adds items to the action bar if it's present
            inflater.inflate(R.menu.profile_fragment, menu);

            // Retrieve the share menu item
            MenuItem shareItem = menu.findItem(R.id.action_share);

            // Get the provider and hold on it to set/change the share intent.
            ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

            // Attach an intent to this ShareActionProvider. This can be updated anytime,
            // like when the user selects a new piece of data they might like to share.
            if (shareActionProvider != null) {
                shareActionProvider.setShareIntent(createShareBusinessIntent());
            } else {
                Log.d(LOG_TAG, "Share Action Provider is null?");
            }

        }
        // ShareBusinessIntent
        @SuppressWarnings("deprecation")
        private Intent createShareBusinessIntent() {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    mNearbyStr + PROFILE_SHARE_HASHTAG);

            return  shareIntent;
        }
    }
}
