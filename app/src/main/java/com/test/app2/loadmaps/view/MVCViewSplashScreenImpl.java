package com.test.app2.loadmaps.view;

import static com.test.app2.loadmaps.MyApplication.getRealmDBAdapterInstance;
import static io.realm.Realm.getApplicationContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.test.app2.R;
import com.test.app2.loadmaps.Activities.HomeActivity;
import com.test.app2.loadmaps.Activities.LoginActivity;
import com.test.app2.loadmaps.controller.MVCSplashScreenController;
import com.test.app2.loadmaps.model.MVCModelImplementor;

public class MVCViewSplashScreenImpl implements MVCViewSplashScreen {

    View rootView;
    Activity activity;
    MVCSplashScreenController mvcSplashScreenController;
    MVCModelImplementor mvcModel;

    public MVCViewSplashScreenImpl (Context context,Activity activity, ViewGroup continer){
        this.activity = activity;
        rootView = LayoutInflater.from(context).inflate(R.layout.activity_splash_screen,continer);
        mvcModel = new MVCModelImplementor(getRealmDBAdapterInstance());
        mvcSplashScreenController = new MVCSplashScreenController(mvcModel,this);
    }

    @Override
    public void navigationToLogin() {
        Intent intent = new Intent(rootView.getContext(), LoginActivity.class);
        rootView.getContext().startActivity(intent);
        activity.finish();
    }

    @Override
    public void navigationToHome() {
        Intent intent = new Intent(rootView.getContext(), HomeActivity.class);
        rootView.getContext().startActivity(intent);
        activity.finish();
    }

    @Override
    public void showError(String ErrorMessage) {
        Toast.makeText(rootView.getContext(),ErrorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        // This method is used so that your splash activity can cover the entire screen.
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        mvcSplashScreenController.onRedirectPage();
    }
}
