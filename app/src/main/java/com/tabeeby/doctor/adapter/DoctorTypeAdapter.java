package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Z510 on 8/8/2016.
 */
public class DoctorTypeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> arrayListType;

    public DoctorTypeAdapter(Context mContext, ArrayList<String> arrayListType) {
        this.context = mContext;
        this.arrayListType = arrayListType;
    }

    @Override
    public int getCount() {
        return arrayListType.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListType.get(position);
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
