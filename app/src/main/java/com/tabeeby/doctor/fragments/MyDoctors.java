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
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.tabeeby.doctor.adapter.ViewPagerDoctorAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Z510 on 8/10/2016.
 */
public class MyDoctors extends Fragment {

    ViewPagerDoctorAdapter viewPagerAdapter;

    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);
        ButterKnife.bind(this, view);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        viewPagerAdapter = setUpViewPager();
    }

    private ViewPagerDoctorAdapter setUpViewPager() {
        viewPagerAdapter = new ViewPagerDoctorAdapter(getChildFragmentManager());

        viewPagerAdapter.addFragment(new MyDoctorsFragment(), getActivity().getResources().getString(R.string.my_doctors));
        viewPagerAdapter.addFragment(new RecommendFragment(), getActivity().getResources().getString(R.string.recommended));

        return viewPagerAdapter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        if (viewPager.getCurrentItem() == 0) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.my_doctors);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0: {
                        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.my_doctors);
                    }
                    break;

                    case 1: {
                        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.recommended);
                    }
                    break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
