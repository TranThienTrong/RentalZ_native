package com.example.rentalapartmentapp.View.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rentalapartmentapp.Model.BedroomTypes;
import com.example.rentalapartmentapp.Model.Constant;
import com.example.rentalapartmentapp.Model.FurnitureTypes;
import com.example.rentalapartmentapp.Model.Property;
import com.example.rentalapartmentapp.Model.PropertyTypes;
import com.example.rentalapartmentapp.R;
import com.example.rentalapartmentapp.Services.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddPropertyActivity extends BaseActivity implements View.OnClickListener {

    ImageButton imgBtnAddImageproperty;
    ImageView imgproperty;
    Button btnAddproperty;
    EditText etpropertyTitle;
    EditText etpropertyPrice;
    EditText etpropertyDesc;
    EditText etpropertyQuantity;
    EditText etpropertyAddress;
    Spinner snBedroom;
    Spinner snFurniture;
    Spinner snType;

    Uri imageSelectedUri = null;

    DatabaseHelper databaseHelper;

    Property property = new Property();

    ArrayList<String> bedrooms = new ArrayList<>();
    ArrayList<String> furnitures = new ArrayList<>();
    ArrayList<String> types = new ArrayList<>();


    @Override
    protected void onStart() {
        super.onStart();
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar_add_property);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        for (BedroomTypes bedroom : BedroomTypes.values()) {
            bedrooms.add(bedroom.name());
        }

        for (FurnitureTypes furniture : FurnitureTypes.values()) {
            furnitures.add(furniture.name());
        }

        for (PropertyTypes type : PropertyTypes.values()) {
            types.add(type.name());
        }

        imgBtnAddImageproperty = findViewById(R.id.imgBtn_add_image_property);
        imgproperty = findViewById(R.id.img_property_add_property);
        btnAddproperty = findViewById(R.id.btn_add_property);
        etpropertyTitle = findViewById(R.id.et_property_title_add_property);
        etpropertyPrice = findViewById(R.id.et_property_price_add_property);
        etpropertyDesc = findViewById(R.id.et_property_desc_add_property);
        etpropertyQuantity = findViewById(R.id.et_property_quantity_add_property);
        etpropertyAddress = findViewById(R.id.et_property_address_add_property);

        snBedroom = findViewById(R.id.spinner_property_bedrooms);
        snFurniture = findViewById(R.id.spinner_property_furniture);
        snType = findViewById(R.id.spinner_property_type);

        btnAddproperty.setOnClickListener(this);
        imgBtnAddImageproperty.setOnClickListener(this);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bedrooms);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snBedroom.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, furnitures);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snFurniture.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snType.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtn_add_image_property:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Constant.showGallery(AddPropertyActivity.this);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 69);
                }
                break;
            case R.id.btn_add_property:
                if (validatepropertyInfo()) {
                    property.setTitle(etpropertyTitle.getText().toString());
                    property.setRoomsQuantity(Integer.parseInt(etpropertyQuantity.getText().toString()));
                    property.setPrice(etpropertyPrice.getText().toString());
                    property.setDesc(etpropertyDesc.getText().toString());
                    property.setAddress(etpropertyAddress.getText().toString());
                    property.setBedroom(BedroomTypes.getBedroom(snBedroom.getSelectedItem().toString()));
                    property.setFurnitureType(FurnitureTypes.getFurnitureTypes(snFurniture.getSelectedItem().toString()));
                    property.setPropertyType(PropertyTypes.getPropertyType(snType.getSelectedItem().toString()));

                    showProgressDialog(this, "Adding...");
                    databaseHelper.addPropertyToDatabase(property);
                    dismissProgressDialog();
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }

    private boolean validatepropertyInfo() {
        if (TextUtils.isEmpty(etpropertyTitle.getText().toString().trim())) {
            etpropertyTitle.setError("Title cannot empty");
            showErrorMessage("Title cannot empty", true);
            return false;
        } else if (TextUtils.isEmpty(etpropertyPrice.getText().toString().trim())) {
            etpropertyPrice.setError("Price cannot empty");
            showErrorMessage("Price cannot empty", true);
            return false;
        } else if (TextUtils.isEmpty(etpropertyDesc.getText().toString().trim())) {
            etpropertyDesc.setError("Desc cannot empty");
            showErrorMessage("Desc cannot empty", true);
            return false;
        } else if (TextUtils.isEmpty(etpropertyQuantity.getText().toString().trim())) {
            etpropertyQuantity.setError("Desc cannot empty");
            showErrorMessage("Desc cannot empty", true);
            return false;
        } else if (imageSelectedUri == null) {
            showErrorMessage("Please set Image for Property", true);
            return false;
        } else if (TextUtils.isEmpty(etpropertyAddress.getText().toString().trim())) {
            etpropertyAddress.setError("Address cannot empty");
            showErrorMessage("Address cannot empty", true);
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.GALLERY_RESULT_CODE && resultCode == RESULT_OK) {
            if (data.getData() != null) {
                imageSelectedUri = data.getData();
                try {
                    InputStream imageStream = getContentResolver().openInputStream(imageSelectedUri);
                    Bitmap bitmapImage = BitmapFactory.decodeStream(imageStream);
                    imgproperty.setImageBitmap(bitmapImage);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] imageBytes = stream.toByteArray();
                    property.setImage(imageBytes);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                imgBtnAddImageproperty.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_edit_24));
            }
        } else {
            Log.e("image", "get image false");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 69) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

}