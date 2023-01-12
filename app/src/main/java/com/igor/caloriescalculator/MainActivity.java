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
    }

    private void showOrHideDatePicker(){
        if(this.dp.getVisibility() == View.VISIBLE) {
            this.tvDatePrompt.setVisibility(View.VISIBLE);
            this.tvSelectedDate.setVisibility(View.VISIBLE);
            this.ivShowDatePicker.setVisibility(View.VISIBLE);
            this.dp.setVisibility(View.GONE);
        }
        else {
            this.tvDatePrompt.setVisibility(View.GONE);
            this.tvSelectedDate.setVisibility(View.GONE);
            this.ivShowDatePicker.setVisibility(View.GONE);
            this.dp.setVisibility(View.VISIBLE);
        }

    }
}