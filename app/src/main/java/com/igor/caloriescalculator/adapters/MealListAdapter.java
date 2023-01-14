package com.igor.caloriescalculator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.model.entities.Meal;

import java.util.List;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MyViewHolder> {

    private List<Meal> mealList;

    public MealListAdapter(List<Meal> meals){
        this.mealList = meals;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       Meal meal =  this.mealList.get(position);
       holder.ivClassification.setImageResource(meal.getMealClassificaion().getImageId());
       holder.tvFoodName.setText(meal.getName());
       holder.tvCalories.setText(meal.getFoodCalories()+"");

    }

    @Override
    public int getItemCount() {
        return this.mealList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivClassification;
        public TextView tvFoodName;
        public TextView tvCalories;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivClassification = itemView.findViewById(R.id.iv_classification);
            this.tvFoodName = itemView.findViewById(R.id.tv_food_name);
            this.tvCalories = itemView.findViewById(R.id.tv_calories);
        }
    }
}
