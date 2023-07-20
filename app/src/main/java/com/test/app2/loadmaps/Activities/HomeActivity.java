package com.test.app2.loadmaps.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.test.app2.R;
import com.test.app2.loadmaps.DataSets.CountriesApi;
import com.test.app2.loadmaps.view.MVCView;
import com.test.app2.loadmaps.view.MVCViewFactory;
import com.test.app2.loadmaps.view.MVCViewHomePageImpl;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    MVCView mvcView;
    MVCViewHomePageImpl mvcViewHomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mvcView = MVCViewFactory.getMVCView(MVCViewFactory.VIEW_TYPE.HOME_VIEW_TYPE, HomeActivity.this,this, null, getIntent());
        setContentView(mvcView.getRootView());
        mvcViewHomePage = new MVCViewHomePageImpl(this,this,null);
        mvcView.initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            mvcViewHomePage.navigationToHome();
        } else if (id == R.id.nav_location_play) {

        } else if (id == R.id.nav_logout) {
            mvcViewHomePage.navigationToLogin();
            mvcViewHomePage.OnLogOut();
        }
        return false;
    }

}
