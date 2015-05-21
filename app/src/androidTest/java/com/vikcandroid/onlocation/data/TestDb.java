/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vikcandroid.onlocation.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.util.HashSet;

import data.BusinessContract;
import data.BusinessDBHelper;

public class TestDb extends AndroidTestCase {

    public static final String LOG_TAG = TestDb.class.getSimpleName();

    // Since we want each test to start with a clean slate
    void deleteTheDatabase() {
        mContext.deleteDatabase(BusinessDBHelper.DATABASE_NAME);
    }

    /*
        This function gets called before each test is executed to delete the database.  This makes
        sure that we always have a clean test.
     */
    public void setUp() {
        deleteTheDatabase();
    }

    /*
        Uncomment this test once you've written the code to create the Category
        table.  Note that you will have to have chosen the same column names that I did in
        my solution for this test to compile, so if you haven't yet done that, this is
        a good time to change your column names to match mine.

        Note that this only tests that the Category table has the correct columns.
     */
    public void testCreateDb() throws Throwable {
        // build a HashSet of all of the table names we wish to look for
        // Note that there will be another table in the DB that stores the
        // Android metadata (db version information)
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(BusinessContract.CategoryEntry.TABLE_NAME);
        tableNameHashSet.add(BusinessContract.SubCategoryEntry.TABLE_NAME);
        tableNameHashSet.add(BusinessContract.BusinessEntry.TABLE_NAME);

        mContext.deleteDatabase(BusinessDBHelper.DATABASE_NAME);
        SQLiteDatabase db = new BusinessDBHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());

        // have we created the tables we want?
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst());

        // verify that the tables have been created
        do {
            tableNameHashSet.remove(c.getString(0));
        } while( c.moveToNext() );

        // if this fails, it means that your database doesn't contain both the category entry
        // and business entry tables
        assertTrue("Error: Your database was created without both the category entry and business entry tables",
                tableNameHashSet.isEmpty());

        // now, do our tables contain the correct columns?
        c = db.rawQuery("PRAGMA table_info(" + BusinessContract.CategoryEntry.TABLE_NAME + ")",
                null);

        assertTrue("Error: This means that we were unable to query the database for table information.",
                c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> categoryColumnHashSet = new HashSet<String>();
        categoryColumnHashSet.add(BusinessContract.CategoryEntry._ID);
        categoryColumnHashSet.add(BusinessContract.CategoryEntry.COLUMN_CATEGORY_NAME);
//        categoryColumnHashSet.add(BusinessContract.CategoryEntry.COLUMN_COORD_LAT);
//        categoryColumnHashSet.add(BusinessContract.CategoryEntry.COLUMN_COORD_LONG);
//        categoryColumnHashSet.add(BusinessContract.CategoryEntry.COLUMN_LOCATION_SETTING);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            categoryColumnHashSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required category
        // entry columns
        assertTrue("Error: The database doesn't contain all of the required category entry columns",
                categoryColumnHashSet.isEmpty());
        db.close();
    }

    /*
        Here is where you will build code to test that we can insert and query the
        location database.  We've done a lot of work for you.  You'll want to look in TestUtilities
        where you can uncomment out the "createComputerCategoryValues" function.  You can
        also make use of the ValidateCurrentRecord function from within TestUtilities.
    */
    public void testCategoryTable() {
//        // First step: Get reference to writable database
//        // If there's an error in those massive SQL table creation Strings,
//        // errors will be thrown here when you try to get a writable database.
//        BusinessDBHelper dbHelper = new BusinessDBHelper(mContext);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        // Second step: Create ContentValues of what you want to insert
//        // (you can use the createBusinessValues TestUtilities function if you wish)
//        ContentValues businessValues = TestUtilities.createBusinessValues(categoryRowId, subCatRowId);
//
//        // Third Step: Insert ContentValues into database and get a row ID back
//        long businessRowId = db.insert(BusinessContract.BusinessEntry.TABLE_NAME, null, businessValues);
//        assertTrue(businessRowId != -1);
//
//        // Fourth Step: Query the database and receive a Cursor back
//        // A cursor is our primary interface to the query results
//        Cursor businessCursor = db.query(
//                BusinessContract.BusinessEntry.TABLE_NAME, // Table to query
//                null, // leaving "columns" null just returns all the columns.
//                null, // calls for "where" clause
//                null, // values for "where" clause
//                null, // columns to group by
//                null, // columns to filter row groups
//                null // sort order
//        );
//
//        // Move the cursor to the first valid database row and check to see if we have any rows
//        assertTrue( "Error: No Records returned from category query", businessCursor.moveToFirst() );
//
//        // Fifth Step: Validate the category query
//        TestUtilities.validateCurrentRecord("testInsertReadDb businessEntry failed to validate",
//                businessCursor, businessValues);
//
//        // Move the cursor to demonstrate that there is more than one record in the database
//        assertTrue( "Error: Only one record returned from business query", businessCursor.moveToFirst() );
//
//        // Sixth Step: Finally, close the cursor and database
//        businessCursor.close();
//        dbHelper.close();
//    }
//
//
//    /*
//        This is a helper method for the testBusinessTable quiz. You can move your
//        code from testLocationTable to here so that you can call this code from both
//        testBusinessTable and testCategoryTable.
//     */
//    public long insertCategory() {
//        // First step: Get reference to writable database
//        // If there's an error in those massive SQL table creation Strings,
//        // errors will be thrown here when you try to get a writable database.
//        BusinessDBHelper dbHelper = new BusinessDBHelper(mContext);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        // Second Step: Create ContentValues of what you want to insert
//        // (you can use the createComputersCategoryValues if you wish)
//        ContentValues testValues = TestUtilities.createComputersCategoryValues();
//
//        // Third Step: Insert ContentValues into database and get a row ID back
//        long categoryRowId;
//        categoryRowId = db.insert(BusinessContract.CategoryEntry.TABLE_NAME, null, testValues);
//
//        // Verify we got a row back.
//        assertTrue(categoryRowId != -1);
//
//        // Data's inserted.  IN THEORY.  Now pull some out to stare at it and verify it made
//        // the round trip.
//
//        // Fourth Step: Query the database and receive a Cursor back
//        // A cursor is your primary interface to the query results.
//        Cursor cursor = db.query(
//                BusinessContract.CategoryEntry.TABLE_NAME,  // Table to Query
//                null, // all columns
//                null, // Columns for the "where" clause
//                null, // Values for the "where" clause
//                null, // columns to group by
//                null, // columns to filter by row groups
//                null // sort order
//        );
//
//        // Move the cursor to a valid database row and check to see if we got any records back
//        // from the query
//        assertTrue( "Error: No Records returned from category query", cursor.moveToFirst() );
//
//        // Fifth Step: Validate data in resulting Cursor with the original ContentValues
//        // (you can use the validateCurrentRecord function in TestUtilities to validate the
//        // query if you like)
//        TestUtilities.validateCurrentRecord("Error: Location Query Validation Failed",
//                cursor, testValues);
//
//        // Move the cursor to demonstrate that there is only one record in the database
//        assertFalse( "Error: More than one record returned from location query",
//                cursor.moveToNext() );
//
//        // Sixth step: Close the cursor and database
//        cursor.close();;
//        db.close();
        insertCategory();

    }

    public long testSubCatTable() {
        // First insert the location, and then use the categoryRowId to insert
        // the subcategory. Make sure to cover as many failure cases as you can.

        // Instead of rewriting all of the code we've already written in testCategoryTable
        // we can move this code to insertCategory and then call insertCategory from both
        // tests. Why move it? We need the code to return the ID of the inserted category
        // and our testCategoryTable can only return void because it's a test.

        long categoryRowId = insertCategory();

        // Make sure we have a valid row ID.
        assertFalse("Error: Category Not Inserted Correctly", categoryRowId == -1L);

        // First step: Get reference to writable database
        // If there's an error in those massive SQL table creation Strings,
        // errors will be thrown here when you try to get a writable database.
        BusinessDBHelper dbHelper = new BusinessDBHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Second step: Create ContentValues of what you want to insert
        // (you can use the createBusinessValues TestUtilities function if you wish)
        ContentValues subCatValues = TestUtilities.createSubCategoryValues(categoryRowId);

        // Third Step: Insert ContentValues into database and get a row ID back
        long subCatRowId = db.insert(BusinessContract.SubCategoryEntry.TABLE_NAME, null, subCatValues);
        assertTrue(subCatRowId != -1);

        // Fourth Step: Query the database and receive a Cursor back
        // A cursor is our primary interface to the query results
        Cursor subCatCursor = db.query(
                BusinessContract.SubCategoryEntry.TABLE_NAME, // Table to query
                null, // leaving "columns" null just returns all the columns.
                null, // calls for "where" clause
                null, // values for "where" clause
                null, // columns to group by
                null, // columns to filter row groups
                null // sort order
        );

        // Move the cursor to the first valid database row and check to see if we have any rows
        assertTrue("Error: No Records returned from subcategory query", subCatCursor.moveToFirst());

        // Fifth Step: Validate the category query
        TestUtilities.validateCurrentRecord("testInsertReadDb subCategoryEntry failed to validate",
                subCatCursor, subCatValues);

        // Move the cursor to demonstrate that there is more than one record in the database
        assertTrue("Error: Only one record returned from subcategory query", subCatCursor.moveToFirst());

        // Sixth Step: Finally, close the cursor and database
        subCatCursor.close();
        dbHelper.close();

        return subCatRowId;

    }

    /*
        Here is where you will build code to test that we can insert and query the
        database. You'll want to look in TestUtilities
        where you can use the "createBusinessValues" function.  You can
        also make use of the validateCurrentRecord function from within TestUtilities.
     */
    public void testBusinessTable() {
        // First insert the location, and then use the categoryRowId to insert
        // the business. Make sure to cover as many failure cases as you can.

        // Instead of rewriting all of the code we've already written in testCategoryTable
        // we can move this code to insertCategory and then call insertCategory from both
        // tests. Why move it? We need the code to return the ID of the inserted category
        // and our testCategoryTable can only return void because it's a test.

        long categoryRowId = insertCategory();

        long subCatRowId = insertSubCategory();

        // Make sure we have a valid row ID.
        assertFalse("Error: Category Not Inserted Correctly", categoryRowId == -1L);

        // First step: Get reference to writable database
        // If there's an error in those massive SQL table creation Strings,
        // errors will be thrown here when you try to get a writable database.
        BusinessDBHelper dbHelper = new BusinessDBHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Second step: Create ContentValues of what you want to insert
        // (you can use the createBusinessValues TestUtilities function if you wish)
        ContentValues businessValues = TestUtilities.createBusinessValues(categoryRowId, subCatRowId);

        // Third Step: Insert ContentValues into database and get a row ID back
        long businessRowId = db.insert(BusinessContract.BusinessEntry.TABLE_NAME, null, businessValues);
        assertTrue(businessRowId != -1);

        // Fourth Step: Query the database and receive a Cursor back
        // A cursor is our primary interface to the query results
        Cursor businessCursor = db.query(
                BusinessContract.BusinessEntry.TABLE_NAME, // Table to query
                null, // leaving "columns" null just returns all the columns.
                null, // calls for "where" clause
                null, // values for "where" clause
                null, // columns to group by
                null, // columns to filter row groups
                null // sort order
        );

        // Move the cursor to the first valid database row and check to see if we have any rows
        assertTrue( "Error: No Records returned from category query", businessCursor.moveToFirst() );

        // Fifth Step: Validate the category query
        TestUtilities.validateCurrentRecord("testInsertReadDb businessEntry failed to validate",
                businessCursor, businessValues);

        // Move the cursor to demonstrate that there is more than one record in the database
        assertTrue( "Error: Only one record returned from business query", businessCursor.moveToFirst() );

        // Sixth Step: Finally, close the cursor and database
        businessCursor.close();
        dbHelper.close();
    }


    /*
        This is a helper method for the testBusinessTable quiz. You can move your
        code from testLocationTable to here so that you can call this code from both
        testBusinessTable and testCategoryTable.
     */
    public long insertCategory() {
        // First step: Get reference to writable database
        // If there's an error in those massive SQL table creation Strings,
        // errors will be thrown here when you try to get a writable database.
        BusinessDBHelper dbHelper = new BusinessDBHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Second Step: Create ContentValues of what you want to insert
        // (you can use the createComputersCategoryValues if you wish)
        ContentValues testValues = TestUtilities.createComputersCategoryValues();

        // Third Step: Insert ContentValues into database and get a row ID back
        long categoryRowId;
        categoryRowId = db.insert(BusinessContract.CategoryEntry.TABLE_NAME, null, testValues);

        // Verify we got a row back.
        assertTrue(categoryRowId != -1);

        // Data's inserted.  IN THEORY.  Now pull some out to stare at it and verify it made
        // the round trip.

        // Fourth Step: Query the database and receive a Cursor back
        // A cursor is your primary interface to the query results.
        Cursor cursor = db.query(
                BusinessContract.CategoryEntry.TABLE_NAME,  // Table to Query
                null, // all columns
                null, // Columns for the "where" clause
                null, // Values for the "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null // sort order
        );

        // Move the cursor to a valid database row and check to see if we got any records back
        // from the query
        assertTrue( "Error: No Records returned from category query", cursor.moveToFirst() );

        // Fifth Step: Validate data in resulting Cursor with the original ContentValues
        // (you can use the validateCurrentRecord function in TestUtilities to validate the
        // query if you like)
        TestUtilities.validateCurrentRecord("Error: Category Query Validation Failed",
                cursor, testValues);

        // Move the cursor to demonstrate that there is only one record in the database
        assertFalse( "Error: More than one record returned from category query",
                cursor.moveToNext() );

        // Sixth Step: Close Cursor and Database
        cursor.close();
        db.close();
        return categoryRowId;
    }

    public long insertSubCategory() {
        return -1L;
    }
}
