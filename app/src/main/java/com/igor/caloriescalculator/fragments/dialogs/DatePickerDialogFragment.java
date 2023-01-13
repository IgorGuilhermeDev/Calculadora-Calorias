package com.igor.caloriescalculator.fragments.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.fragments.interfaces.SetDateFromFragmentInterface;

public class DatePickerDialogFragment extends DialogFragment {

    private static SetDateFromFragmentInterface setDateInterface;
    private DatePicker dpInjestionDate;
    private String previusDate;


    public DatePickerDialogFragment(){

    }

    public DatePickerDialogFragment(String previusDate){
        this.previusDate = previusDate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_picker_fragment, container, false);
        initializeUiComponents(view);
        dataBinding();
        setEvents();
        return view;
    }

    private void initializeUiComponents(View view){
        this.dpInjestionDate = view.findViewById(R.id.dp_injestion_date);
    }


    private void dataBinding(){

        if(this.previusDate != null){
            int day = Integer.valueOf(this.previusDate.substring(0,2));
            int month = Integer.valueOf(this.previusDate.substring(3,5));
            int year = Integer.valueOf(this.previusDate.substring(6));
            this.dpInjestionDate.updateDate(year, month - 1, day);
        }
    }

    private void setEvents(){
        this.dpInjestionDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
              selectDateAndCloseDialog(year, monthOfYear, dayOfMonth);
            }
        });
    }

    private void selectDateAndCloseDialog( int year, int monthOfYear, int dayOfMonth){
        String strMonth;
        String strDay;
        if(monthOfYear < 10) strMonth = "0" + (monthOfYear + 1);
        else strMonth = "" + (monthOfYear + 1);
        if(dayOfMonth < 10) strDay = "0" + (dayOfMonth);
        else strDay = "" + dayOfMonth;

        setDateInterface.setDate(strDay + "/" + strMonth + "/" + year);

        this.dismiss();
    }
    public static void setInterface(SetDateFromFragmentInterface setDateFromFragmentInterface){
        DatePickerDialogFragment.setDateInterface = setDateFromFragmentInterface;
    }


}
