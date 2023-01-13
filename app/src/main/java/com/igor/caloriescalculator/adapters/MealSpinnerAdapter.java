package com.igor.caloriescalculator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.igor.caloriescalculator.R;
import com.igor.caloriescalculator.model.enums.MealClassification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<MealClassification> classifications;

    public MealSpinnerAdapter(Context context){
        this.context = context;
        classifications = new ArrayList<>(Arrays.asList(MealClassification.values()));

    }
    @Override
    public int getCount() {
        return classifications.size();
    }

    @Override
    public Object getItem(int position) {
        return this.classifications.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = LayoutInflater.from(this.context).inflate(R.layout.spinner_item, parent, false);
        TextView tvItemName = rootView.findViewById(R.id.tv_item_name);
        ImageView ivImgItem = rootView.findViewById(R.id.iv_img_item);
        tvItemName.setText(this.classifications.get(position).getName());
        ivImgItem.setImageResource(this.classifications.get(position).getImageId());
        return rootView;
    }
}
