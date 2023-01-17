package com.igor.caloriescalculator.fragments.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.adapters.MealListAdapter;
import com.igor.caloriescalculator.adapters.MinorMealListAdapter;
import com.igor.caloriescalculator.model.entities.Meal;

import java.util.List;

public class StatisticsDialogFragment extends DialogFragment {

    private final String TEXT_AUX = "De";
    private final String TEXT_AUX_2 = "até";

    private String initialDate;
    private String finalDate;

    private TextView tvDates;
    private TextView tvSumOfCalories;
    private TextView tvMediaOfCalories;
    private RecyclerView rvMeal;

    private Double sum;
    private Double mediaOfCalories;
    private List<Meal> meals;
    private Context context;

    public StatisticsDialogFragment(){

    }
    public StatisticsDialogFragment(Context context, String initialDate, String finalDate, double sum, double mediaForDay, List<Meal> groupedMeals){
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.sum = sum;
        this.mediaOfCalories = mediaForDay;
        this.context = context;
        meals = groupedMeals;

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
        this.tvSumOfCalories = view.findViewById(R.id.tv_sum_of_calories);
        this.tvMediaOfCalories = view.findViewById(R.id.tv_media_of_calories);
        this.rvMeal = view.findViewById(R.id.rv_grouped_meals);
    }

    private void dataBinding(){
        this.tvDates.setText(TEXT_AUX + " " + this.initialDate + " " + TEXT_AUX_2 + " " + this.finalDate);
        this.tvSumOfCalories.setText( " " + this.sum);
        this.tvMediaOfCalories.setText(" " + String.format("%.2f", this.mediaOfCalories));
        this.rvMeal.setLayoutManager(new LinearLayoutManager(context));
        this.rvMeal.setHasFixedSize(true);
        this.rvMeal.setAdapter(new MinorMealListAdapter(this.meals));
    }
}
