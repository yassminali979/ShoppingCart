package com.example.shoppingapp.Database;

import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract {
    public static final String CONTENT_AUTHORITY = "com.example.shoppingapp";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH = "shop";

    public static abstract class OrderEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH);
        public static final String TABLE_NAME = "shop";
        public static final String _ID = BaseColumns._ID;
        public static final String NAME = "name";
        public static final String QUANTITY = "quantity";
        public static final String PRICE = "price";

    }
}
