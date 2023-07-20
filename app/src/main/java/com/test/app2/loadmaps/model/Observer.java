package com.test.app2.loadmaps.model;

import android.provider.ContactsContract;

import com.test.app2.loadmaps.DataSets.CountriesApi;
import com.test.app2.loadmaps.model.API.DataFactory;

import java.util.ArrayList;

public interface Observer {
    void updateData(DataFactory dataFactory);
    void showProgress();
    void hideProgress();
    void onNetworkFailed();
    void showErrorMsg(String ErrorMessage);
}
