package com.example.susspropertyapp.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.susspropertyapp.R;

import static com.example.susspropertyapp.R.id;
import static com.example.susspropertyapp.R.layout;

public class PropertyLoanFragment extends Fragment {


    Toolbar topAppBar;
    MediaPlayer mp;

    EditText editValue;
    EditText editRate;
    EditText editTenure;
    EditText editLoan;
    SeekBar seekRate;
    SeekBar seekTenure;
    SeekBar seekLoan;
    TextView tvAmount;
    String passValue;
    public PropertyLoanFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            passValue = getArguments().getString("amount");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(layout.fragment_loan_property, container, false);
        topAppBar = root.findViewById(id.topAppBar);
        mp = MediaPlayer.create(getContext(), R.raw.button);
        topAppBar.setNavigationOnClickListener(view1 -> requireActivity().onBackPressed() );


        editValue = root.findViewById(id.editValue);
        editRate = root.findViewById(id.editRate);
        editTenure = root.findViewById(id.editTenure);
        editLoan = root.findViewById(id.editLoan);

        seekRate = root.findViewById(id.seekBarRate);
        seekTenure = root.findViewById(id.seekBarTenure);
        seekLoan = root.findViewById(id.seekBarLoan);

        tvAmount = root.findViewById(id.tvAmount);

        resetValues();
        if (passValue != null){
            passValue=passValue.replaceAll(",","")
                    .replaceAll("S","").replaceAll("\\$","")
                    .replaceAll(" ","");
            editValue.setText(passValue);
        }

        calculateMortgage();
        Button calculateBtn = root.findViewById(id.btnCalculate);


        seekRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String progressStr = String.format("%.1f", Double.valueOf(progress)/10);
                if (fromUser) editRate.setText(progressStr);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        editRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int after, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try{
                    Float num = Float.parseFloat(s.toString())*10;
                    int progress = Math.round(num);
                    if (num>100){
                        progress = 100;
                        editRate.setText("10.0");
                    } else if (num <0){
                        progress = 0;
                        editRate.setText("0.0");
                    }
                    seekRate.setProgress(progress);
                } catch(Exception ex) {}
            }
        });


        seekTenure.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) editTenure.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        editTenure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int after, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try{
                    //Update Seekbar value after entering a number
                    int progress = Math.round(Float.parseFloat(s.toString()));
                    if (progress > 35){
                        progress = 35;
                        editTenure.setText("35");
                    } else if (progress < 0) {
                        progress = 0;
                        editTenure.setText("0");
                    }

                    seekTenure.setProgress(progress);
                } catch(Exception ex) {}
            }
        });

        seekLoan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) editLoan.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        editLoan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int after, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                try{
                    //Update Seekbar value after entering a number
                    int progress = Math.round(Float.parseFloat(s.toString()));
                    if (progress > 100){
                        progress = 100;
                        editLoan.setText("100");
                    } else if (progress < 0) {
                        progress = 0;
                        editLoan.setText("0");
                    }

                    seekLoan.setProgress(progress);
                } catch(Exception ex) {}
            }
        });


        calculateBtn.setOnClickListener(view -> {
            calculateMortgage();
        });

        topAppBar.setOnMenuItemClickListener(item -> {
            mp.start();
            if (item.getItemId() == id.reset) {
                resetValues();
            }
            return true;
        });

        return root;
    }

    public void resetValues(){
        editValue.setText("100000");
        editRate.setText("0.1");
        editTenure.setText("5");
        editLoan.setText("50");
        tvAmount.setText("S$ 5016");
        seekRate.setProgress(1);
        seekTenure.setProgress(5);
        seekLoan.setProgress(50);

    }

    public void calculateMortgage(){
        double totalCharge = 0.0;
        try {
            double value = Double.parseDouble(String.valueOf(editValue.getText()));
            double rate = Double.parseDouble(String.valueOf(editRate.getText())) ;
            double tenure = Double.parseDouble(String.valueOf(editTenure.getText()));
            double loan = Double.parseDouble(String.valueOf(editLoan.getText()));

            double principal = value * loan/100;
            rate = (rate/100)/12;
            double time = tenure * 12;
//                totalCharge = principle * rate / (1 - Math.pow(1 + rate, - numberOfPayments));
            totalCharge = (principal * rate) / (1 - Math.pow(1 + rate, - time));
//            Log.i("info", "value "+value);
//            Log.i("info", "rate "+ rate);
//            Log.i("info", "tenure "+ tenure);
//            Log.i("loan", "loan "+ loan);
//            Log.i("info", "principle "+principal);
//            Log.i("info", "numberOfPayments "+ time);

            tvAmount.setText( "S$ "+ (int)totalCharge );
        }
        catch (Exception e) {
            // pop alert or something
            Log.i("info", e+"");

        }
        if (totalCharge < 0){
            totalCharge = 0;
        }
        tvAmount.setText( "S$ "+ (int)totalCharge );
    }

}