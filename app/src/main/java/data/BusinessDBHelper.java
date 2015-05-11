package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Manages a local database for business data
 */
public class BusinessDBHelper extends SQLiteOpenHelper {

    // If you change the database schema you must increment tje database version
    // For this case, it's ! since it's the first time
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "business.db";

    public BusinessDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create  table to hold categories. A category consists of the string supplied in the
        // category name
        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " + BusinessContract.CategoryEntry.TABLE_NAME +
                " (" +
                BusinessContract.CategoryEntry._ID + " INTEGER PRIMARY KEY," +
                BusinessContract.CategoryEntry.COLUMN_CATEGORY_NAME + " TEXT UNIQUE NOT NULL " +
                " );";

        // Create a table to hold the sub_categories. A sub_category consists of the string supplied in the
        // sub_category name
        final String SQL_CREATE_SUB_CATEGORY_TABLE = "CREATE TABLE " + BusinessContract.SubCategoryEntry.TABLE_NAME +
                " (" +
                BusinessContract.SubCategoryEntry._ID + " INTEGER PRIMARY KEY," +
                BusinessContract.SubCategoryEntry.COLUMN_SUB_CATEGORY_NAME + " TEXT UNIQUE NOT NULL, " +

                BusinessContract.SubCategoryEntry.COLUMN_CATEGORY_KEY + " INTEGER NOT NULL, " +

                //Set up the foreign key
                " FOREIGN KEY (" + BusinessContract.SubCategoryEntry.COLUMN_CATEGORY_KEY + ") REFERENCES " +
                BusinessContract.CategoryEntry.TABLE_NAME + " (" + BusinessContract.CategoryEntry._ID +
                " ));";

        final String SQL_CREATE_BUSINESS_TABLE = "CREATE TABLE " + BusinessContract.BusinessEntry.TABLE_NAME +
                " (" +
                // Why AutoIncrement here, and not above?
                // Unique keys will be auto-generated in either case. But for business
                // data, it's reasonable to assume the user will want information
                // for a certain business, so it should be sorted accordingly
                BusinessContract.BusinessEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                // the ID of the category entry associated with this data
                BusinessContract.BusinessEntry.COLUMN_CAT_KEY + " INTEGER NOT NULL, " +

                // ID of sub-category entry
                BusinessContract.BusinessEntry.COLUMN_SUB_CAT_KEY + " INTEGER NOT NULL, " +

                BusinessContract.BusinessEntry.COLUMN_BUSINESS_ID + " INTEGER NOT NULL, " +
                BusinessContract.BusinessEntry.COLUMN_BUSINESS_NAME + " TEXT NOT NULL, " +
                BusinessContract.BusinessEntry.COLUMN_PHONE + " TEXT NOT NULL, " +
                BusinessContract.BusinessEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                BusinessContract.BusinessEntry.COLUMN_BUILDING + " TEXT NOT NULL, " +
                BusinessContract.BusinessEntry.COLUMN_STREET + " TEXT NOT NULL, " +

                // Set up the foreign keys
                " FOREIGN KEY (" + BusinessContract.BusinessEntry.COLUMN_CAT_KEY + ") REFERENCES " +
                BusinessContract.CategoryEntry.TABLE_NAME + " (" + BusinessContract.CategoryEntry._ID + "), " +

                " FOREIGN KEY (" + BusinessContract.BusinessEntry.COLUMN_SUB_CAT_KEY + ") REFERENCES " +
                BusinessContract.SubCategoryEntry.TABLE_NAME + " (" + BusinessContract.SubCategoryEntry._ID + "));";

        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_SUB_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_BUSINESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This  database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for my application
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        db.execSQL("DROP TABLE IF EXISTS " + BusinessContract.CategoryEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BusinessContract.SubCategoryEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BusinessContract.BusinessEntry.TABLE_NAME);
        onCreate(db);
    }
}
