package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.events.EventDetailActivity;
import com.tabeeby.doctor.activities.quastionandanswer.ViewQuestionAndAnswer;
import com.tabeeby.doctor.model.Events;
import com.tabeeby.doctor.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lenovo R61 on 8/11/2016.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ListHolder> {

    private static ArrayList<Events> items;
    private Context mContext;

    public EventAdapter(Context context, ArrayList<Events> arrayList) {
        mContext = context;
        items = arrayList;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_item, viewGroup, false);
        ListHolder listHolder = new ListHolder(v);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(ListHolder ListHolder, final int i) {
        ListHolder.mTextViewEventTitle.setText(items.get(i).getEvent_title());
       /* ListHolder.mTextViewEventDescription.setText(items.get(i).getEvent_description());
        ListHolder.mTextViewEventLocation.setText(items.get(i).getEvent_location());
        ListHolder.mTextViewEventPalce.setText(items.get(i).getUsername());

        ListHolder.mTextViewEventDataTime.setText(Utils.ConvertDateUnixtoNormal(items.get(i).getEvent_start_time_unix(),"dd MMM")+" to "+
                Utils.ConvertDateUnixtoNormal(items.get(i).getEvent_start_time_unix(),"dd MMM")+" | "+
                Utils.ConvertDateUnixtoNormal(items.get(i).getEvent_start_time_unix(),"hh a")+" to "+
                Utils.ConvertDateUnixtoNormal(items.get(i).getEvent_start_time_unix(),"hh a"));
        */
        Picasso.with(mContext)
                .load(items.get(i).getEvent_banner_path())
                .placeholder(R.drawable.default_profile)
                .into(ListHolder.mImageViewBanner);


        ListHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext, EventDetailActivity.class);
                intent.putExtra("EventTitle",items.get(i).getEvent_title());
                intent.putExtra("EventDate",items.get(i).getEvent_start_time_unix());
                intent.putExtra("EventDesc",items.get(i).getEvent_description());
                intent.putExtra("EventLocation",items.get(i).getEvent_location());
                intent.putExtra("EventUserName",items.get(i).getUsername());
                intent.putExtra("EventBannerPath",items.get(i).getEvent_banner_path());
                intent.putExtra("EventId",items.get(i).getEvent_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ListHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_event_title)
        protected TextView mTextViewEventTitle;

       /* @Bind(R.id.txt_event_data_time)
        protected  TextView mTextViewEventDataTime;

        @Bind(R.id.txt_event_location)
        protected  TextView mTextViewEventLocation;

        @Bind(R.id.txt_event_place)
        protected TextView mTextViewEventPalce;*/

       /* @Bind(R.id.txt_event_description)
        protected  TextView mTextViewEventDescription;
*/
        @Bind(R.id.img_event_banner)
        protected ImageView mImageViewBanner;

        public ListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

}
