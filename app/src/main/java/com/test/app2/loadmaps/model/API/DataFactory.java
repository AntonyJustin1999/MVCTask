package com.test.app2.loadmaps.model.API;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.test.app2.loadmaps.DataSets.CountriesApi;
import com.test.app2.loadmaps.view.MVCView;
import com.test.app2.loadmaps.view.MVCViewFactory;
import com.test.app2.loadmaps.view.MVCViewLoginImpl;
import com.test.app2.loadmaps.view.MVCViewRegisterImpl;
import com.test.app2.loadmaps.view.MVCViewSplashScreenImpl;

import java.util.ArrayList;

public class DataFactory {
    public DATA_TYPE data_type;
    public ArrayList<CountriesApi> countriesList;

    public DATA_TYPE getData_type() {
        return data_type;
    }

    public ArrayList<CountriesApi> getCountriesList() {
        return countriesList;
    }

    public void setData_type(DATA_TYPE data_type) {
        this.data_type = data_type;
    }

    public void setCountriesList(ArrayList<CountriesApi> countriesList) {
        this.countriesList = countriesList;
    }

    public enum DATA_TYPE{
        COUNTRY_LIST_TYPE
    }
    public DataFactory getData(){
        return this;
    }
}
