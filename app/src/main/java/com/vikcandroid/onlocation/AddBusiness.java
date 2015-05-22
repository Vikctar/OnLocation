package com.vikcandroid.onlocation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class AddBusiness extends ActionBarActivity {

    Button Add;

    EditText editAddress;
    EditText editName;
    EditText editPhone;
    EditText editRemarks;
    EditText editEmail;

    Spinner spinnerCategory;

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
