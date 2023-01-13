package com.igor.caloriescalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.adapters.HourSpinnerAdapter;
import com.igor.caloriescalculator.adapters.MealSpinnerAdapter;
import com.igor.caloriescalculator.data_mock.ListHours;
import com.igor.caloriescalculator.fragments.dialogs.DatePickerDialogFragment;
import com.igor.caloriescalculator.fragments.interfaces.SetDateFromFragmentInterface;

import java.util.ArrayList;

public class RegisterMealFragment extends Fragment implements SetDateFromFragmentInterface {

    private TextView tvCaloriesLimit;
    private TextView tvDatePrompt;
    private TextView tvSelectedDate;
    private ImageView ivShowDatePicker;
    private Spinner spHour;
    private Spinner spClassifications;


    public RegisterMealFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_meal_fragment, container, false);
        initializeUiComponents(view);
        setEvents();
        return view;
    }

    private void initializeUiComponents(View view){
        this.tvCaloriesLimit = view.findViewById(R.id.tv_calories_limit);
        this.tvDatePrompt = view.findViewById(R.id.tv_date_prompt);
        this.tvSelectedDate = view.findViewById(R.id.tv_selected_date);
        this.ivShowDatePicker = view.findViewById(R.id.iv_show_date_picker);
        this.spHour = view.findViewById(R.id.sp_hour);
        spHour.setAdapter(new HourSpinnerAdapter(getContext(),  new ArrayList<>(ListHours.getListHours())));
        this.spClassifications = view.findViewById(R.id.sp_classifications);
        this.spClassifications.setAdapter(new MealSpinnerAdapter(getContext()));
    }

    private void setEvents(){
        this.ivShowDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showDatePicker(); }
        });
    }

    private void showDatePicker(){
        DatePickerDialogFragment.setInterface(this);
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment(this.tvSelectedDate.getText().toString());
        datePickerDialogFragment.show(getParentFragmentManager(), datePickerDialogFragment.getTag());
    }


    @Override
    public void setDate(String date) {
        this.tvSelectedDate.setText(date);
    }
}
