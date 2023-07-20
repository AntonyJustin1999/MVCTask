package com.test.app2.loadmaps.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MVCViewFactory {
    public enum VIEW_TYPE{
        SPLASH_VIEW_TYPE,LOGIN_VIEW_TYPE,REGISTER_VIEW_TYPE,HOME_VIEW_TYPE
    }

    public static MVCView getMVCView(VIEW_TYPE viewType, Context context, AppCompatActivity activity, ViewGroup viewGroup, Intent intent){
        MVCView mvcView = null;
        switch (viewType){
            case SPLASH_VIEW_TYPE: mvcView = new MVCViewSplashScreenImpl(context, activity,viewGroup); break;
            case LOGIN_VIEW_TYPE:mvcView = new MVCViewLoginImpl(context,activity, viewGroup); break;
            case REGISTER_VIEW_TYPE:mvcView = new MVCViewRegisterImpl(context,activity, viewGroup); break;
            case HOME_VIEW_TYPE:mvcView = new MVCViewHomePageImpl(context,activity, viewGroup); break;
        }
        return mvcView;
    }
}
