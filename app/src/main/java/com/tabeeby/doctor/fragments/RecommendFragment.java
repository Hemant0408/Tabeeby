package com.tabeeby.doctor.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.tabeeby.doctor.BuildConfig;
import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.maintabactivity.MainActivity;
import com.tabeeby.doctor.activities.profile.DoctorProfileActivity;
import com.tabeeby.doctor.adapter.FindDoctorAdapter;

import java.util.ArrayList;

/**
 * Created by Z510 on 8/10/2016.
 */
public class RecommendFragment extends Fragment {

    ArrayList<String> arrayList;
    FindDoctorAdapter findDoctorAdapter;
    LinearLayoutManager linearLayoutManager;

    /* @Bind(R.id.lv_doctor_list)
     protected RecyclerView mRvListView;
 */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_layout, container, false);
        //ButterKnife.bind(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.lv_doctor_list);
        arrayList = new ArrayList<>();

        //this is for six cards
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");

        findDoctorAdapter = new FindDoctorAdapter(getActivity(), arrayList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(findDoctorAdapter);

        //((MainActivity) getActivity()).getSupportActionBar().setTitle("Recommended");

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (!BuildConfig.VERSION) {
                    startActivity(new Intent(getActivity(), DoctorProfileActivity.class));
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.search);
        item.setVisible(true);

        MenuItem item_ = menu.findItem(R.id.sig_out);
        item_.setVisible(true);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
