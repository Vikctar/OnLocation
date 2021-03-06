package com.vikcandroid.placexpress.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.test.AndroidTestCase;

import com.vikcandroid.placexpress.utils.PollingCheck;

import java.util.Map;
import java.util.Set;

import data.BusinessContract;
import data.BusinessDBHelper;

/*
    These are functions and some test data to make it easier to test your database and
    Content Provider.  Note that you'll want your WeatherContract class to exactly match the one
    in our solution to use these as-given.
 */
public class TestUtilities extends AndroidTestCase {
    static final String TEST_CATEGORY = "COMPUTERS";
    static final String TEST_SUB_CATEGORY = "IT Services";

    static void validateCursor(String error, Cursor valueCursor, ContentValues expectedValues) {
        assertTrue("Empty cursor returned. " + error, valueCursor.moveToFirst());
        validateCurrentRecord(error, valueCursor, expectedValues);
        valueCursor.close();
    }

    static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
            String expectedValue = entry.getValue().toString();
            assertEquals("Value '" + entry.getValue().toString() +
                    "' did not match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }
    }

    /*
         Use this to create some default business values for your database tests.
     */
    static ContentValues createBusinessValues(long categoryRowId, long subCategoryRowId) {
        ContentValues businessValues = new ContentValues();
        businessValues.put(BusinessContract.BusinessEntry.COLUMN_CAT_KEY, categoryRowId);
        businessValues.put(BusinessContract.BusinessEntry.COLUMN_SUB_CAT_KEY, subCategoryRowId);
        businessValues.put(BusinessContract.BusinessEntry.COLUMN_BUSINESS_NAME, "FIBREROOT KENYA");
        businessValues.put(BusinessContract.BusinessEntry.COLUMN_PHONE, "0712345678");
        businessValues.put(BusinessContract.BusinessEntry.COLUMN_EMAIL, "support@fibreroot.com");
        businessValues.put(BusinessContract.BusinessEntry.COLUMN_BUILDING, "FIRE STATION");
        businessValues.put(BusinessContract.BusinessEntry.COLUMN_STREET, "TOM MBOYA" );
        businessValues.put(BusinessContract.BusinessEntry.COLUMN_BUSINESS_ID, 1);

        return businessValues;
    }

    /*
        You can uncomment this helper function once you have finished creating the
        CategoryEntry part of the BusinessContract.
     */
    static ContentValues createComputersCategoryValues() {
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(BusinessContract.CategoryEntry.COLUMN_CATEGORY_NAME, TEST_CATEGORY);
//        testValues.put(WeatherContract.LocationEntry.COLUMN_CITY_NAME, "North Pole");
//        testValues.put(WeatherContract.LocationEntry.COLUMN_COORD_LAT, 64.7488);
//        testValues.put(WeatherContract.LocationEntry.COLUMN_COORD_LONG, -147.353);

        return testValues;
    }

    /*
         Create some default sub-category values for database tests
     */
    static ContentValues createSubCategoryValues(long categoryRowId) {
        // Create a new map of values, where column names are the keys
        ContentValues subCatValues = new ContentValues();
        subCatValues.put(BusinessContract.SubCategoryEntry.COLUMN_CATEGORY_KEY, categoryRowId);
        subCatValues.put(BusinessContract.SubCategoryEntry.COLUMN_SUB_CATEGORY_NAME, TEST_SUB_CATEGORY);

        return subCatValues;
    }

    /*
        You can uncomment this function once you have finished creating the
        LocationEntry part of the WeatherContract as well as the WeatherDbHelper.
     */
    static long insertComputerCategoryValues(Context context) {
        // insert our test records into the database
        BusinessDBHelper dbHelper = new BusinessDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues testValues = TestUtilities.createComputersCategoryValues();

        long categoryRowId;
        categoryRowId = db.insert(BusinessContract.CategoryEntry.TABLE_NAME, null, testValues);

        // Verify we got a row back.
        assertTrue("Error: Failure to insert Computer Category Values", categoryRowId != -1);

        return categoryRowId;
    }

    /*
        Test for insertion into sub_cat table
     */
//    static long insertItServicesSubCatValues(Context context) {
//        // insert test records into the database
//        BusinessDBHelper dbHelper = new BusinessDBHelper(context);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues subCatValues = TestUtilities.createSubCategoryValues();
//
//        long subCatRowId;
//        subCatRowId = db.insert(BusinessContract.SubCategoryEntry.TABLE_NAME, null, subCatValues);
//
//        // Verify we got a row back
//        assertTrue("Error: Failure to insert IT Services Sub_Category value", subCatRowId != -1);
//
//        return subCatRowId;
//    }

    /*
        The functions we provide inside of TestProvider use this utility class to test
        the ContentObserver callbacks using the PollingCheck class that we grabbed from the Android
        CTS tests.

        Note that this only tests that the onChange function is called; it does not test that the
        correct Uri is returned.
     */
    static class TestContentObserver extends ContentObserver {
        final HandlerThread mHT;
        boolean mContentChanged;

        static TestContentObserver getTestContentObserver() {
            HandlerThread ht = new HandlerThread("ContentObserverThread");
            ht.start();
            return new TestContentObserver(ht);
        }

        private TestContentObserver(HandlerThread ht) {
            super(new Handler(ht.getLooper()));
            mHT = ht;
        }

        // On earlier versions of Android, this onChange method is called
        @Override
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            mContentChanged = true;
        }

        public void waitForNotificationOrFail() {
            // Note: The PollingCheck class is taken from the Android CTS (Compatibility Test Suite).
            // It's useful to look at the Android CTS source for ideas on how to test your Android
            // applications.  The reason that PollingCheck works is that, by default, the JUnit
            // testing framework is not running on the main Android application thread.
            new PollingCheck(5000) {
                @Override
                protected boolean check() {
                    return mContentChanged;
                }
            }.run();
            mHT.quit();
        }
    }

    static TestContentObserver getTestContentObserver() {
        return TestContentObserver.getTestContentObserver();
    }
}
