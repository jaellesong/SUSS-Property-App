package com.example.susspropertyapp.ui.account;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.Listing;
import com.example.susspropertyapp.model.ListingDataManager;

import java.util.ArrayList;
import java.util.Objects;

public class NewListingActivity extends AppCompatActivity {

    Toolbar topAppBar;
    MediaPlayer mp;

    private ListingDataManager propertyDM;
    // init all the text fields
    EditText etTitle;
    EditText etAddress;
    EditText etDescription;
    EditText etPrice;
    EditText etArea;
    EditText etYear;
    EditText etKeywords; // not required

    String getAddress;
    String getLatitude;
    String getLongitude;

    Drawable drawPlus;
    ImageButton clickedButton;
    ImageButton clickedButtonDel;

    String title;
    String address;
    String description;
    String price;
    String area;
    String year;
    String keywords;
    String getTenure;
    String getHouseType;
    String getStatus;
    String getBathrooms;
    String getBedRooms;

    String agentId;
    //Request code gallery
    private static final int GALLERY_REQUEST = 9;
    //Request code for camera
    private static final int CAMERA_REQUEST = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_listing);
        propertyDM = new ListingDataManager(this);


        ////////////////////////////////  init all the text fields ////////////////////////////////
        etTitle = findViewById(R.id.etTitle); etAddress = findViewById(R.id.etAddress);
        etDescription = findViewById(R.id.etDescription); etPrice = findViewById(R.id.etPrice);
        etArea = findViewById(R.id.etArea);  etYear = findViewById(R.id.etYear);
        etKeywords = findViewById(R.id.etKeywords);

        drawPlus= ResourcesCompat.getDrawable( getResources() ,R.drawable.add_black_24dp,getTheme());

        ////////////////////////////////  action bar    /////////////////////////////////////
        // CLICK functions
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        topAppBar = findViewById(R.id.topAppBar);
        mp = MediaPlayer.create(this, R.raw.button);
        topAppBar.setNavigationOnClickListener(view1 -> {
            mp.start();
            Intent intent = new Intent(this, com.example.susspropertyapp.MainActivity.class);
            intent.putExtra("destination", (Integer) R.id.navigation_account);
            intent.putExtra("isFiltering", false);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, options.toBundle());
        });

        ////////////////////////////////   add del new image    /////////////////////////////////////
        ImageButton imgBtn1 = findViewById(R.id.imgBtn1); ImageButton imgBtn2 = findViewById(R.id.imgBtn2);
        ImageButton imgBtn3 = findViewById(R.id.imgBtn3); ImageButton imgBtn4 = findViewById(R.id.imgBtn4);
        ImageButton imgBtn5 = findViewById(R.id.imgBtn5);
        ImageButton imgBtn1del = findViewById(R.id.imgBtn1del);
        ImageButton imgBtn2del = findViewById(R.id.imgBtn2del);
        ImageButton imgBtn3del = findViewById(R.id.imgBtn3del);
        ImageButton imgBtn4del = findViewById(R.id.imgBtn4del);
        ImageButton imgBtn5del = findViewById(R.id.imgBtn5del);
        // if imgBtn has hasImage inside, show the [x] button
        initImgDelBtn(imgBtn1,imgBtn1del);
        initImgDelBtn(imgBtn2,imgBtn2del);
        initImgDelBtn(imgBtn3,imgBtn3del);
        initImgDelBtn(imgBtn4,imgBtn4del);
        initImgDelBtn(imgBtn5,imgBtn5del);

        // initImgBtn
        imgBtn1.setOnClickListener(view -> {
            clickedButton = imgBtn1;
            clickedButtonDel = imgBtn1del;
            showImageOptionDialog();
        });
        // initImgBtn
        imgBtn2.setOnClickListener(view -> {
            clickedButton = imgBtn2;
            clickedButtonDel = imgBtn2del;
            showImageOptionDialog();
        });
        // initImgBtn
        imgBtn3.setOnClickListener(view -> {
            clickedButton = imgBtn3;
            clickedButtonDel = imgBtn3del;
            showImageOptionDialog();
        });
        // initImgBtn
        imgBtn4.setOnClickListener(view -> {
            clickedButton = imgBtn4;
            clickedButtonDel = imgBtn4del;
            showImageOptionDialog();
        });
        // initImgBtn
        imgBtn5.setOnClickListener(view -> {
            clickedButton = imgBtn5;
            clickedButtonDel = imgBtn5del;
            showImageOptionDialog();
        });



        /////////////////////////////// toggle button functions ///////////////////////////////////
        ToggleButton tbHsTypeCondo = findViewById(R.id.tbHsTypeCondo);
        ToggleButton tbHsTypeHDB = findViewById(R.id.tbHsTypeHDB);
        ToggleButton tbHsTypeLanded = findViewById(R.id.tbHsTypeLanded);
        ArrayList<ToggleButton> tbHsTypeList= new ArrayList<>();

        ToggleButton tbBedStudio = findViewById(R.id.tbBedStudio);
        ToggleButton tbBed1 = findViewById(R.id.tbBed1);
        ToggleButton tbBed2 = findViewById(R.id.tbBed2);
        ToggleButton tbBed3 = findViewById(R.id.tbBed3);
        ToggleButton tbBed4 = findViewById(R.id.tbBed4);
        ToggleButton tbBed5 = findViewById(R.id.tbBed5);
        ArrayList<ToggleButton> tbBedList = new ArrayList<>();

        ToggleButton tbBath1 = findViewById(R.id.tbBath1);
        ToggleButton tbBath2 = findViewById(R.id.tbBath2);
        ToggleButton tbBath3 = findViewById(R.id.tbBath3);
        ToggleButton tbBath4 = findViewById(R.id.tbBath4);
        ToggleButton tbBath5 = findViewById(R.id.tbBath5);
        ToggleButton tbBath6 = findViewById(R.id.tbBath6);
        ArrayList<ToggleButton> tbBathList = new ArrayList<>();

        ToggleButton tbTenure99 = findViewById(R.id.tbTenure99);
        ToggleButton tbTenure999 = findViewById(R.id.tbTenure999);
        ToggleButton tbTenureFreehold = findViewById(R.id.tbTenureFreehold);
        ArrayList<ToggleButton> tbTenureList= new ArrayList<>();

        ToggleButton tbStatusNew = findViewById(R.id.tbStatusNew);
        ToggleButton tbStatusResale = findViewById(R.id.tbStatusResale);
        ArrayList<ToggleButton> tbStatusList= new ArrayList<>();

        // housing type toggle btns
        tbHsTypeList.add(tbHsTypeCondo);
        tbHsTypeList.add(tbHsTypeHDB); tbHsTypeList.add(tbHsTypeLanded);
        setListToRadio(tbHsTypeList);
        // BED type toggle btns
        tbBedList.add(tbBedStudio);
        tbBedList.add(tbBed1); tbBedList.add(tbBed2);
        tbBedList.add(tbBed3); tbBedList.add(tbBed4);
        tbBedList.add(tbBed5);
        setListToRadio(tbBedList);
        // Bath type toggle btns
        tbBathList.add(tbBath1); tbBathList.add(tbBath2);
        tbBathList.add(tbBath3); tbBathList.add(tbBath4);
        tbBathList.add(tbBath5); tbBathList.add(tbBath6);
        setListToRadio(tbBathList);
        // Tenure type toggle btns
        tbTenureList.add(tbTenure99);
        tbTenureList.add(tbTenure999); tbTenureList.add(tbTenureFreehold);
        setListToRadio(tbTenureList);
        // Status type toggle btns
        tbStatusList.add(tbStatusNew);
        tbStatusList.add(tbStatusResale);
        setListToRadio(tbStatusList);


        /////////////////////////////////  GET from Map INTENT ^top ////////////////////////////////
        Intent getIntent = getIntent();


        etTitle.setText(getIntent.getStringExtra("getTitle"));
        etDescription.setText(getIntent.getStringExtra("getDescription"));
        etPrice.setText(getIntent.getStringExtra("getPrice"));
        etArea.setText(getIntent.getStringExtra("getArea"));
        etYear.setText(getIntent.getStringExtra("getYear"));
        etKeywords.setText(getIntent.getStringExtra("getKeywords"));

        getHouseType = getIntent.getStringExtra("getHouseType");
        getBathrooms = getIntent.getStringExtra("getBathrooms");
        getBedRooms = getIntent.getStringExtra("getBedRooms");
        getTenure = getIntent.getStringExtra("getTenure");
        getStatus = getIntent.getStringExtra("getStatus");
        agentId = getIntent.getStringExtra("agentId");

        setListToRadio(tbHsTypeList,getHouseType);
        setListToRadio(tbBathList,getBathrooms);
        setListToRadio(tbBedList,getBedRooms);
        setListToRadio(tbTenureList,getTenure);
        setListToRadio(tbStatusList,getStatus);


        getAddress = getIntent.getStringExtra("getAddress");
        getLatitude = getIntent.getStringExtra("getLatitude");
        getLongitude = getIntent.getStringExtra("getLongitude");


        if (getIntent.getBooleanExtra("mapContinued",false)){
            if (getAddress!= null && getAddress != ""){
                etAddress.setText(getAddress);
            }
        } else{
            etAddress.setText(getIntent.getStringExtra("getAddressField"));
        }

        /////////////////////////////////  map get long lat data   ////////////////////////////////
        findViewById(R.id.mapBtn).setOnClickListener(view -> {

            title = etTitle.getText().toString();
            address = etAddress.getText().toString();
            description = etDescription.getText().toString();
            price = etPrice.getText().toString();
            area = etArea.getText().toString();
            year = etYear.getText().toString();
            keywords = etKeywords.getText().toString();
            // get active radio button
            getHouseType = getActiveBox(tbHsTypeList);
            getBathrooms = getActiveBox(tbBathList);
            getBedRooms = getActiveBox(tbBedList);
            getTenure = getActiveBox(tbTenureList);
            getStatus = getActiveBox(tbStatusList);


            Intent intent = new Intent(this, com.example.susspropertyapp.ui.home.MapActivity.class);
            // TODO: save all the data

            intent.putExtra("isFromNewListingActivity", true);

            intent = putExtraStringForMap(intent);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, options.toBundle());
        });

        ((EditText) findViewById(R.id.etAddress)).setOnClickListener(view -> {

            title = etTitle.getText().toString();
            address = etAddress.getText().toString();
            description = etDescription.getText().toString();
            price = etPrice.getText().toString();
            area = etArea.getText().toString();
            year = etYear.getText().toString();
            keywords = etKeywords.getText().toString();
            // get active radio button
            getHouseType = getActiveBox(tbHsTypeList);
            getBathrooms = getActiveBox(tbBathList);
            getBedRooms = getActiveBox(tbBedList);
            getTenure = getActiveBox(tbTenureList);
            getStatus = getActiveBox(tbStatusList);


            Intent intent = new Intent(this, com.example.susspropertyapp.ui.home.MapActivity.class);
            // TODO: save all the data

            intent.putExtra("isFromNewListingActivity", true);

            intent = putExtraStringForMap(intent);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, options.toBundle());
        });



        ////////////////////////////////  continue button    /////////////////////////////////////
        // TLDR: have to pass all the data from the page
        // show new property page
        findViewById(R.id.continueBtn).setOnClickListener(view -> {
            // TODO: pass image
            title = etTitle.getText().toString();
            address = etAddress.getText().toString();
            description = etDescription.getText().toString();
            price = etPrice.getText().toString();
            area = etArea.getText().toString();
            year = etYear.getText().toString();
            keywords = etKeywords.getText().toString();
            // get active radio button
            getHouseType = getActiveBox(tbHsTypeList);
            getBathrooms = getActiveBox(tbBathList);
            getBedRooms = getActiveBox(tbBedList);
            getTenure = getActiveBox(tbTenureList);
            getStatus = getActiveBox(tbStatusList);
            // get image stuff

            if (title != null && address != null  && description != null
                    && price != null && area != null && year != null
                    && getHouseType != null && getBathrooms != null
                    && getBedRooms != null && getTenure != null && getStatus != null ){
                Intent intent = new Intent(this, com.example.susspropertyapp.MainActivity.class);
                intent.putExtra("destination", (Integer) R.id.navigation_account);
                Listing listing = new Listing();
                listing.setTitle(title);
                listing.setAddress(address);
                listing.setLatitude(getLatitude);
                listing.setLongitude(getLongitude);
                listing.setDescription(description);
                listing.setType(getHouseType);
                listing.setBathrooms(getBathrooms);
                listing.setBedrooms(getBedRooms);
                listing.setPrice(price);
                listing.setArea(area);
                listing.setYear(year);
                listing.setTenure(getTenure);
                listing.setStatus(getStatus);
                listing.setKeywords(keywords);
                listing.setAgentId(agentId);

                Cursor testC = null;
                try {
                    testC = propertyDM.searchRecord(listing.getTitle());
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (testC == null || testC.getCount() == 0) propertyDM.insert(listing);

//                intent.putExtra("listing",listing);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                intent.putExtra("userId",agentId);
                startActivity(intent, options.toBundle());



            } else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //Setting message manually and performing action on button click
                builder.setMessage("You need to fill up all the fields to continue")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Oh no there are some empty fields");
                alert.show();
            }
            // check if all fields are filled
            // post an alert fragment or something

        });



    } // onCreate Ends here <<<<<

    // radio toggle button functions
    public void setBtnChk(CompoundButton buttonView){
        buttonView.setChecked(true);
        buttonView.setBackgroundResource(R.drawable.rounded_button);
        buttonView.setTextColor(0xFFFFFFFF);
    }
    public void setBtnUnchk(ToggleButton btn){
        btn.setChecked(false);
        btn.setBackgroundResource(R.drawable.rounded_button_light);
        btn.setTextColor(0xFF2196F3);
    }
    public void setListToRadio(ArrayList<ToggleButton> thisList){
        for (ToggleButton tb : thisList) {
            tb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                for (ToggleButton tb2 : thisList) setBtnUnchk(tb2);
                if (buttonView.isPressed()) setBtnChk(buttonView);
            });
        }
    }
    public void setListToRadio(ArrayList<ToggleButton> thisList, String activeBtn){
        for (ToggleButton tb : thisList) {
            if (tb.getText().toString().equals(activeBtn)) {
                setBtnChk(tb);
            }
        }

    }
    public @Nullable String getActiveBox(ArrayList<ToggleButton> thisList){
        for (ToggleButton tb : thisList) {
            if (tb.isChecked()) {
                return tb.getText().toString();
            }
        }
        return null;
    }


    // img del button functions
    public void initImgDelBtn(ImageButton imgBtn1, ImageButton imgBtn1del){
        if (imgBtn1.getTag() != null){
            imgBtn1del.setVisibility((int)0);
        }

        imgBtn1del.setOnClickListener(view -> {
            imgBtn1del.setVisibility((int)8);
            imgBtn1.setImageDrawable(drawPlus);
            imgBtn1.setScaleType( ImageView.ScaleType.CENTER );
            imgBtn1.setPadding(35,35,35,35);
            imgBtn1.setColorFilter(ContextCompat.getColor(this, R.color.lightBlueTint), PorterDuff.Mode.MULTIPLY);
            imgBtn1.setTag(null);
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // getting image from gallery or camera functions

    private void showImageOptionDialog(){
        final String[] options = getResources().getStringArray(R.array.image_options);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Image Options")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case 0:
                                getImageFromGallery();
                                break;
                            case 1:
                                capturePictureFromCamera();
                                break;
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void capturePictureFromCamera(){
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    //Open phone gallery
    private void getImageFromGallery(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check if the intent was to pick image, was successful and an image was picked
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null){

            //Get selected image uri from phone gallery
            Uri selectedImage = data.getData();

            //Display selected photo in image view
            clickedButton.setImageURI(selectedImage);
            //if added image do this
            clickedButton.setTag("hasImage");
            clickedButton.setImageTintMode(null);
            clickedButton.setScaleType( ImageView.ScaleType.CENTER_CROP );
            clickedButton.setPadding(0,0,0,0);
            clickedButton.setColorFilter(null);
            initImgDelBtn(clickedButton,clickedButtonDel);
        }
        //Handle camera request
        else if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null){

            //We need a bitmap variable to store the photo
            Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

            //Display taken picture in image view
            clickedButton.setImageBitmap(bitmap);

            //if added image do this
            clickedButton.setTag("hasImage");
            clickedButton.setImageTintMode(null);
            clickedButton.setScaleType( ImageView.ScaleType.CENTER_CROP );
            clickedButton.setPadding(0,0,0,0);
            clickedButton.setColorFilter(null);
            initImgDelBtn(clickedButton,clickedButtonDel);
        }

    }

    public Intent putExtraStringForMap(Intent intent){
        intent.putExtra("getTitle",title);
        intent.putExtra("getAddressField",address);
        intent.putExtra("getDescription",description);
        intent.putExtra("getPrice",price);
        intent.putExtra("getArea",area);
        intent.putExtra("getYear",year);
        intent.putExtra("getKeywords",keywords);
        intent.putExtra("getHouseType",getHouseType);
        intent.putExtra("getBathrooms",getBathrooms);
        intent.putExtra("getBedRooms",getBedRooms);
        intent.putExtra("getTenure",getTenure);
        intent.putExtra("getStatus",getStatus);

        intent.putExtra("getAddress",getAddress);
        intent.putExtra("getLatitude",getLatitude);
        intent.putExtra("getLongitude",getLongitude);
        intent.putExtra("agentId",agentId);
        return intent;
    }

} // class Ends here <<<<<