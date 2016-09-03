package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tabeeby.doctor.R;

import java.util.ArrayList;

public class FindDoctorAdapter extends RecyclerView.Adapter<FindDoctorAdapter.ListHolder> {

    private static ArrayList<String> items;
    private Context mContext;

    public FindDoctorAdapter(Context context, ArrayList<String> arrayList) {
        mContext = context;
        this.items = arrayList;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

<<<<<<< HEAD
        public FindDoctorAdapter(Context context, ArrayList<String> arrayList){
            mContext = context;
            items = arrayList;
        }
=======
    @Override
    public ListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_card, viewGroup, false);
        ListHolder listHolder = new ListHolder(v);
        return listHolder;
    }
>>>>>>> b528eaec3454dcf115dcaec209117caf90f326a4

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
