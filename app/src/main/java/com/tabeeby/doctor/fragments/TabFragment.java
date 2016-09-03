package com.tabeeby.doctor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.events.EventsActivity;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.tabeeby.doctor.activities.news.NewsActivity;
import com.tabeeby.doctor.activities.quastionandanswer.QuastionAndAnswerList;
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
    @Bind(R.id.bottom_toolbar_qanda)
    protected LinearLayout mQuaAndAns;

    @Bind(R.id.bottom_toolbar_event)
    protected LinearLayout mEvent;

    @Bind(R.id.bottom_toolbar_news)
    protected LinearLayout mNews;


    @Bind(R.id.img_toolbar_qanda)
    protected ImageView mImgQuaAndAns;

    @Bind(R.id.img_toolbar_event)
    protected ImageView mImgEvent;

    @Bind(R.id.img_toolbar_news)
    protected ImageView mImgNews;


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
        viewPagerAdapter.addFragment(new FeedsFragment(), getActivity().getString(R.string.feeds));

        if (com.tabeeby.doctor.BuildConfig.VERSION) {
            viewPagerAdapter.addFragment(new DoctorsFragment(), getActivity().getString(R.string.patients));
        } else {
            viewPagerAdapter.addFragment(new PatientFragment(), getActivity().getString(R.string.doctors));
        }
        viewPagerAdapter.addFragment(new AppointmentsFragment(), getActivity().getString(R.string.appointments));
        viewPagerAdapter.addFragment(new OffersFragment(), getActivity().getString(R.string.offers));

        return viewPagerAdapter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tabCall = tabLayout.getTabAt(0);
        tabCall.setIcon(R.drawable.selector_feed);

        if (com.tabeeby.doctor.BuildConfig.VERSION) {
            TabLayout.Tab tabCal = tabLayout.getTabAt(1);
            tabCal.setIcon(R.drawable.selector_patient);
        } else {
            TabLayout.Tab tabCal = tabLayout.getTabAt(1);
            tabCal.setIcon(R.drawable.selector_doctor);
        }

        TabLayout.Tab tabCall__ = tabLayout.getTabAt(2);
        tabCall__.setIcon(R.drawable.selector_appointment);

        TabLayout.Tab tabCall_ = tabLayout.getTabAt(3);
        tabCall_.setIcon(R.drawable.selector_offers);


        if (viewPager.getCurrentItem() == 0) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.feeds));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0: {
                        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.feeds));
                    }
                    break;

                    case 1: {
                        if (com.tabeeby.doctor.BuildConfig.VERSION) {
                            ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.patients));
                        } else {
                            ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.doctors));
                        }
                    }
                    break;

                    case 2: {
                        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.appointments));
                    }
                    break;

                    case 3: {
                        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.offers));
                    }
                    break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        mImgQuaAndAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), QuastionAndAnswerList.class));
            }
        });

        mImgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EventsActivity.class));
            }
        });

        mImgNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewsActivity.class));
            }
        });


       mQuaAndAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), QuastionAndAnswerList.class));
            }
        });

        mEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EventsActivity.class));
            }
        });

        mNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewsActivity.class));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
