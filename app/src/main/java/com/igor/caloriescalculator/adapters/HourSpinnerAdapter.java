package com.igor.caloriescalculator.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.igor.caloriescalculator.R;

import java.util.List;


public class HourSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<String> hours;

    public HourSpinnerAdapter(Context context, List<String> hours){
        this.context = context;
        this.hours = hours;

    }

    @Override
    public int getCount() {
        return this.hours.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
        tvItemName.setText(this.hours.get(position));
        ivImgItem.setImageResource(R.drawable.clock_icon);
        return rootView;
    }
}
