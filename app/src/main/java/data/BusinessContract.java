package data;

import android.provider.BaseColumns;

/**
 * Defines table and column names for the business database
 */
public class BusinessContract {

    /*
        Inner class that defines the table contents of the business table
     */
    public static final class BusinessEntry implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "business";

        // Column with the foreign key into the category table.
        public static final String COLUMN_CAT_KEY = "category_id";

        // Column with the foreign key into the sub-category table
        public static final String COLUMN_SUB_CAT_KEY = "sub_category_id";

        // phone number is stored as a float
        public static final String COLUMN_PHONE = "phone";

        // Business id as returned by API, to identify the icon to be used
        public static final String COLUMN_BUSINESS_ID = "business_id";

        // Column email
        public static final String COLUMN_EMAIL = "email";


        public static final String COLUMN_STREET = "street";
        public static final String COLUMN_BUILDING = "building";

        // Column business name
        public static final String COLUMN_BUSINESS_NAME = "business_name";

    }

    /*
        Inner class that defines the table contents of the category table
     */
    public static final class CategoryEntry implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "category";

        // Category name
        public static final String COLUMN_CATEGORY_NAME = "category_name";

    }

    /*
        Inner class that defines the table contents of the sub-category table
     */
    public static final class SubCategoryEntry implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "sub_category";

        // Column with the foreign key into the category table
        public static final String COLUMN_CATEGORY_KEY = "category_id";

        // sub_category_name
        public static final String COLUMN_SUB_CATEGORY_NAME = "sub_category_name";

    }
}
