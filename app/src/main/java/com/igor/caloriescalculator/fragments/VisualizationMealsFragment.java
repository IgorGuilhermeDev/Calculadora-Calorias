package com.igor.caloriescalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.adapters.MealListAdapter;
import com.igor.caloriescalculator.model.repositories.MealRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class VisualizationMealsFragment extends Fragment {

    private RecyclerView rvMeals;
    private MealRepository repository;

    public VisualizationMealsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.repository = new MealRepository(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visualization_meal_fragment, container, false);
        initUIComponents(view);
        return view;
    }

    private void initUIComponents(View view){
        this.rvMeals = view.findViewById(R.id.rv_meals);
        rvMeals.setHasFixedSize(true);
        rvMeals.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMeals.setAdapter(new MealListAdapter(repository.selectAllTodayMeals(
                ZonedDateTime.of(LocalDateTime.now().with(LocalTime.MIDNIGHT), ZoneId.systemDefault()).toEpochSecond(),
                ZonedDateTime.of(LocalDateTime.now().with(LocalTime.NOON), ZoneId.systemDefault()).toEpochSecond()
        )));
    }
}
