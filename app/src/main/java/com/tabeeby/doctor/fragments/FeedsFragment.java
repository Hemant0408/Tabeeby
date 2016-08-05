package com.tabeeby.doctor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.adapter.ViewPagerTabAdapter;

/**
 * Created by Z510 on 8/4/2016.
 */
public class FeedsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feeds_layout, container, false);
        return view;
    }
}
