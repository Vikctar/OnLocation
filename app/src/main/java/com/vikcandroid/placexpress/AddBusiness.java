package com.vikcandroid.placexpress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    String name = editName.getText().toString();
    String address = editAddress.getText().toString();
    String phone = editPhone.getText().toString();
    String email = editEmail.getText().toString();

    if(name.length()==0 || address.length()==0 || phone.length()==0||
            email.length()==0 /*|| editRemarks.getText().length()==0*/){
Toast.makeText(AddBusiness.this,"Fields must not be empty",Toast.LENGTH_LONG).show();

    }
    else{
        // loopj lib AsyncHttpClient
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();

        requestParams.add("business_name", name);
        requestParams.add("address", address);
        requestParams.add("phone", phone);
        requestParams.add("email", email);

        Toast.makeText(getApplicationContext(), "Posting", Toast.LENGTH_LONG).show();

        // post the fucking data
        asyncHttpClient.post("http://192.168.100.96/placexpress/add_business1.php", requestParams,
                new AsyncHttpResponseHandler()

                {
                    @SuppressWarnings("deprecation")
                    public void onSuccess(String content) {
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                    }
                }

        );

    }
}

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
