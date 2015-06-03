package com.vikcandroid.onlocation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ViewWeb extends ActionBarActivity {

    String  keyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
       keyId = getIntent().getStringExtra("KEY");
        Toast.makeText(getApplicationContext(),"hhh"+keyId,Toast.LENGTH_LONG).show();
        android.webkit.WebView webView = (android.webkit.WebView)findViewById(R.id.web_view);

        if (keyId == "1") {
            webView.loadUrl("file:///android_asset/privacy.html");
        }
        else if (keyId == "2")
            webView.loadUrl("file:///android_asset/terms.html");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
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
