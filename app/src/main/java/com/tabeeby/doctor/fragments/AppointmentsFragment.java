package com.tabeeby.doctor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.adapter.AppointmentAdapter;
import com.tabeeby.doctor.adapter.FindDoctorAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Z510 on 8/4/2016.
 */
public class AppointmentsFragment extends Fragment {

    ArrayList<String> arrayList;
    AppointmentAdapter findDoctorAdapter;
    LinearLayoutManager linearLayoutManager;
/*    @Bind(R.id.lv_appointment_list)
    protected RecyclerView mRvListView;*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appointment_layout, container, false);
       // ButterKnife.bind(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.lv_appointment_list);
        arrayList=new ArrayList<>();

        //this is for six cards
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");

        findDoctorAdapter = new AppointmentAdapter(getActivity(),arrayList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(findDoctorAdapter);
    }

    @Override
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
}
