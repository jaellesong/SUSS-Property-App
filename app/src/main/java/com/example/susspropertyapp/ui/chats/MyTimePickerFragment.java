package com.example.susspropertyapp.ui.chats;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyTimePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY) ;
        int mMinute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), timeSetListener, mHour, mMinute, true);
    }

    private TimePickerDialog.OnTimeSetListener timeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int pHour, int pMinute) {
                    String timeString = String.format("%02d", view.getHour()) + ":" + String.format("%02d", view.getMinute());
                    Toast.makeText(getActivity(), "selected time is " + timeString , Toast.LENGTH_SHORT).show();
                    OverlaySetMeetingFragment.setTimeOnButton(timeString);
                }
            };
}