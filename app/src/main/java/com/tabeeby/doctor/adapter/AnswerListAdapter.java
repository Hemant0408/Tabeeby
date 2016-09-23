package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.addpost.AddPostActivity;
import com.tabeeby.doctor.model.AnswerModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lenovo R61 on 9/22/2016.
 */

public class AnswerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private ArrayList<AnswerModel> listItems;
    private Context mContext;

    protected TextView mAnswerUsername;
    protected TextView mAnswerText;
    protected ImageView mAnswerUserPic;
    protected TextView mHeaderQuestionTitle;
    protected TextView mHeaderQuastionText;
    protected TextView mHeaderAskBy;
    protected TextView mHeaderCount;

    public AnswerListAdapter(Context context, ArrayList<AnswerModel> listItems) {
        this.mContext = context;
        this.listItems = listItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_header, parent, false);
            return new com.tabeeby.doctor.adapter.AnswerListAdapter.VHHeader(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new com.tabeeby.doctor.adapter.AnswerListAdapter.VHItem(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            //Set header view
            mHeaderQuestionTitle.setText(listItems.get(position).getQuestion_title().trim());
            mHeaderQuastionText.setText(listItems.get(position).getQuestion_text().trim());
            mHeaderAskBy.setText("Ask By - " + listItems.get(position).getQuastion_ask_by().trim());
            mHeaderCount.setText("Answers - " + listItems.get(position).getQuastion_answer_count() + " | " + "View - " + listItems.get(position).getQuestion_view_count());
        } else {
            if (listItems.size() > 1) {
                mAnswerUsername.setText(listItems.get(position).getUsername());
                mAnswerText.setText(listItems.get(position).getAnswer_text());

                Picasso.with(mContext)
                        .load(listItems.get(position).getPicture_path())
                        .placeholder(R.drawable.default_profile)
                        .into(mAnswerUserPic);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class VHHeader extends RecyclerView.ViewHolder {


        public VHHeader(View itemView) {
            super(itemView);
            mHeaderQuestionTitle = (TextView) itemView.findViewById(R.id.header_quastion_title);
            mHeaderQuastionText = (TextView) itemView.findViewById(R.id.header_question_text);
            mHeaderAskBy = (TextView) itemView.findViewById(R.id.header_ask_by);
            mHeaderCount = (TextView) itemView.findViewById(R.id.header_count);
        }
    }

    class VHItem extends RecyclerView.ViewHolder {
        public VHItem(View itemView) {
            super(itemView);
            mAnswerUsername = (TextView) itemView.findViewById(R.id.txt_answer_user_name);
            mAnswerText = (TextView) itemView.findViewById(R.id.txt_answer_text);
            mAnswerUserPic = (ImageView) itemView.findViewById(R.id.img_answer_profile_pic);
        }
    }
}
