package com.example.susspropertyapp.ui.chats;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.susspropertyapp.R;

import java.util.Calendar;

public class MyDatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    String dateString = view.getDayOfMonth() +
                            "/" + (view.getMonth()+1) +
                            "/" + view.getYear();
                    Toast.makeText(getActivity(), "selected date is " + dateString,
                                    Toast.LENGTH_SHORT).show();
                    OverlaySetMeetingFragment.setDateOnButton(dateString);
                }
            };
}