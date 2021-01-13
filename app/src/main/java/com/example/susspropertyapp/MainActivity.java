package com.example.susspropertyapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Property;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.susspropertyapp.model.Agent;
import com.example.susspropertyapp.model.AgentDataManager;
import com.example.susspropertyapp.model.Listing;
import com.example.susspropertyapp.model.ListingDataManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    public static String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null)  getSupportActionBar().hide();
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_agents, R.id.navigation_chats, R.id.navigation_account, R.id.navigation_loan)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Intent intent = getIntent();

        userId = intent.getStringExtra("userId");

        navController.navigate(intent.getIntExtra("destination", R.id.navigation_home));

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()){
                    case R.id.navigation_home_results:
                        // filter activity -> results fragment
                        if(intent.getBooleanExtra("isFiltering",false)) {
                            String priceMin = intent.getStringExtra("priceMin");
                            String priceMax = intent.getStringExtra("priceMax");
                            String areaMin = intent.getStringExtra("areaMin");
                            String areaMax = intent.getStringExtra("areaMax");
                            String yearMin = intent.getStringExtra("yearMin");
                            String yearMax = intent.getStringExtra("yearMax");
                            String keywords = intent.getStringExtra("keywords");
                            Log.i("info","priceMinMax "+priceMin+" "+priceMax);
                            Log.i("info","areaMinMax "+areaMin+" "+areaMax);
                            Log.i("info","yearMinMax "+yearMin+" "+yearMax);
                            Log.i("info","keywords "+keywords);
                            break;
                        }

                        // Map activity -> results fragment
                        else if (intent.getBooleanExtra("isMapSearching",false)){
                            String latitude = intent.getStringExtra("latitude");
                            String longitude = intent.getStringExtra("longitude");
                            break;
                        }
                        break;

                    default: break;
                }
            }

        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        navController.navigate(intent.getIntExtra("destination", R.id.navigation_home));
    }




}
