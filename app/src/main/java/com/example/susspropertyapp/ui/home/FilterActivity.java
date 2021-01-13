package com.example.susspropertyapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.NavHostFragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.ui.loan.LoanFragment;

import java.util.ArrayList;
import java.util.logging.Filter;

public class FilterActivity extends AppCompatActivity {

    Toolbar topAppBar;
    MediaPlayer mp;

    EditText etPriceMin; EditText etPriceMax;
    EditText etAreaMin; EditText etAreaMax;
    EditText etYearMin; EditText etYearMax;
    EditText etKeywords;

    /////////////////////////////////// On Create ///////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        topAppBar = findViewById(R.id.topAppBar);
        mp = MediaPlayer.create(this, R.raw.button);


        ToggleButton tbHsTypeAny = findViewById(R.id.tbHsTypeAny);
        ToggleButton tbHsTypeCondo = findViewById(R.id.tbHsTypeCondo);
        ToggleButton tbHsTypeHDB = findViewById(R.id.tbHsTypeHDB);
        ToggleButton tbHsTypeLanded = findViewById(R.id.tbHsTypeLanded);
        ArrayList<ToggleButton> tbHsTypeList= new ArrayList<>();

        ToggleButton tbBedAny = findViewById(R.id.tbBedAny);
        ToggleButton tbBedStudio = findViewById(R.id.tbBedStudio);
        ToggleButton tbBed1 = findViewById(R.id.tbBed1);
        ToggleButton tbBed2 = findViewById(R.id.tbBed2);
        ToggleButton tbBed3 = findViewById(R.id.tbBed3);
        ToggleButton tbBed4 = findViewById(R.id.tbBed4);
        ToggleButton tbBed5 = findViewById(R.id.tbBed5);
        ArrayList<ToggleButton> tbBedList = new ArrayList<>();

        ToggleButton tbBathAny = findViewById(R.id.tbBathAny);
        ToggleButton tbBath1 = findViewById(R.id.tbBath1);
        ToggleButton tbBath2 = findViewById(R.id.tbBath2);
        ToggleButton tbBath3 = findViewById(R.id.tbBath3);
        ToggleButton tbBath4 = findViewById(R.id.tbBath4);
        ToggleButton tbBath5 = findViewById(R.id.tbBath5);
        ToggleButton tbBath6 = findViewById(R.id.tbBath6);
        ArrayList<ToggleButton> tbBathList = new ArrayList<>();

        ToggleButton tbTenureAny = findViewById(R.id.tbTenureAny);
        ToggleButton tbTenure99 = findViewById(R.id.tbTenure99);
        ToggleButton tbTenure999 = findViewById(R.id.tbTenure999);
        ToggleButton tbTenureFreehold = findViewById(R.id.tbTenureFreehold);
        ArrayList<ToggleButton> tbTenureList= new ArrayList<>();

        ToggleButton tbStatusAll = findViewById(R.id.tbStatusAll);
        ToggleButton tbStatusNew = findViewById(R.id.tbStatusNew);
        ToggleButton tbStatusResale = findViewById(R.id.tbStatusResale);
        ArrayList<ToggleButton> tbStatusList= new ArrayList<>();

        etPriceMin = findViewById(R.id.etPriceMin);
        etPriceMax = findViewById(R.id.etPriceMax);
        etAreaMin = findViewById(R.id.etAreaMin);
        etAreaMax = findViewById(R.id.etAreaMax);
        etYearMin = findViewById(R.id.etYearMin);
        etYearMax = findViewById(R.id.etYearMax);
        etKeywords = findViewById(R.id.etKeywords);


        topAppBar.setNavigationOnClickListener(view1 -> {
            mp.start();
            Intent intent = new Intent(this, com.example.susspropertyapp.MainActivity.class);
            intent.putExtra("destination", (Integer) R.id.navigation_home_results);
            intent.putExtra("isFiltering", false);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, options.toBundle());

        });
        topAppBar.setOnMenuItemClickListener(item -> {
            mp.start();
            if (item.getItemId() == R.id.clear) {
                etPriceMin.setText("");
                etPriceMax.setText("");
                etAreaMin.setText("");
                etAreaMax.setText("");
                etYearMin.setText("");
                etYearMax.setText("");
                etKeywords.setText("");
                resetListRadio(tbHsTypeList);
                resetListRadio(tbBedList);
                resetListRadio(tbBathList);
                resetListRadio(tbTenureList);
                resetListRadio(tbStatusList);
            }
            return true;
        });

        findViewById(R.id.continueBtn).setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, com.example.susspropertyapp.MainActivity.class);
            intent.putExtra("destination", (Integer) R.id.navigation_home_results);
//            intent.putExtra("latitude", (double) myPlace.latitude);
//            intent.putExtra("longitude", (double) myPlace.longitude);
            intent.putExtra("isFiltering", true);
            intent = addEditTextToIntent(intent,"priceMin", etPriceMin);
            intent = addEditTextToIntent(intent,"priceMax", etPriceMax);
            intent = addEditTextToIntent(intent,"areaMin", etAreaMin);
            intent = addEditTextToIntent(intent,"areaMax", etAreaMax);
            intent = addEditTextToIntent(intent,"yearMin", etYearMin);
            intent = addEditTextToIntent(intent,"yearMax", etYearMax);
            intent.putExtra("keywords", etKeywords.getText().toString());
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, options.toBundle());
        });



        //////////////////////////// housing type toggle btns ////////////////////////////////////
        tbHsTypeList.add(tbHsTypeAny); tbHsTypeList.add(tbHsTypeCondo);
        tbHsTypeList.add(tbHsTypeHDB); tbHsTypeList.add(tbHsTypeLanded);
        setListToRadio(tbHsTypeList);
        //////////////////////////// BED type toggle btns ////////////////////////////////////
        tbBedList.add(tbBedAny); tbBedList.add(tbBedStudio);
        tbBedList.add(tbBed1); tbBedList.add(tbBed2);
        tbBedList.add(tbBed3); tbBedList.add(tbBed4);
        tbBedList.add(tbBed5);
        setListToRadio(tbBedList);
        //////////////////////////// Bath type toggle btns ////////////////////////////////////
        tbBathList.add(tbBathAny);
        tbBathList.add(tbBath1); tbBathList.add(tbBath2);
        tbBathList.add(tbBath3); tbBathList.add(tbBath4);
        tbBathList.add(tbBath5); tbBathList.add(tbBath6);
        setListToRadio(tbBathList);
        //////////////////////////// Tenure type toggle btns ////////////////////////////////////
        tbTenureList.add(tbTenureAny); tbTenureList.add(tbTenure99);
        tbTenureList.add(tbTenure999); tbTenureList.add(tbTenureFreehold);
        setListToRadio(tbTenureList);
        //////////////////////////// Status type toggle btns ////////////////////////////////////
        tbStatusList.add(tbStatusAll); tbStatusList.add(tbStatusNew);
        tbStatusList.add(tbStatusResale);
        setListToRadio(tbStatusList);
        /////////////////////////////////////////////////////////////////////////////////////////
    }




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


    public void resetListRadio(ArrayList<ToggleButton> thisList){
        for (ToggleButton tb : thisList) setBtnUnchk(tb);
        setBtnChk(thisList.get(0));
    }

    public Intent addEditTextToIntent(Intent intent, String putName, EditText item){
        if (item.getText() != null
                && !item.getText().toString().isEmpty()
                && !item.getText().toString().equals("")
                && Integer.parseInt(item.getText().toString())-1 != -1 ) {
            intent.putExtra(putName, item.getText().toString() );

        }
        return intent;
    }

}