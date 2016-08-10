package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tabeeby.doctor.R;

import java.util.ArrayList;

/**
 * Created by Lenovo R61 on 8/10/2016.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ListHolder> {

    private Context mContext;
    private static ArrayList<String> items;

    public static class ListHolder extends RecyclerView.ViewHolder {


        public ListHolder(View itemView) {
            super(itemView);
        }

    }

    public OfferAdapter(Context context, ArrayList<String> arrayList){
        mContext = context;
        this.items = arrayList;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_item, viewGroup, false);
        ListHolder listHolder = new ListHolder(v);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(ListHolder ListHolder, final int i) {
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
