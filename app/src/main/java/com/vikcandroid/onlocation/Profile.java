package com.vikcandroid.onlocation;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Profile extends ActionBarActivity {

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
    public static class ProfileFragment extends Fragment {

        // Log tag
        private static final String LOG_TAG = Profile.class.getSimpleName();

        // Share Tag
        private static final String PROFILE_SHARE_HASHTAG = " #ONLocation";

        private String mNearbyStr;

        public ProfileFragment() {
            // Required for the share icon to appear in the action bar
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

            // The profile activity called via intent. Inspect the intent for nearby places data
            Intent intent = getActivity().getIntent();
            if (intent != null && intent.hasExtra(intent.EXTRA_TEXT)) {
               mNearbyStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                ((TextView) rootView.findViewById(R.id.profile_text)).setText(mNearbyStr);
            }
            return rootView;
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
