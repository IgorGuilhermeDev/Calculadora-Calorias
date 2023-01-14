package com.igor.caloriescalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.caloriescalculator.R;

public class VisualizationMealsFragment extends Fragment {

    private RecyclerView rvMeals;

    public VisualizationMealsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visualization_meal_fragment, container, false);
        return view;
    }

    private void initUIComponents(View view){
        this.rvMeals = view.findViewById(R.id.rv_meals);
        rvMeals.setHasFixedSize(true);
    }
}
