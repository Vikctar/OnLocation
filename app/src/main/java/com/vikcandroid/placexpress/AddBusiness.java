package com.vikcandroid.placexpress;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;


@SuppressWarnings("deprecation")
public class AddBusiness extends AppCompatActivity {

    Button Add;

    EditText editAddress;
    EditText editName;
    EditText editPhone;
    EditText editRemarks;
    EditText editEmail;
//    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
    boolean connection = false;


    Spinner spinnerCategory;
    Spinner spinnerSubCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        Add = (Button) findViewById(R.id.btnAdd);
        editName = (EditText) findViewById(R.id.edit_name);
        editAddress = (EditText) findViewById(R.id.edit_address);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        editEmail = (EditText) findViewById(R.id.edit_email);
        editRemarks = (EditText) findViewById(R.id.add_remark);
        spinnerCategory = (Spinner) findViewById(R.id.spinner_cat);
        spinnerSubCat = (Spinner) findViewById(R.id.spinner_sub_cat);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });
     }
public void getData(){

    if(editName.getText().length()==0||editAddress.getText().length()==0||editPhone.getText().length()==0||
            editEmail.getText().length()==0||
            editRemarks.getText().length()==0){
Toast.makeText(AddBusiness.this,"Fields must not be empty",Toast.LENGTH_LONG).show();

    }
    else{
//        nameValuePairs.add(new BasicNameValuePair("business_name", editName
//                .getText().toString().trim()));
//        nameValuePairs.add(new BasicNameValuePair("business_address",
//                editAddress.getText().toString()));
//        nameValuePairs.add(new BasicNameValuePair("business_phone",
//                editPhone.getText().toString()));
//        nameValuePairs.add(new BasicNameValuePair("business_email",
//                editEmail.getText().toString()));
//        nameValuePairs.add(new BasicNameValuePair("business_remarks",
//                editRemarks.getText().toString()));
        new AddBusinessAsync().execute();

    }
}

    //Async class to send data
   @SuppressWarnings("deprecation")
   public class AddBusinessAsync extends AsyncTask<Void, Void, String> {

        InputStream ins;
        String res = "";
        String s;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(AddBusiness.this);
            pDialog.setMax(5);
            pDialog.setMessage("Please wait.....");
            pDialog.setCancelable(false);

            pDialog.show();
        }

        @Override
        protected String  doInBackground(Void... arg0) {
//Accessing the php in the local host directory
//The link should have the ip address of your machine if using a phone to test
//if ita emulator use 10.0.2.2/
//placexpress is the folder name
            try {
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpPost httppost = new HttpPost("http://192.168.43.199/placexpress/add_business1.php");
//                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//                HttpResponse response = httpclient.execute(httppost);
//                HttpEntity entity = response.getEntity();
//                ins = entity.getContent();

            } catch (Exception e) {
                connection = true;
            }

            return null;

        }

        @Override
        protected void onPostExecute(String Items) {
            super.onPostExecute(Items);
             pDialog.dismiss();
            if (connection) {
                //Dialog to pop up if there is no connection or good data connection
                AlertDialog.Builder ErrorDialog = new AlertDialog.Builder(
                        AddBusiness.this);
                ErrorDialog.setTitle("Connection Failure");
                ErrorDialog.setMessage("Error while connecting to the  server");
                ErrorDialog.setCancelable(false);
                ErrorDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface Edialog, int id) {
                                Edialog.cancel();
                            }
                        });
                AlertDialog alert = ErrorDialog.create();
                alert.show();
            }
            // converting response to string
            try {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(ins, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                ins.close();
                String res1 = sb.toString();

                if (res1.trim().equalsIgnoreCase("success")) {
                    Toast.makeText(AddBusiness.this,"successful",Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(AddBusiness.this,"Failed !!",Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
            }// parsing json data
//
//            try {
////Creating a JSON array object to get the coming data in JSON format
//                JSONArray array = new JSONArray(res);
//                //Getting the size of the data in the table>>> No of rows in the table
//                arraylength = array.length();
////Initializing an the array strings
//                final String[] bsn_name = new String[arraylength];
//                final String[] bsn_phone = new String[arraylength];
//                final String[] bsn_location = new String[arraylength];
//                final String[] bsn_email = new String[arraylength];
//
//                for (int i = 0; i < array.length(); i++) {
//                    JSONObject json = array.getJSONObject(i);
//
//                    // obtaining the name of the bsn from the Db
//                    //business_name>>>>>>THe column name in the tables -----Should match
//                    bsn_name[i] = json.getString("business_name");
//                    // obtaining the phone number of the bsn from the Db
//                    //business_phone>>>>>>THe column name in the tables
//                    bsn_phone[i] = json.getString("business_phone");
//                    //Same as above
//                    bsn_location[i] = json.getString("business_location");
//                    //same as above
//                    bsn_email[i] = json.getString("business_email");
//
//                }
//
//
//                // setting the list adapter
//
//                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item1,bsn_name);
//                //this will only set the name of the bsn to the list. you can add the others on the list by getting them from above ar
//
//                //array strings. For this you use a custom listview
//                business_list.setAdapter(adapter);
//                business_list.setOnItemClickListener(new OnItemClickListener() {
//
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view,
//                                            int pos, long id) {
//                        //Setting the list listener
//
//                    }
//                });
//
//            } catch (Exception e) {
//                //Catch any exceptions that may occur when loading from the database
//            }
            s = null;
            res = null;

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_business, menu);
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
}
