package com.example.rentalapartmentapp.Model;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

public class Constant {
    public static final int GALLERY_RESULT_CODE = 10;
    public static class PropertyEntry{
        public static final String TABLE_NAME = "properties";
        // Columns
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ROOM_QUANTITY = "rooms_quantity";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_BEDROOM = "bedroom";
        public static final String COLUMN_FURNITURE = "furniture";
        public static final String COLUMN_TYPE = "type";
    }


    public static void showGallery(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, GALLERY_RESULT_CODE);
    }

}
