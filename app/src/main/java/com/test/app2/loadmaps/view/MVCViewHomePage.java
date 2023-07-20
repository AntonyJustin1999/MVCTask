package com.test.app2.loadmaps.view;

import com.test.app2.loadmaps.DataSets.CountriesApi;
import com.test.app2.loadmaps.model.API.DataFactory;

import java.util.ArrayList;

public interface MVCViewHomePage extends MVCView {
    void navigationToHome();
    void showError(String ErrorMessage);
    void showprogressbar();
    void hideprogressbar();
    void navigationToLogin();
}

