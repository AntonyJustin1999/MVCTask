package com.test.app2.loadmaps.model;

import androidx.annotation.NonNull;

import com.test.app2.loadmaps.DataSets.CountriesApi;
import com.test.app2.loadmaps.Databases.UserData;
import com.test.app2.loadmaps.model.API.ApiCall;
import com.test.app2.loadmaps.model.API.DataFactory;
import com.test.app2.loadmaps.model.API.RestManager;
import com.test.app2.loadmaps.model.db.RealmDBAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MVCModelImplementor implements MVCModel {

    RealmDBAdapter realmDBAdapter;
    RestManager restManager;
    List<Observer> observers;
    public MVCModelImplementor(RealmDBAdapter realmDBAdapter){
        this.realmDBAdapter = realmDBAdapter;
        this.restManager = new RestManager();
        observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObserversforCountryList(DataFactory dataFactory){
        for(Observer observer: observers){
            observer.updateData(dataFactory);
        }
    }
    public void notifyObserversforShowProgress(){
        for(Observer observer: observers){
            observer.showProgress();
        }
    }
    public void notifyObserversforHideProgress(){
        for(Observer observer: observers){
            observer.hideProgress();
        }
    }
    public void notifyObserversforErrorMsg(String msg){
        for(Observer observer: observers){
            observer.showErrorMsg(msg);
        }
    }

    @Override
    public Boolean IsAccountAnyExists() throws Exception {

        try{
            boolean result = realmDBAdapter.IsloginAccountExists();
            if (result){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new Exception("Some thing went wrong!!!");
        }

    }

    @Override
    public Boolean UserRegisteration(UserData registerData) throws Exception {
        try{
            boolean result = realmDBAdapter.RegisterData(registerData);
            if (result){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean LoginAuthentication(String UserName, String Password) throws Exception {
        try{
            boolean result = realmDBAdapter.IsloginCredentialExists(UserName,Password);
            if (result){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new Exception("Some thing went wrong!!!");
        }
    }

    @Override
    public void LoggedInUser(String UserName) throws Exception {
        try{
            realmDBAdapter.loginUser(UserName);
        } catch (Exception e){
            throw new Exception("Some thing went wrong!!!");
        }
    }

    @Override
    public void LoadCountrylist() {
        ApiCall apiCall = restManager.getRetrofitClient().create(ApiCall.class);

        Call<ArrayList<CountriesApi>> Call = apiCall.getAllCountrieslist(true,"" +
                "name,flags");
        notifyObserversforShowProgress();
        Call.enqueue(new Callback<ArrayList<CountriesApi>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<CountriesApi>> call, @NonNull Response<ArrayList<CountriesApi>> response) {
                try {
                    if(response.body()!=null){
                        DataFactory dataFactory = new DataFactory();
                        dataFactory.setData_type(DataFactory.DATA_TYPE.COUNTRY_LIST_TYPE);
                        dataFactory.setCountriesList(response.body());
                        notifyObserversforCountryList(dataFactory);
                        notifyObserversforHideProgress();
                    } else {
                        notifyObserversforErrorMsg("");
                        notifyObserversforHideProgress();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    notifyObserversforErrorMsg(e.getMessage());
                    notifyObserversforHideProgress();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<CountriesApi>> call, @NonNull Throwable t) {
                notifyObserversforErrorMsg(t.getMessage());
                notifyObserversforHideProgress();
            }
        });
    }

    @Override
    public void LogOut() throws Exception {
        try{
            realmDBAdapter.LogOut();
        } catch (Exception e){
            throw new Exception("Some thing went wrong!!!");
        }
    }


}
