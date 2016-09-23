package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.quastionandanswer.ViewQuestionAndAnswer;
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
        if(items.get(i).getQuestion_title()!=null)
        ListHolder.mTextViewShortQuestion.setText(items.get(i).getQuestion_title());

        ListHolder.mTextViewViewCount.setText("Answers - "+items.get(i).getAnswer_count()+" | "+" Views -"+items.get(i).getView_count());

        if(items.get(i).getFullname()!=null)
        ListHolder.mTextViewAskBy.setText("Asked By - "+items.get(i).getFullname());

        Picasso.with(mContext)
                .load(items.get(i).getPicture_path())
                .placeholder(R.drawable.default_profile)
                .into(ListHolder.mImageViewQuestionAskPicture);


        ListHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext, ViewQuestionAndAnswer.class);
                intent.putExtra("category",items.get(i).getCategory());
                intent.putExtra("qua_title",items.get(i).getQuestion_title());
                intent.putExtra("qua_desc",items.get(i).getQuestion_text());
                intent.putExtra("view_count",items.get(i).getView_count());
                intent.putExtra("answer_count",items.get(i).getAnswer_count());
                intent.putExtra("ask_by",items.get(i).getFullname());
                intent.putExtra("user_id",items.get(i).getUser_id());
                intent.putExtra("question_id",items.get(i).getQuestion_id());
                mContext.startActivity(intent);
            }
        });
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

        @Bind(R.id.img_question_asked_by_pic)
        protected de.hdodenhof.circleimageview.CircleImageView mImageViewQuestionAskPicture;

        public ListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}