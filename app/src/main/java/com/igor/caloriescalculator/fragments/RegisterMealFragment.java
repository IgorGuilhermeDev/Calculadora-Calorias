package com.igor.caloriescalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.adapters.HourSpinnerAdapter;
import com.igor.caloriescalculator.adapters.MealSpinnerAdapter;
import com.igor.caloriescalculator.data_mock.ListHours;
import com.igor.caloriescalculator.fragments.dialogs.DatePickerDialogFragment;
import com.igor.caloriescalculator.fragments.interfaces.SetDateFromFragmentInterface;
import com.igor.caloriescalculator.model.controllers.RegisterMealFragmentController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RegisterMealFragment extends Fragment implements SetDateFromFragmentInterface {

    private TextView tvCaloriesLimit;
    private TextView tvSelectedDate;
    private EditText etName;
    private EditText etCalories;
    private ImageView ivShowDatePicker;
    private Spinner spHour;
    private Spinner spClassifications;
    private Button btRegister;
    private Button btVisualization;
    private RegisterMealFragmentController controller;
    private final String LIMIT_CALORIES_REGEX = "[0-9]+\\.[0-9]+";


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
        configSpinners();
        controller = new RegisterMealFragmentController(this.getContext(), View.inflate(getContext(), R.layout.register_meal_fragment, new LinearLayoutCompat(getContext())));
        dataBinding();
        return view;
    }

    private void initializeUiComponents(View view){
        this.tvCaloriesLimit = view.findViewById(R.id.tv_calories_limit);
        this.tvSelectedDate = view.findViewById(R.id.tv_selected_date);
        this.etName = view.findViewById(R.id.et_name);
        this.etCalories = view.findViewById(R.id.et_calories);
        this.ivShowDatePicker = view.findViewById(R.id.iv_show_date_picker);
        this.spHour = view.findViewById(R.id.sp_hour);
        this.spClassifications = view.findViewById(R.id.sp_classifications);
        this.btRegister = view.findViewById(R.id.bt_register);
        this.btVisualization = view.findViewById(R.id.bt_visualization);
    }

    private void setEvents(){
        this.ivShowDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showDatePicker(); }
        });

        this.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMeal();
            }
        });

        this.btVisualization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toVisualizationScreen();
            }
        });
    }


    private void showDatePicker(){
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment(this.tvSelectedDate.getText().toString());
        DatePickerDialogFragment.setInterface(this);
        datePickerDialogFragment.show(getParentFragmentManager(), datePickerDialogFragment.getTag());
    }

    private void dataBinding(){
        this.tvSelectedDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        updateCaloriesLimit();
    }

    private void insertMeal(){
        boolean isSuccessful = controller.insertMeal(this.etName.getText().toString(), this.etCalories.getText().toString(),
                this.spClassifications.getSelectedItem(), this.tvSelectedDate.getText().toString(), this.spHour.getSelectedItem());

        if(isSuccessful) updateCaloriesLimit();
    }


    private void configSpinners(){
        this.spHour.setAdapter(new HourSpinnerAdapter(getContext(),  new ArrayList<>(ListHours.getListHours())));
        this.spClassifications.setAdapter(new MealSpinnerAdapter(getContext()));
    }

    private void toVisualizationScreen() {
        getActivity().getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_main, new VisualizationMealsFragment())
                .addToBackStack("main_screen").commit();
    }

    private void updateCaloriesLimit(){
        String auxText = this.tvCaloriesLimit.getText().toString();
        auxText = auxText.replaceAll(LIMIT_CALORIES_REGEX,this.controller.getCaloriesFromThisDay());
        this.tvCaloriesLimit.setText( auxText );
    }


    @Override
    public void setDate(String date) {
        this.tvSelectedDate.setText(date);
    }
}
