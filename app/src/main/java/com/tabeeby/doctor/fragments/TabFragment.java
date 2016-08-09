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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Z510 on 8/4/2016.
 */
public class TabFragment extends Fragment {

    ViewPagerTabAdapter viewPagerAdapter;

    //@Bind(R.id.tabLayout)
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

    private ViewPagerTabAdapter setUpViewPager() {
        viewPagerAdapter = new ViewPagerTabAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new FeedsFragment(), "Feeds");

        if(com.tabeeby.doctor.BuildConfig.VERSION) {
            viewPagerAdapter.addFragment(new DoctorsFragment(), "Patients");
        }
        else
        {
            viewPagerAdapter.addFragment(new PatientFragment(), "Doctors");
        }
        viewPagerAdapter.addFragment(new AppointmentsFragment(), "Appointments");
        viewPagerAdapter.addFragment(new OffersFragment(), "Offers");

        return viewPagerAdapter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tabCall = tabLayout.getTabAt(0);
        tabCall.setIcon(R.drawable.selector_feed);

        if(com.tabeeby.doctor.BuildConfig.VERSION) {
            TabLayout.Tab tabCal = tabLayout.getTabAt(1);
            tabCal.setIcon(R.drawable.selector_doctor);
        }else
        {
            TabLayout.Tab tabCal = tabLayout.getTabAt(1);
            tabCal.setIcon(R.drawable.selector_doctor);
        }

        TabLayout.Tab tabCall__ = tabLayout.getTabAt(2);
        tabCall__.setIcon(R.drawable.selector_appointment);

        TabLayout.Tab tabCall_ = tabLayout.getTabAt(3);
        tabCall_.setIcon(R.drawable.selector_offers);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
