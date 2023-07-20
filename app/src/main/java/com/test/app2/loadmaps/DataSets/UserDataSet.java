package com.test.app2.loadmaps.DataSets;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserDataSet extends RealmObject {
    // on below line we are creating our variables
    // and with are using primary key for our id.
    public String userName = null;
    public String mobileNumber = null;
    public String emailId = null;
    public String password = null;
    public String conpassword = null;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConpassword() {
        return conpassword;
    }

    public void setConpassword(String conpassword) {
        this.conpassword = conpassword;
    }
}
