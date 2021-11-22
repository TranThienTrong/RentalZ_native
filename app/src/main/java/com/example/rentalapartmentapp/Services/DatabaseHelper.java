package com.example.rentalapartmentapp.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rentalapartmentapp.Model.BedroomTypes;
import com.example.rentalapartmentapp.Model.Constant;
import com.example.rentalapartmentapp.Model.FurnitureTypes;
import com.example.rentalapartmentapp.Model.Property;
import com.example.rentalapartmentapp.Model.PropertyTypes;
import com.example.rentalapartmentapp.View.Activities.AddPropertyActivity;
import com.example.rentalapartmentapp.View.Activities.DashboardActivity;
import com.example.rentalapartmentapp.View.Adapter.MyPropertyAdapter;


import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper implements DAO {

    Context context;

    // Database Info
    private static final String DATABASE_NAME = "RentalAppDatabase";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    String SQL_CREATE_PROPERTIES_TABLE = "CREATE TABLE " + Constant.PropertyEntry.TABLE_NAME + "(" +
            Constant.PropertyEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Constant.PropertyEntry.COLUMN_IMAGE + " BLOB, " +
            Constant.PropertyEntry.COLUMN_TITLE + " TEXT, " +
            Constant.PropertyEntry.COLUMN_ROOM_QUANTITY + " INTEGER, " +
            Constant.PropertyEntry.COLUMN_PRICE + " TEXT, " +
            Constant.PropertyEntry.COLUMN_DESC + " TEXT, " +
            Constant.PropertyEntry.COLUMN_ADDRESS + " TEXT, " +
            Constant.PropertyEntry.COLUMN_BEDROOM + " TEXT, " +
            Constant.PropertyEntry.COLUMN_FURNITURE + " TEXT, " +
            Constant.PropertyEntry.COLUMN_TYPE + " TEXT " +
            ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PROPERTIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DROP_PROPERTIES_TABLE = "DROP TABLE IF EXISTS " + Constant.PropertyEntry.TABLE_NAME;

        db.execSQL(SQL_DROP_PROPERTIES_TABLE);

        onCreate(db);
    }

    public long insertProperty(Property property) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.PropertyEntry.COLUMN_IMAGE, property.getImage());
        values.put(Constant.PropertyEntry.COLUMN_TITLE, property.getTitle());
        values.put(Constant.PropertyEntry.COLUMN_ROOM_QUANTITY, property.getRoomsQuantity());
        values.put(Constant.PropertyEntry.COLUMN_PRICE, property.getPrice());
        values.put(Constant.PropertyEntry.COLUMN_DESC, property.getDesc());
        values.put(Constant.PropertyEntry.COLUMN_ADDRESS, property.getAddress());
        values.put(Constant.PropertyEntry.COLUMN_BEDROOM, property.getBedroom().name());
        values.put(Constant.PropertyEntry.COLUMN_FURNITURE, property.getFurnitureType().name());
        values.put(Constant.PropertyEntry.COLUMN_TYPE, property.getPropertyType().name());

        return sqLiteDatabase.insert(Constant.PropertyEntry.TABLE_NAME, null, values);
    }


    public ArrayList<Property> getAllProperties() {

        ArrayList<Property> propertiesList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Constant.PropertyEntry.TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Property property = new Property(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getBlob(1),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getInt(cursor.getColumnIndex("rooms_quantity")),
                        cursor.getString(cursor.getColumnIndex("price")),
                        cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getString(cursor.getColumnIndex("address")),
                        BedroomTypes.getBedroom(cursor.getString(cursor.getColumnIndex("bedroom"))),
                        FurnitureTypes.getFurnitureTypes(cursor.getString(cursor.getColumnIndex("furniture"))),
                        PropertyTypes.getPropertyType(cursor.getString(cursor.getColumnIndex("type")))
                );
                propertiesList.add(property);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return propertiesList;
    }


    @Override
    public void addPropertyToDatabase(Property property) {
        insertProperty(property);
        ((AddPropertyActivity) context).finish();
    }

    @Override
    public void showAllProperties() {
        ArrayList<Property> propertyList = new ArrayList<>();
        propertyList = getAllProperties();
        ((DashboardActivity) context).myAdapter = new MyPropertyAdapter(context, propertyList, this);
        ((DashboardActivity) context).showpropertyList();
        ((DashboardActivity) context).myAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteProperty(Property property) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(
                Constant.PropertyEntry.TABLE_NAME,
                Constant.PropertyEntry.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(property.getId())});


    }


}
