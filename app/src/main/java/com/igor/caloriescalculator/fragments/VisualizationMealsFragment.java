package com.igor.caloriescalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.adapters.MealListAdapter;
import com.igor.caloriescalculator.fragments.dialogs.DatePickerDialogFragment;
import com.igor.caloriescalculator.fragments.interfaces.SetDateFromFragmentInterface;
import com.igor.caloriescalculator.model.repositories.MealRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class VisualizationMealsFragment extends Fragment implements SetDateFromFragmentInterface {

    private RecyclerView rvMeals;
    private MealRepository repository;
    private TextView tvInitialDate;
    private TextView tvFinalDate;
    private ImageView ivInitialDatePicker;
    private ImageView ivFinalDatePicker;

    private final Integer ID_INITAL_DATE_PICKER = 1;
    private final Integer ID_FINAL_DATE_PICKER = 2;

    private static final String DATE_PATTERN = "dd/MM/yyyy";

    public VisualizationMealsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.repository == null){
            this.repository = new MealRepository(getContext());
        }
        DatePickerDialogFragment.setInterface(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visualization_meal_fragment, container, false);
        initUIComponents(view);
        configRecyclerView();
        setEvents();
        dataBinding();
        return view;
    }

    private void initUIComponents(View view){
        this.rvMeals = view.findViewById(R.id.rv_meals);
        this.ivInitialDatePicker = view.findViewById(R.id.iv_initial_date_picker);
        this.ivFinalDatePicker = view.findViewById(R.id.iv_final_date_picker);
        this.tvInitialDate = view.findViewById(R.id.tv_initial_date);
        this.tvFinalDate = view.findViewById(R.id.tv_final_date);


    }

    private void configRecyclerView(){
        this.rvMeals.setHasFixedSize(true);
        this.rvMeals.setLayoutManager(new LinearLayoutManager(getContext()));
        setRecyclerViewContent(ZonedDateTime.of(LocalDateTime.now().with(LocalTime.MIDNIGHT), ZoneId.systemDefault()).toEpochSecond(),
                ZonedDateTime.of(LocalDateTime.now().with(LocalTime.NOON), ZoneId.systemDefault()).toEpochSecond() );

    }

    private void setRecyclerViewContent(long beginDate, long endDate) {
        this.rvMeals.setAdapter(new MealListAdapter(repository.selectAllTodayMeals(beginDate, endDate)));
    }

    private void dataBinding(){
        String today = getToday();
        this.tvInitialDate.setText(today);
        this.tvFinalDate.setText(today);
    }

    private void setEvents(){
        this.ivInitialDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(ID_INITAL_DATE_PICKER);
            }
        });

        this.ivFinalDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(ID_FINAL_DATE_PICKER);
            }
        });
    }

    private void showDatePicker(int idDatePicker){
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        DatePickerDialogFragment.setInterface(this);
        DatePickerDialogFragment.setChooseDate(idDatePicker);
        datePickerDialogFragment.show(getParentFragmentManager(), datePickerDialogFragment.getTag());
    }

    private String getToday(){
       return LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    @Override
    public void setDate(String date) {
        final String EXTRACT_DATE_REGEX = "[0-9]\\s";
        String datePickerId = date.substring(0,1);
        date = date.replaceAll(EXTRACT_DATE_REGEX, "");

        if(datePickerId.equals("1")) this.tvInitialDate.setText(date);
        else this.tvFinalDate.setText(date);

        setRecyclerViewContent(stringDateToLong(this.tvInitialDate.getText().toString(), LocalTime.MIDNIGHT),
                stringDateToLong(this.tvFinalDate.getText().toString(), LocalTime.NOON));

    }

    private long stringDateToLong(String date, LocalTime time){
        LocalDate formatedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
        return ZonedDateTime.of(LocalDateTime.of(formatedDate,(time)), ZoneId.systemDefault()).toEpochSecond();
    }
}
