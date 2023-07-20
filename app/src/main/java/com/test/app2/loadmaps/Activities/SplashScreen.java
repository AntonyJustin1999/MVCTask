package com.test.app2.loadmaps.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.test.app2.R;
import com.test.app2.loadmaps.view.MVCView;
import com.test.app2.loadmaps.view.MVCViewFactory;

import io.realm.Realm;
import io.realm.RealmResults;

public class SplashScreen extends AppCompatActivity {
    MVCView mvcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mvcView = MVCViewFactory.getMVCView(MVCViewFactory.VIEW_TYPE.SPLASH_VIEW_TYPE, SplashScreen.this,this, null, getIntent());
        setContentView(mvcView.getRootView());
        mvcView.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
