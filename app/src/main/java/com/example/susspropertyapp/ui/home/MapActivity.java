package com.example.susspropertyapp.ui.home;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.susspropertyapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        OnSuccessListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    Toolbar topAppBar;
    MediaPlayer mp;
    //    map display
    private GoogleMap mMap;
    // location info
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    SupportMapFragment mapFragment;
    private LatLng myPlace = new LatLng(1.525504, 103.76069);
    private Location myLocation;
    private String addressOutput;
    private MarkerOptions myMarkerOptions;

    Boolean isFromNewListingActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // CLICK functions
        topAppBar = findViewById(R.id.topAppBar);
        mp = MediaPlayer.create(this, R.raw.button);

        topAppBar.setNavigationOnClickListener(view1 -> {
            mp.start();
            onBackPressed();
        });

        //check for args (isFromNewListingActivity)
        Intent getIntent = getIntent();
        isFromNewListingActivity = getIntent.getBooleanExtra("isFromNewListingActivity",false);

        String getTitle = getIntent.getStringExtra("getTitle");
        String getAddressField = getIntent.getStringExtra("getAddressField");
        String getDescription = getIntent.getStringExtra("getDescription");
        String getPrice = getIntent.getStringExtra("getPrice");
        String getArea = getIntent.getStringExtra("getArea");
        String getYear = getIntent.getStringExtra("getYear");
        String getKeywords = getIntent.getStringExtra("getKeywords");

        String getHouseType = getIntent.getStringExtra("getHouseType");
        String getBathrooms = getIntent.getStringExtra("getBathrooms");
        String getBedRooms = getIntent.getStringExtra("getBedRooms");
        String getTenure = getIntent.getStringExtra("getTenure");
        String getStatus = getIntent.getStringExtra("getStatus");

        String getAddress = getIntent.getStringExtra("getAddress");
        String getLatitude = getIntent.getStringExtra("getLatitude");
        String getLongitude = getIntent.getStringExtra("getLongitude");
        String getAgentId = getIntent.getStringExtra("agentId");

        findViewById(R.id.continueBtn).setOnClickListener(v -> {
            if (isFromNewListingActivity){
                Intent intent = new Intent(MapActivity.this, com.example.susspropertyapp.ui.account.NewListingActivity.class);

                intent.putExtra("mapContinued", true);
                intent.putExtra("getLatitude", (double) myPlace.latitude);
                intent.putExtra("getLongitude", (double) myPlace.longitude);
                intent.putExtra("getAddress",addressOutput);

                intent.putExtra("getTitle",getTitle);
                intent.putExtra("getAddressField",getAddressField);
                intent.putExtra("getDescription",getDescription);
                intent.putExtra("getPrice",getPrice);
                intent.putExtra("getArea",getArea);
                intent.putExtra("getYear",getYear);
                intent.putExtra("getKeywords",getKeywords);
                intent.putExtra("getHouseType",getHouseType);
                intent.putExtra("getBathrooms",getBathrooms);
                intent.putExtra("getBedRooms",getBedRooms);
                intent.putExtra("getTenure",getTenure);
                intent.putExtra("getStatus",getStatus);
                intent.putExtra("agentId",getAgentId);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                startActivity(intent, options.toBundle());
            } else{
                Intent intent = new Intent(MapActivity.this, com.example.susspropertyapp.MainActivity.class);
                intent.putExtra("destination", (Integer) R.id.navigation_home_results);

                intent.putExtra("isMapSearching", (Boolean) true);
                intent.putExtra("latitude", (double) myPlace.latitude);
                intent.putExtra("longitude", (double) myPlace.longitude);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                startActivity(intent, options.toBundle());
            }
        });


        findViewById(R.id.setMyLocationBtn).setOnClickListener(v -> {
            createLocationRequest();
            startLocationUpdates();
        });

        // location function
        checkPermission();
        buildGoogleApiClient();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        resultReceiver = new AddressResultReceiver(new Handler());
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////    MAP Functions      ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(point -> {
            myPlace = point;
            myLocation = new Location(LocationManager.GPS_PROVIDER);
            myLocation.setLatitude(myPlace.latitude);
            myLocation.setLongitude(myPlace.longitude);
            fetchAddressHandler();
        });
        checkPermission();
        fetchAddressHandler();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//        displayAddressOutput();
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// Permission Functions  ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {//Can add more as per requirement

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
            return;
        }
    }

    private GoogleApiClient mGoogleApiClient;
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();//call onConnected
    }
    public void onConnected(Bundle connectionHint) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //call onSuccess
        fusedLocationClient.getLastLocation().addOnSuccessListener(this);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////   Location Functions  ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onSuccess(Object o) {
        myLocation = (Location) o;
        myPlace = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        fetchAddressHandler();
        createLocationRequest();
        startLocationUpdates();
    }

    LocationRequest mLocationRequest;
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        //norm is to set to 10000ms
        mLocationRequest.setInterval(1000);
        //norm is to set to 5000ms
        mLocationRequest.setFastestInterval(500);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected void startLocationUpdates() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult result) {
                super.onLocationResult(result);
                for(Location location : result.getLocations()){
                    myLocation = location;
                    myPlace = new LatLng(location.getLatitude(), location.getLongitude());
                }
            }
        };
        fusedLocationClient.requestLocationUpdates(mLocationRequest, locationCallback, Looper.getMainLooper());

        fetchAddressHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(locationCallback!=null && locationCallback!=null ) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    public void resetMarker(){
        mMap.clear();
        myMarkerOptions = new MarkerOptions().position(myPlace).title(addressOutput)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(myMarkerOptions).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 12.0f));
        mMap.addCircle(new CircleOptions().center(myPlace).radius(5000).strokeWidth(1)
                .strokeColor(0xAA2196F3).fillColor(0x1A2196F3));
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// set address functions ////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    private AddressResultReceiver resultReceiver;
    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, myLocation);
        startService(intent);
    }

    public void fetchAddressHandler() {
        //Note the last know location will not be updated even the location is changed
        //Need to use request for location update service
        if (fusedLocationClient != null){

            startIntentService();
//            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location) {
//                    myLocation = location;
//                    // In some rare cases the location returned can be null
//                    if (myLocation == null) {
//                        return;
//                    }
//                    if (!Geocoder.isPresent()) {
//                        Toast.makeText(MapActivity.this, "no_geocoder_available", Toast.LENGTH_LONG).show();
//                        return;
//                    }
//                    startIntentService();
//                }
//            });
        }

    }

    private void displayAddressOutput(){
//        addresstv.setText((addressOutput));

//        Log.i("info",addressOutput + "");
//        Log.i("info",myLocation + "");
//        Log.i("info",myPlace + "");
//        Log.i("info", "");
    }

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
//            Log.i("info", "on receive result");
            if (resultData == null) {
                return;
            }

            // Display the address string
            // or an error message sent from the intent service.
            addressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            if (addressOutput == null) {
                addressOutput = "";
            }
            displayAddressOutput();
            resetMarker();

            // Show a toast message if an address was found.
//            if (resultCode == Constants.SUCCESS_RESULT) {
//                Toast.makeText(MapActivity.this, "Address Found", Toast.LENGTH_LONG).show();
//            }
        }
    }
}