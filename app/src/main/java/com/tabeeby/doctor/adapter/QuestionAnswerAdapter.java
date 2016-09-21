package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.model.QuestionsModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lenovo R61 on 9/20/2016.
 */

public class QuestionAnswerAdapter extends RecyclerView.Adapter<QuestionAnswerAdapter.ListHolder> {

    private static ArrayList<QuestionsModel> items;
    private Context mContext;



    public QuestionAnswerAdapter(Context context, ArrayList<QuestionsModel> arrayList) {
        mContext = context;
        this.items = arrayList;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_group, viewGroup, false);
        ListHolder listHolder = new ListHolder(v);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(ListHolder ListHolder, final int i) {
        ListHolder.mTextViewShortQuestion.setText(items.get(i).getQuestion_title());
        ListHolder.mTextViewViewCount.setText("Answer -"+items.get(i).getAnswer_count()+"|"+" View -"+items.get(i).getView_count());
        ListHolder.mTextViewAskBy.setText("Asked By -"+items.get(i).getFullname());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ListHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_short_que_desc)
        protected TextView mTextViewShortQuestion;

        @Bind(R.id.txt_answer_and_view_count)
        protected TextView mTextViewViewCount;

        @Bind(R.id.txt_ask_by)
        protected TextView mTextViewAskBy;

        public ListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

}