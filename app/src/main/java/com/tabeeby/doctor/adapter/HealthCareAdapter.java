package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Z510 on 8/8/2016.
 */
public class HealthCareAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> arrayListHealthCare;

    public HealthCareAdapter(Context mContext, ArrayList<String> arrayListHealthCare) {
        this.context = mContext;
        this.arrayListHealthCare = arrayListHealthCare;
    }

    @Override
    public int getCount() {
        return arrayListHealthCare.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListHealthCare.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
