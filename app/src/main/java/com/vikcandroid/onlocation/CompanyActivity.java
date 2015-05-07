package com.vikcandroid.onlocation;

import java.util.Locale;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.vikcandroid.onlocation.comapany_fragments.Accommodation;
import com.vikcandroid.onlocation.comapany_fragments.Automotive;
import com.vikcandroid.onlocation.comapany_fragments.BusinessServices;
import com.vikcandroid.onlocation.comapany_fragments.ComputersAndInternet;
import com.vikcandroid.onlocation.comapany_fragments.Construction;
import com.vikcandroid.onlocation.comapany_fragments.Entertainment;
import com.vikcandroid.onlocation.comapany_fragments.Finance;
import com.vikcandroid.onlocation.comapany_fragments.Food;
import com.vikcandroid.onlocation.comapany_fragments.Government;
import com.vikcandroid.onlocation.comapany_fragments.Health;
import com.vikcandroid.onlocation.comapany_fragments.Industry;
import com.vikcandroid.onlocation.comapany_fragments.PersonalServices;
import com.vikcandroid.onlocation.comapany_fragments.Properties;
import com.vikcandroid.onlocation.comapany_fragments.PublicAndSocial;
import com.vikcandroid.onlocation.comapany_fragments.Shopping;
import com.vikcandroid.onlocation.comapany_fragments.SportsAndRecreation;
import com.vikcandroid.onlocation.comapany_fragments.Travel;


public class CompanyActivity extends ActionBarActivity {

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
        setContentView(R.layout.activity_company);


        // Create the adapter that will return a fragment for each of the
        // sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_company, menu);
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
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        // Controls the fragments being displayed.
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a different fragment based on the position.
            switch (position) {
                case 0:
                    return new BusinessServices();
                case 1:
                    return new Entertainment();
                case 2:
                    return new Shopping();
                case 3:
                    return new Food();
                case 4:
                    return new Construction();
                case 5:
                    return new Accommodation();
                case 6:
                    return new Health();
                case 7:
                    return new Finance();
                case 8:
                    return new Government();
                case 9:
                    return new Properties();
                case 10:
                    return new Travel();
                case 11:
                    return new Automotive();
                case 12:
                    return new Industry();
                case 13:
                    return new PublicAndSocial();
                case 14:
                    return new PersonalServices();
                case 15:
                    return new SportsAndRecreation();
                case 16:
                    return new ComputersAndInternet();
                default:
                    return new Construction();
            }
        }

        // Number of pages/fragments
        @Override
        public int getCount() {
            // Show  total pages.
            return 18;
        }

        // Get the page titles for the fragments
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.business_services).toUpperCase(l);
                case 1:
                    return getString(R.string.entertainment).toUpperCase(l);
                case 2:
                    return getString(R.string.shopping).toUpperCase(l);
                case 3:
                    return getString(R.string.food_drinks).toUpperCase(l);
                case 4:
                    return getString(R.string.buildings).toUpperCase(l);
                case 5:
                    return getString(R.string.accommodation).toUpperCase(l);
                case 6:
                    return getString(R.string.health).toUpperCase(l);
                case 7:
                    return getString(R.string.financial_services).toUpperCase(l);
                case 8:
                    return getString(R.string.government).toUpperCase(l);
                case 9:
                    return getString(R.string.properties).toUpperCase(l);
                case 10:
                    return getString(R.string.transport).toUpperCase(l);
                case 11:
                    return getString(R.string.automotive).toUpperCase(l);
                case 12:
                    return getString(R.string.industry).toUpperCase(l);
                case 13:
                    return getString(R.string.public_services).toUpperCase(l);
                case 14:
                    return getString(R.string.personal_services).toUpperCase(l);
                case 15:
                    return getString(R.string.sports).toUpperCase(l);
                case 16:
                    return getString(R.string.computers).toUpperCase(l);
                case 17:
                    return getString(R.string.construction).toUpperCase(l);
            }
            return null;
        }
    }


}
