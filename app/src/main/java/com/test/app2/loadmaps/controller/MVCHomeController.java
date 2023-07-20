package com.test.app2.loadmaps.controller;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.test.app2.loadmaps.model.MVCModelImplementor;
import com.test.app2.loadmaps.view.MVCViewHomePageImpl;
import com.test.app2.loadmaps.view.MVCViewSplashScreenImpl;

import java.util.ArrayList;

public class MVCHomeController {
    MVCModelImplementor mvcModel;
    MVCViewHomePageImpl mvcView;
    Context context;

    public MVCHomeController(MVCModelImplementor mvcModel, MVCViewHomePageImpl mvcView,Context context){
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
        this.context = context;
    }
    public void OnLoadCountryList() {
        mvcModel.LoadCountrylist();
    }

    public void OnHomePageRedirect() {
        mvcView.navigationToHome();
    }

    public void OnLogOut() {
        try {
            mvcModel.LogOut();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isNetworkAvailable(){
        try {
            if(context!=null){
                ConnectivityManager connectivity = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivity != null) {
                    NetworkInfo info = connectivity.getActiveNetworkInfo();
                    if (info != null && info.isConnected()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
