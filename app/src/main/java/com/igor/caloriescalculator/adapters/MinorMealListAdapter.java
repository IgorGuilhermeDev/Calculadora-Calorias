package com.igor.caloriescalculator.adapters;

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

public class MinorMealListAdapter extends RecyclerView.Adapter<MinorMealListAdapter.MyViewHolder> {

    private List<Meal> mealList;

    public MinorMealListAdapter(List<Meal> meals){
        this.mealList = meals;

    }

    @NonNull
    @Override
    public MinorMealListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MinorMealListAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.minor_meal_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MinorMealListAdapter.MyViewHolder holder, int position) {
        Meal meal =  this.mealList.get(position);
        holder.ivClassification.setImageResource(meal.getMealClassificaion().getImageId());
        holder.tvFoodName.setText(meal.getName());
        holder.tvCalories.setText(String.format("%.2f",meal.getFoodCalories()));

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
