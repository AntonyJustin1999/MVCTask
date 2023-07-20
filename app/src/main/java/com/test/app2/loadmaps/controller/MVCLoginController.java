package com.test.app2.loadmaps.controller;

import android.os.Handler;

import com.test.app2.loadmaps.model.MVCModelImplementor;
import com.test.app2.loadmaps.view.MVCViewLoginImpl;
import com.test.app2.loadmaps.view.MVCViewSplashScreenImpl;

public class MVCLoginController {
    MVCModelImplementor mvcModel;
    MVCViewLoginImpl mvcView;

    public MVCLoginController(MVCModelImplementor mvcModel, MVCViewLoginImpl mvcView){
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
    }

    public void ValidateLogin(String username, String password) {
        if(username.length()>0){
            if(password.length()>0){
                mvcView.showprogressbar();
                try {
                    if(mvcModel.LoginAuthentication(username, password)){
                        mvcView.hideprogressbar();
                        mvcModel.LoggedInUser(username);
                        mvcView.navigationToHome();
                    } else {
                        mvcView.showError("Invalid UserName and Password");
                        mvcView.hideprogressbar();
                    }
                } catch (Exception e) {
                    mvcView.showError(e.getMessage());
                }

            } else {
                mvcView.showError("Please enter Password");
            }
        } else {
            mvcView.showError("Please enter UserNmae");
        }
    }

}
