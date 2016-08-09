package com.tabeeby.doctor.activities.maintabactivity;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.tabeeby.doctor.R;
import com.tabeeby.doctor.fragments.TabFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

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

        displayFragment(1);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    public void displayFragment(int i) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        getSupportActionBar().setTitle("");
        switch (i) {

            case 1:
                fragment = new TabFragment();
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
}
