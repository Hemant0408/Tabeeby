package com.tabeeby.doctor.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.viewpager.ViewPagerActivity;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private Integer[] mResources;

    public ViewPagerAdapter(ViewPagerActivity mContext, Integer[] mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

        TextView tvPagerItem = (TextView) itemView.findViewById(R.id.tv_pager_item);
        TextView tvPagerDesc = (TextView) itemView.findViewById(R.id.tv_pager_description);
        ImageView imgIcon = (ImageView) itemView.findViewById(R.id.img_icon);

        tvPagerItem.setText(mResources[position]);
        tvPagerDesc.setText((ViewPagerActivity.mImage[position]));
        imgIcon.setImageDrawable(mContext.getResources().getDrawable(ViewPagerActivity.mImageText[position]));

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
