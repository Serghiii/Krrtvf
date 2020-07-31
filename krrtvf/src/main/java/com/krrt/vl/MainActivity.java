package com.krrt.vl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private static final String STATE_BNAI = "BNAI";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.isChecked())return false;
            else return DoChoice(item.getItemId());
        }
    };

    private boolean DoChoice(int Id){
        switch (Id) {
            case R.id.navigation_home:
                doHome();
                return true;
            case R.id.navigation_concern:
                doConcern();
                return true;
            case R.id.navigation_services:
                doServices();
                return true;
            case R.id.navigation_partners:
                doPartners();
                return true;
            case R.id.navigation_press_center:
                doPress_center();
                return true;
        }
        return false;
    }

    private void doHome() {
        HomeFragment fr = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, fr);
        ft.commit();
    }

    private void doConcern() {
        ConcernFragment fr = new ConcernFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, fr);
        ft.commit();
    }

    private void doServices() {
        ServicesFragment fr = new ServicesFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, fr);
        ft.commit();
    }

    private void doPartners() {
        PartnersFragment fr = new PartnersFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, fr);
        ft.commit();
    }

    private void doPress_center() {
        Press_centerFragment fr = new Press_centerFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, fr);
        ft.commit();
    }

    private void removePaddingFromNavigationItem(BottomNavigationView bottomNavigationView) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            View activeLabel = item.findViewById(R.id.largeLabel);
            if (activeLabel instanceof TextView) {
                activeLabel.setPadding(0, 0, 0, 0);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_title);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#184967")));
        getSupportActionBar().setElevation(0);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        removePaddingFromNavigationItem(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        DoChoice(findViewById(R.id.navigation_home).getId());
//        navigation.setSelectedItemId(findViewById(R.id.navigation_home).getId());

//        int BNI = R.id.navigation_home;
//        if (savedInstanceState != null) {
//            BNI = savedInstanceState.getInt("STATE_BNI", R.id.navigation_home);
//        }
//        DoChoice(BNI);
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        outState.putInt("STATE_BNI", navigation.getSelectedItemId());
//    }

}
