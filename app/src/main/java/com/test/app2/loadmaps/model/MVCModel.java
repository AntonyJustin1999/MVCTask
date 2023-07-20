package com.test.app2.loadmaps.model;

import com.test.app2.loadmaps.Databases.UserData;

public interface MVCModel {
    Boolean IsAccountAnyExists() throws Exception;
    Boolean UserRegisteration(UserData registerData) throws Exception;
    Boolean LoginAuthentication(String UserName,String Password) throws Exception;
    void LoggedInUser(String UserName) throws Exception;
    void LoadCountrylist() throws Exception;
    void LogOut() throws Exception;

}
