package com.example.susspropertyapp.ui.chats;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.login.ui.MainLoginActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class OverlaySetMeetingFragment extends Fragment {
    private static Button setDateBtn;
    private static Button setTimeBtn;
    String roomId;
    public OverlaySetMeetingFragment(String roomId){
        this.roomId = roomId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_overlay_set_meeting, container, false);
        setDateBtn = root.findViewById(R.id.setDateBtn);
        setTimeBtn = root.findViewById(R.id.setTimeBtn);
        setDateBtn.setOnClickListener(v-> showDatePicker(root));
        setTimeBtn.setOnClickListener(v-> showTimePicker(root));



        root.findViewById(R.id.shadedOverlay).setOnClickListener(v-> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
        });


        root.findViewById(R.id.closeBtn).setOnClickListener(v-> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
        });


        root.findViewById(R.id.confirmBtn).setOnClickListener(v-> {
            Bundle bundle = new Bundle();
            bundle.putString("meeting", setDateBtn.getText().toString()+" "+setTimeBtn.getText().toString() );
            bundle.putString("roomId", roomId );

            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            navController.navigate(R.id.chatDetailFragment,bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
        });

        return root;

    }

    public void showDatePicker(View v) {
        DialogFragment newFragment = new MyDatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "date picker");
    }

    public void showTimePicker(View v) {
        DialogFragment newFragment = new MyTimePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "time picker");
    }

    public static void setDateOnButton(String dateString){
        setDateBtn.setText(dateString);
    }
    public static void setTimeOnButton(String timeString){
        setTimeBtn.setText(timeString);
    }
}