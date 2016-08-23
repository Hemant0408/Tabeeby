package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tabeeby.doctor.R;

import java.util.ArrayList;

/**
 * Created by Lenovo R61 on 8/10/2016.
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ListHolder> {

    private static ArrayList<String> items;
    private Context mContext;

    public AppointmentAdapter(Context context, ArrayList<String> arrayList) {
        mContext = context;
        this.items = arrayList;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appointments_card, viewGroup, false);
        ListHolder listHolder = new ListHolder(v);
        TextView textView = (TextView) viewGroup.findViewById(R.id.txt_upcomming);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(ListHolder ListHolder, final int i) {

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ListHolder extends RecyclerView.ViewHolder {


        public ListHolder(View itemView) {
            super(itemView);
        }

    }

}
