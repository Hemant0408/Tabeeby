package com.tabeeby.doctor.activities.maintabactivity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.activities.login.LoginActivity;
import com.tabeeby.doctor.activities.myappointment.MyAppointmentActivity;
import com.tabeeby.doctor.activities.mypatient.MyPatientActivity;
import com.tabeeby.doctor.activities.profile.DoctorProfileActivity;
import com.tabeeby.doctor.activities.profile.PatientProfileActivity;
import com.tabeeby.doctor.fragments.MyDoctors;
import com.tabeeby.doctor.fragments.TabFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    RelativeLayout notificationCount;

    private TextView headerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        headerTextView = (TextView) header.findViewById(R.id.headerTextView);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getSupportActionBar().setTitle("");
                    // event when click home button
                    getSupportFragmentManager().popBackStack();
                    displayFragment(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        displayFragment(1);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.profile) {
            if(com.tabeeby.doctor.BuildConfig.VERSION) {
                Intent intent = new Intent(this, DoctorProfileActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, PatientProfileActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.dashboard) {
            /*Intent intent = new Intent(this, PatientProfileActivity.class);
            startActivity(intent);*/
            displayFragment(1);
        } else if (id == R.id.my_doctor) {
            if(com.tabeeby.doctor.BuildConfig.VERSION) {
                Intent intent = new Intent(this, MyPatientActivity.class);
                startActivity(intent);
            }else
            {
                displayFragment(2);
            }
        }
        else if (id == R.id.my_appointments) {
                Intent intent = new Intent(this, MyAppointmentActivity.class);
                startActivity(intent);
        }
        else if(id==R.id.logout)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayFragment(int i) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        getSupportActionBar().setTitle("");
        switch (i) {

            case 1:
                fragment = new TabFragment();
                break;

            case 2:
                fragment = new MyDoctors();
                break;

            default:
                fragment = new TabFragment();
                break;
        }
        if (i == 1 && fragmentManager.getBackStackEntryCount() > 1 && !fragmentManager.getBackStackEntryAt(0).getName().equals(fragment.getClass().getName())) {

            fragmentManager.popBackStack(fragment.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            navigationView.getMenu().getItem(i - 1).setChecked(true);
        } else if (i > 10) {
            fragmentManager.beginTransaction()
                    // .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                    .replace(R.id.containerView, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
            for (int z = 0; z < navigationView.getMenu().size(); z++) {
                navigationView.getMenu().getItem(z).setChecked(false);
            }
        } else {
            fragmentManager.popBackStack(fragment.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction()
                    // .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                    .replace(R.id.containerView, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
            navigationView.setCheckedItem(navigationView.getMenu().getItem(i - 1).getItemId());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() != 1) {
                Log.i("TAG", "fragmentStack size :" + getSupportFragmentManager().getBackStackEntryCount());
                getSupportFragmentManager().popBackStack();
            } else {
                finish();
            }
        }
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        try {
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            if (searchView != null) {
                //searchView.setOnQueryTextListener(this);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        MenuItem item1 = menu.findItem(R.id.sig_out);
        MenuItemCompat.setActionView(item1, R.layout.custom_menu_icon_layout);
        notificationCount = (RelativeLayout) MenuItemCompat.getActionView(item1);
        Button button = (Button) notificationCount.findViewById(R.id.notification_bell_menu_button);
        //button.setOnClickListener(this);

        searchItem.setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

}
