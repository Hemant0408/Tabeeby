package com.tabeeby.doctor.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.addpost.AddPostActivity;

import java.util.List;

/**
 * Created by Lenovo R61 on 8/10/2016.
 */
public class FeedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    LinearLayout linearLayout;
    List<String> listItems;
    Context context;

    public FeedsAdapter(Context context, List<String> listItems) {
        // this.header = header;
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
            return new VHHeader(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_layout_row, parent, false);
            return new VHItem(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }

    //    need to override this method
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    //increasing getItemcount to 1. This will be the row of header.
    @Override
    public int getItemCount() {
        return listItems.size() + 1;
    }

    class VHHeader extends RecyclerView.ViewHolder {
        //TextView txtTitle;
        public VHHeader(View itemView) {
            super(itemView);
           linearLayout=(LinearLayout) itemView.findViewById(R.id.  linear_parent_header);
                   linearLayout.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent intent=new Intent(context, AddPostActivity.class);
                           context.startActivity(intent);
                       }
                   });

        }
    }

    class VHItem extends RecyclerView.ViewHolder {
        /*TextView txtName;
        ImageView iv;*/
        public VHItem(View itemView) {
            super(itemView);
            // this.txtName = (TextView)itemView.findViewById(R.id.txtName);
            //   this.iv = (ImageView)itemView.findViewById(R.id.ivListItem);
        }
    }

}
