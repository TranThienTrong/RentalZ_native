package com.example.rentalapartmentapp.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalapartmentapp.Model.Property;
import com.example.rentalapartmentapp.R;
import com.example.rentalapartmentapp.Services.DatabaseHelper;
import com.example.rentalapartmentapp.View.Adapter.MyPropertyAdapter;

import java.util.ArrayList;

public class DashboardActivity extends BaseActivity {

    RecyclerView recyclerView;
    public MyPropertyAdapter myAdapter;
    ArrayList<Property> propertyList;

    DatabaseHelper databaseHelper;

    @Override
    protected void onStart() {
        super.onStart();
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.auth_screens_background));

        propertyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_fragment_propertys);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgressDialog(this, "Getting property...");
        databaseHelper.showAllProperties();
        dismissProgressDialog();
    }

    public void showpropertyList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.property_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_property:
                startActivity(new Intent(this, AddPropertyActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        doubleBackToExit();
    }
}