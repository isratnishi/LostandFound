package com.opus_bd.lostandfound.Utils;

import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utilities {
    public static void showLogcatMessage(String message) {
        Log.d("tag", message);
    }


  /*  public static void datePicker(View view){
        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int
                            selectedYear, int selectedMonth, int selectedDay) {
                        Calendar saleDateCalender = Calendar.getInstance();
                        saleDateCalender.set(Calendar.YEAR, selectedYear);
                        saleDateCalender.set(Calendar.MONTH, selectedMonth);
                        saleDateCalender.set(Calendar.DAY_OF_MONTH, selectedDay);

                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                        etVehicleDate.setText(formatter.format(saleDateCalender.getTime()));
                    }
                }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();
    }*/
}
