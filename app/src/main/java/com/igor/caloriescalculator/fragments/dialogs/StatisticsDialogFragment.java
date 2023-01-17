package com.igor.caloriescalculator.fragments.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.igor.caloriescalculator.R;

public class StatisticsDialogFragment extends DialogFragment {

    private final String TEXT_AUX = "De";
    private final String TEXT_AUX_2 = "até";

    private String initialDate;
    private String finalDate;

    private TextView tvDates;
    private TextView tvSumOfCalories;


    public StatisticsDialogFragment(){

    }
    public StatisticsDialogFragment(String initialDate, String finalDate){
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistcs_dialog_fragment,  container, false);
        initUIComponents(view);
        dataBinding();
        return view;
    }


    private void initUIComponents(View view){
        this.tvDates = view.findViewById(R.id.tv_dates);
    }

    private void dataBinding(){
        this.tvDates.setText(TEXT_AUX + " " + this.initialDate + " " + TEXT_AUX_2 + " " + this.finalDate);
    }
}
