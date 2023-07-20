package com.test.app2.loadmaps.controller;

import android.os.Handler;

import com.test.app2.loadmaps.model.MVCModelImplementor;
import com.test.app2.loadmaps.view.MVCViewSplashScreenImpl;

public class MVCSplashScreenController {
    MVCModelImplementor mvcModel;
    MVCViewSplashScreenImpl mvcView;

    public MVCSplashScreenController(MVCModelImplementor mvcModel, MVCViewSplashScreenImpl mvcView){
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
    }

    public void onRedirectPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if(mvcModel.IsAccountAnyExists()){
                        mvcView.navigationToHome();
                    } else {
                        mvcView.navigationToLogin();
                    }
                } catch (Exception e) {
                    mvcView.showError(e.getMessage());
                }
            }
        },2000);
    }

}
