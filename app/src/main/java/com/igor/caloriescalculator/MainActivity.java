package com.igor.caloriescalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvCaloriesLimit;
    private TextView tvDatePrompt;
    private TextView tvSelectedDate;
    private ImageView ivShowDatePicker;
    private DatePicker dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUiComponents();
        setEvents();
    }

    private void initializeUiComponents(){
        this.tvCaloriesLimit = findViewById(R.id.tv_calories_limit);
        this.tvDatePrompt = findViewById(R.id.tv_date_prompt);
        this.tvSelectedDate = findViewById(R.id.tv_selected_date);
        this.ivShowDatePicker = findViewById(R.id.iv_show_date_picker);
        this.dp = findViewById(R.id.dp);
    }

    private void setEvents(){
        this.ivShowDatePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { showOrHideDatePicker(); }
                    });
        this.dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setSelectedDate(year, monthOfYear, dayOfMonth);}
            });
    }

    private void showOrHideDatePicker(){
        if(this.dp.getVisibility() == View.VISIBLE) setDateFormVisible(View.VISIBLE);
        else setDateFormVisible(View.GONE);
    }

    private void setDateFormVisible(int visibility){
        this.tvDatePrompt.setVisibility(visibility);
        this.tvSelectedDate.setVisibility(visibility);
        this.ivShowDatePicker.setVisibility(visibility);
        int dpVisibility = visibility == View.GONE ? View.VISIBLE : View.GONE;
        this.dp.setVisibility(dpVisibility);
    }

    private void setSelectedDate(int year, int monthOfYear, int dayOfMonth){
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        this.tvSelectedDate.setText(date);
        showOrHideDatePicker();
    }
}